package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserVendorMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserVendor;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserVendorVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserVendorQuery;
import com.xdcplus.xdcweb.biz.generator.UserVendorBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.utils.BeanUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户供应商中间表(UserVendor)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:31
 */
public class UserVendorBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<UserVendor, UserVendorVO, UserVendor, UserVendorMapper> implements UserVendorBaseService<S, T, E> {

    @Autowired
    protected UserVendorMapper userVendorMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveUserVendor(UserVendorDTO userVendorDTO) {

        UserVendor userVendor = userVendorMapper.selectById(userVendorDTO.getId());
        if (ObjectUtil.isNotNull(userVendor)) {
            log.error("saveUserVendor() The UserVendor already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        userVendor = new UserVendor();
        BeanUtil.copyProperties(userVendorDTO, userVendor);
        userVendor.setCreatedTime(DateUtil.current());
        userVendor.setDeleted(0);

        return this.save(userVendor);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateUserVendor(UserVendorDTO userVendorDTO) {

        UserVendor userVendor = this.getById(userVendorDTO.getId());
        if (ObjectUtil.isNull(userVendor)) {
            log.error("updateUserVendor() The UserVendor does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(userVendorDTO, userVendor);
        userVendor.setUpdatedUser(userVendorDTO.getUpdatedUser());
        userVendor.setUpdatedTime(DateUtil.current());

        return this.updateById(userVendor);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<UserVendor> userVendorList) {

        userVendorList.forEach(userVendor -> {
            UserVendor userVendorParam = new UserVendor();
            userVendorParam.setId(userVendor.getId());
            if (ObjectUtil.isNotNull(userVendor.getId())) {
                userVendor.setId(userVendor.getId());
                userVendor.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<UserVendor> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(UserVendor::getId, userVendor.getId());
                update(userVendor, lambdaUpdate);
            } else {
                userVendor.setCreatedTime(DateUtil.current());
                userVendor.setDeleted(0);
                save(userVendor);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<UserVendorDTO> userVendorDTOList) {

        List<UserVendor> userVendorList = BeanUtils.copyProperties(userVendorDTOList, UserVendor.class);
        return saveOrUpdateBatch(userVendorList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteUserVendorLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        UserVendor userVendor = this.getById(id);

        if (ObjectUtil.isNull(userVendor)) {
            log.error("deleteUserVendor() The UserVendor does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        userVendor.setDeleted(1);

        return this.updateById(userVendor);
    }

    private List<UserVendor> queryUserVendorList(UserVendorFilterDTO userVendorFilterDTO) {
        if (ObjectUtil.isNull(userVendorFilterDTO)) {
            userVendorFilterDTO = new UserVendorFilterDTO();
        }
        userVendorFilterDTO.setDeleted(0);
        UserVendorQuery userVendorQuery = BeanUtil.copyProperties(userVendorFilterDTO, UserVendorQuery.class);

        return userVendorMapper.queryUserVendor(userVendorQuery);
    }

    @Override
    public List<UserVendorVO> queryUserVendorVOList(UserVendorFilterDTO userVendorFilterDTO) {
        if (ObjectUtil.isNull(userVendorFilterDTO)) {
            userVendorFilterDTO = new UserVendorFilterDTO();
        }
        userVendorFilterDTO.setDeleted(0);
        return this.objectConversion(queryUserVendorList(userVendorFilterDTO));
    }

    @Override
    public PageVO<UserVendorVO> queryUserVendor(UserVendorFilterDTO userVendorFilterDTO) {
        if (ObjectUtil.isNull(userVendorFilterDTO)) {
            userVendorFilterDTO = new UserVendorFilterDTO();
        }
        userVendorFilterDTO.setDeleted(0);
        PageVO<UserVendorVO> pageVO = new PageVO<>();

        if (userVendorFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(userVendorFilterDTO.getCurrentPage(), userVendorFilterDTO.getPageSize(),
                    userVendorFilterDTO.getOrderType(), userVendorFilterDTO.getOrderField());
        }

        List<UserVendor> userVendorList = queryUserVendorList(userVendorFilterDTO);

        PageInfo<UserVendor> pageInfo = new PageInfo<>(userVendorList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(userVendorList));

        return pageVO;
    }

    @Override
    public UserVendorVO queryUserVendorById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

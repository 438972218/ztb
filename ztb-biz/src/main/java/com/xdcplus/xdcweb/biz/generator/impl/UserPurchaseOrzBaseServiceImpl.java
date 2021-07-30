package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserPurchaseOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserPurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserPurchaseOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserPurchaseOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserPurchaseOrzVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserPurchaseOrzQuery;
import com.xdcplus.xdcweb.biz.generator.UserPurchaseOrzBaseService;
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
 * 用户采购组织中间表(UserPurchaseOrz)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:30
 */
public class UserPurchaseOrzBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<UserPurchaseOrz, UserPurchaseOrzVO, UserPurchaseOrz, UserPurchaseOrzMapper> implements UserPurchaseOrzBaseService<S, T, E> {

    @Autowired
    protected UserPurchaseOrzMapper userPurchaseOrzMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveUserPurchaseOrz(UserPurchaseOrzDTO userPurchaseOrzDTO) {

        UserPurchaseOrz userPurchaseOrz = userPurchaseOrzMapper.selectById(userPurchaseOrzDTO.getId());
        if (ObjectUtil.isNotNull(userPurchaseOrz)) {
            log.error("saveUserPurchaseOrz() The UserPurchaseOrz already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        userPurchaseOrz = new UserPurchaseOrz();
        BeanUtil.copyProperties(userPurchaseOrzDTO, userPurchaseOrz);
        userPurchaseOrz.setCreatedTime(DateUtil.current());
        userPurchaseOrz.setDeleted(0);

        return this.save(userPurchaseOrz);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateUserPurchaseOrz(UserPurchaseOrzDTO userPurchaseOrzDTO) {

        UserPurchaseOrz userPurchaseOrz = this.getById(userPurchaseOrzDTO.getId());
        if (ObjectUtil.isNull(userPurchaseOrz)) {
            log.error("updateUserPurchaseOrz() The UserPurchaseOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(userPurchaseOrzDTO, userPurchaseOrz);
        userPurchaseOrz.setUpdatedUser(userPurchaseOrzDTO.getUpdatedUser());
        userPurchaseOrz.setUpdatedTime(DateUtil.current());

        return this.updateById(userPurchaseOrz);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<UserPurchaseOrz> userPurchaseOrzList) {

        userPurchaseOrzList.forEach(userPurchaseOrz -> {
            UserPurchaseOrz userPurchaseOrzParam = new UserPurchaseOrz();
            userPurchaseOrzParam.setId(userPurchaseOrz.getId());
            if (ObjectUtil.isNotNull(userPurchaseOrz.getId())) {
                userPurchaseOrz.setId(userPurchaseOrz.getId());
                userPurchaseOrz.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<UserPurchaseOrz> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(UserPurchaseOrz::getId, userPurchaseOrz.getId());
                update(userPurchaseOrz, lambdaUpdate);
            } else {
                userPurchaseOrz.setCreatedTime(DateUtil.current());
                userPurchaseOrz.setDeleted(0);
                save(userPurchaseOrz);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<UserPurchaseOrzDTO> userPurchaseOrzDTOList) {

        List<UserPurchaseOrz> userPurchaseOrzList = BeanUtils.copyProperties(userPurchaseOrzDTOList, UserPurchaseOrz.class);
        return saveOrUpdateBatch(userPurchaseOrzList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteUserPurchaseOrzLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        UserPurchaseOrz userPurchaseOrz = this.getById(id);

        if (ObjectUtil.isNull(userPurchaseOrz)) {
            log.error("deleteUserPurchaseOrz() The UserPurchaseOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        userPurchaseOrz.setDeleted(1);

        return this.updateById(userPurchaseOrz);
    }

    private List<UserPurchaseOrz> queryUserPurchaseOrzList(UserPurchaseOrzFilterDTO userPurchaseOrzFilterDTO) {
        if (ObjectUtil.isNull(userPurchaseOrzFilterDTO)) {
            userPurchaseOrzFilterDTO = new UserPurchaseOrzFilterDTO();
        }
        userPurchaseOrzFilterDTO.setDeleted(0);
        UserPurchaseOrzQuery userPurchaseOrzQuery = BeanUtil.copyProperties(userPurchaseOrzFilterDTO, UserPurchaseOrzQuery.class);

        return userPurchaseOrzMapper.queryUserPurchaseOrz(userPurchaseOrzQuery);
    }

    @Override
    public List<UserPurchaseOrzVO> queryUserPurchaseOrzVOList(UserPurchaseOrzFilterDTO userPurchaseOrzFilterDTO) {
        if (ObjectUtil.isNull(userPurchaseOrzFilterDTO)) {
            userPurchaseOrzFilterDTO = new UserPurchaseOrzFilterDTO();
        }
        userPurchaseOrzFilterDTO.setDeleted(0);
        return this.objectConversion(queryUserPurchaseOrzList(userPurchaseOrzFilterDTO));
    }

    @Override
    public PageVO<UserPurchaseOrzVO> queryUserPurchaseOrz(UserPurchaseOrzFilterDTO userPurchaseOrzFilterDTO) {
        if (ObjectUtil.isNull(userPurchaseOrzFilterDTO)) {
            userPurchaseOrzFilterDTO = new UserPurchaseOrzFilterDTO();
        }
        userPurchaseOrzFilterDTO.setDeleted(0);
        PageVO<UserPurchaseOrzVO> pageVO = new PageVO<>();

        if (userPurchaseOrzFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(userPurchaseOrzFilterDTO.getCurrentPage(), userPurchaseOrzFilterDTO.getPageSize(),
                    userPurchaseOrzFilterDTO.getOrderType(), userPurchaseOrzFilterDTO.getOrderField());
        }

        List<UserPurchaseOrz> userPurchaseOrzList = queryUserPurchaseOrzList(userPurchaseOrzFilterDTO);

        PageInfo<UserPurchaseOrz> pageInfo = new PageInfo<>(userPurchaseOrzList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(userPurchaseOrzList));

        return pageVO;
    }

    @Override
    public UserPurchaseOrzVO queryUserPurchaseOrzById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

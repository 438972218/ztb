package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserCompanyMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCompany;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCompanyDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCompanyFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCompanyVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserCompanyQuery;
import com.xdcplus.xdcweb.biz.generator.UserCompanyBaseService;
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
 * 用户公司中间表(UserCompany)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:29
 */
public class UserCompanyBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<UserCompany, UserCompanyVO, UserCompany, UserCompanyMapper> implements UserCompanyBaseService<S, T, E> {

    @Autowired
    protected UserCompanyMapper userCompanyMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveUserCompany(UserCompanyDTO userCompanyDTO) {

        UserCompany userCompany = userCompanyMapper.selectById(userCompanyDTO.getId());
        if (ObjectUtil.isNotNull(userCompany)) {
            log.error("saveUserCompany() The UserCompany already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        userCompany = new UserCompany();
        BeanUtil.copyProperties(userCompanyDTO, userCompany);
        userCompany.setCreatedTime(DateUtil.current());
        userCompany.setDeleted(0);

        return this.save(userCompany);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateUserCompany(UserCompanyDTO userCompanyDTO) {

        UserCompany userCompany = this.getById(userCompanyDTO.getId());
        if (ObjectUtil.isNull(userCompany)) {
            log.error("updateUserCompany() The UserCompany does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(userCompanyDTO, userCompany);
        userCompany.setUpdatedUser(userCompanyDTO.getUpdatedUser());
        userCompany.setUpdatedTime(DateUtil.current());

        return this.updateById(userCompany);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<UserCompany> userCompanyList) {

        userCompanyList.forEach(userCompany -> {
            UserCompany userCompanyParam = new UserCompany();
            userCompanyParam.setId(userCompany.getId());
            if (ObjectUtil.isNotNull(userCompany.getId())) {
                userCompany.setId(userCompany.getId());
                userCompany.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<UserCompany> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(UserCompany::getId, userCompany.getId());
                update(userCompany, lambdaUpdate);
            } else {
                userCompany.setCreatedTime(DateUtil.current());
                userCompany.setDeleted(0);
                save(userCompany);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<UserCompanyDTO> userCompanyDTOList) {

        List<UserCompany> userCompanyList = BeanUtils.copyProperties(userCompanyDTOList, UserCompany.class);
        return saveOrUpdateBatch(userCompanyList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteUserCompanyLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        UserCompany userCompany = this.getById(id);

        if (ObjectUtil.isNull(userCompany)) {
            log.error("deleteUserCompany() The UserCompany does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        userCompany.setDeleted(1);

        return this.updateById(userCompany);
    }

    private List<UserCompany> queryUserCompanyList(UserCompanyFilterDTO userCompanyFilterDTO) {
        if (ObjectUtil.isNull(userCompanyFilterDTO)) {
            userCompanyFilterDTO = new UserCompanyFilterDTO();
        }
        userCompanyFilterDTO.setDeleted(0);
        UserCompanyQuery userCompanyQuery = BeanUtil.copyProperties(userCompanyFilterDTO, UserCompanyQuery.class);

        return userCompanyMapper.queryUserCompany(userCompanyQuery);
    }

    @Override
    public List<UserCompanyVO> queryUserCompanyVOList(UserCompanyFilterDTO userCompanyFilterDTO) {
        if (ObjectUtil.isNull(userCompanyFilterDTO)) {
            userCompanyFilterDTO = new UserCompanyFilterDTO();
        }
        userCompanyFilterDTO.setDeleted(0);
        return this.objectConversion(queryUserCompanyList(userCompanyFilterDTO));
    }

    @Override
    public PageVO<UserCompanyVO> queryUserCompany(UserCompanyFilterDTO userCompanyFilterDTO) {
        if (ObjectUtil.isNull(userCompanyFilterDTO)) {
            userCompanyFilterDTO = new UserCompanyFilterDTO();
        }
        userCompanyFilterDTO.setDeleted(0);
        PageVO<UserCompanyVO> pageVO = new PageVO<>();

        if (userCompanyFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(userCompanyFilterDTO.getCurrentPage(), userCompanyFilterDTO.getPageSize(),
                    userCompanyFilterDTO.getOrderType(), userCompanyFilterDTO.getOrderField());
        }

        List<UserCompany> userCompanyList = queryUserCompanyList(userCompanyFilterDTO);

        PageInfo<UserCompany> pageInfo = new PageInfo<>(userCompanyList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(userCompanyList));

        return pageVO;
    }

    @Override
    public UserCompanyVO queryUserCompanyById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

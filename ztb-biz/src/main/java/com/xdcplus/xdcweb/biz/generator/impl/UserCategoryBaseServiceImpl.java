package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserCategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCategory;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCategoryVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserCategoryQuery;
import com.xdcplus.xdcweb.biz.generator.UserCategoryBaseService;
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
 * 用户品类中间表(UserCategory)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:29
 */
public class UserCategoryBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<UserCategory, UserCategoryVO, UserCategory, UserCategoryMapper> implements UserCategoryBaseService<S, T, E> {

    @Autowired
    protected UserCategoryMapper userCategoryMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveUserCategory(UserCategoryDTO userCategoryDTO) {

        UserCategory userCategory = userCategoryMapper.selectById(userCategoryDTO.getId());
        if (ObjectUtil.isNotNull(userCategory)) {
            log.error("saveUserCategory() The UserCategory already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        userCategory = new UserCategory();
        BeanUtil.copyProperties(userCategoryDTO, userCategory);
        userCategory.setCreatedTime(DateUtil.current());
        userCategory.setDeleted(0);

        return this.save(userCategory);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateUserCategory(UserCategoryDTO userCategoryDTO) {

        UserCategory userCategory = this.getById(userCategoryDTO.getId());
        if (ObjectUtil.isNull(userCategory)) {
            log.error("updateUserCategory() The UserCategory does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(userCategoryDTO, userCategory);
        userCategory.setUpdatedUser(userCategoryDTO.getUpdatedUser());
        userCategory.setUpdatedTime(DateUtil.current());

        return this.updateById(userCategory);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<UserCategory> userCategoryList) {

        userCategoryList.forEach(userCategory -> {
            UserCategory userCategoryParam = new UserCategory();
            userCategoryParam.setId(userCategory.getId());
            if (ObjectUtil.isNotNull(userCategory.getId())) {
                userCategory.setId(userCategory.getId());
                userCategory.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<UserCategory> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(UserCategory::getId, userCategory.getId());
                update(userCategory, lambdaUpdate);
            } else {
                userCategory.setCreatedTime(DateUtil.current());
                userCategory.setDeleted(0);
                save(userCategory);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<UserCategoryDTO> userCategoryDTOList) {

        List<UserCategory> userCategoryList = BeanUtils.copyProperties(userCategoryDTOList, UserCategory.class);
        return saveOrUpdateBatch(userCategoryList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteUserCategoryLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        UserCategory userCategory = this.getById(id);

        if (ObjectUtil.isNull(userCategory)) {
            log.error("deleteUserCategory() The UserCategory does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        userCategory.setDeleted(1);

        return this.updateById(userCategory);
    }

    private List<UserCategory> queryUserCategoryList(UserCategoryFilterDTO userCategoryFilterDTO) {
        if (ObjectUtil.isNull(userCategoryFilterDTO)) {
            userCategoryFilterDTO = new UserCategoryFilterDTO();
        }
        userCategoryFilterDTO.setDeleted(0);
        UserCategoryQuery userCategoryQuery = BeanUtil.copyProperties(userCategoryFilterDTO, UserCategoryQuery.class);

        return userCategoryMapper.queryUserCategory(userCategoryQuery);
    }

    @Override
    public List<UserCategoryVO> queryUserCategoryVOList(UserCategoryFilterDTO userCategoryFilterDTO) {
        if (ObjectUtil.isNull(userCategoryFilterDTO)) {
            userCategoryFilterDTO = new UserCategoryFilterDTO();
        }
        userCategoryFilterDTO.setDeleted(0);
        return this.objectConversion(queryUserCategoryList(userCategoryFilterDTO));
    }

    @Override
    public PageVO<UserCategoryVO> queryUserCategory(UserCategoryFilterDTO userCategoryFilterDTO) {
        if (ObjectUtil.isNull(userCategoryFilterDTO)) {
            userCategoryFilterDTO = new UserCategoryFilterDTO();
        }
        userCategoryFilterDTO.setDeleted(0);
        PageVO<UserCategoryVO> pageVO = new PageVO<>();

        if (userCategoryFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(userCategoryFilterDTO.getCurrentPage(), userCategoryFilterDTO.getPageSize(),
                    userCategoryFilterDTO.getOrderType(), userCategoryFilterDTO.getOrderField());
        }

        List<UserCategory> userCategoryList = queryUserCategoryList(userCategoryFilterDTO);

        PageInfo<UserCategory> pageInfo = new PageInfo<>(userCategoryList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(userCategoryList));

        return pageVO;
    }

    @Override
    public UserCategoryVO queryUserCategoryById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

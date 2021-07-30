package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.UserCategoryBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserCategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCategory;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCategoryVO;
import com.xdcplus.xdcweb.biz.service.UserCategoryService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户品类中间表(UserCategory)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:42
 */
@Slf4j
@Service("userCategoryService")
public class UserCategoryServiceImpl extends UserCategoryBaseServiceImpl<UserCategory, UserCategoryVO, UserCategory, UserCategoryMapper> implements UserCategoryService {


    @Override
    public int deleteUserCategoryByUserId(Long id) {
        return userCategoryMapper.delete(new QueryWrapper<UserCategory>().eq("user_id",id));
    }
}

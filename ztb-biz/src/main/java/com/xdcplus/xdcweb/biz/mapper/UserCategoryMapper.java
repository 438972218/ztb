package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCategory;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserCategoryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户品类中间表(UserCategory)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-20 11:06:52
 */
public interface UserCategoryMapper extends IBaseMapper<UserCategory> {

    /**
     * 查询用户品类中间表(UserCategory)
     *
     * @param userCategoryQuery 用户品类中间表(UserCategory)查询
     * @return {@link List<UserCategory>}
     */
    List<UserCategory> queryUserCategory(UserCategoryQuery userCategoryQuery);

}

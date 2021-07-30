package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserVendor;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserVendorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户供应商中间表(UserVendor)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-20 11:06:58
 */
public interface UserVendorMapper extends IBaseMapper<UserVendor> {

    /**
     * 查询用户供应商中间表(UserVendor)
     *
     * @param userVendorQuery 用户供应商中间表(UserVendor)查询
     * @return {@link List<UserVendor>}
     */
    List<UserVendor> queryUserVendor(UserVendorQuery userVendorQuery);

}

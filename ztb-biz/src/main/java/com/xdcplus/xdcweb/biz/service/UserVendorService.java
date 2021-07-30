package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.UserVendorBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserVendorVO;


/**
 * 用户供应商中间表(UserVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:45
 */
public interface UserVendorService extends UserVendorBaseService<UserVendor, UserVendorVO, UserVendor> {

    /**
     * 删除用户供应商中间表(UserVendor)
     *
     * @param id 用户表(UserVendor)主键
     * @return {@link Boolean} 是否成功
     */
    int deleteUserVendorByUserId(Long id);

}

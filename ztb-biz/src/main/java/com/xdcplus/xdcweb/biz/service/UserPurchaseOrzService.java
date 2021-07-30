package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.UserPurchaseOrzBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserPurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserPurchaseOrzVO;


/**
 * 用户采购组织中间表(UserPurchaseOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:44
 */
public interface UserPurchaseOrzService extends UserPurchaseOrzBaseService<UserPurchaseOrz, UserPurchaseOrzVO, UserPurchaseOrz> {

    /**
     * 删除用户采购组织中间表(UserPurchaseOrz)
     *
     * @param id 用户表(User)主键
     * @return {@link int} 是否成功
     */
    int deleteUserPurchaseOrzByUserId(Long id);

}

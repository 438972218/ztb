package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserPurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserPurchaseOrzQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户采购组织中间表(UserPurchaseOrz)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-20 11:06:55
 */
public interface UserPurchaseOrzMapper extends IBaseMapper<UserPurchaseOrz> {

    /**
     * 查询用户采购组织中间表(UserPurchaseOrz)
     *
     * @param userPurchaseOrzQuery 用户采购组织中间表(UserPurchaseOrz)查询
     * @return {@link List<UserPurchaseOrz>}
     */
    List<UserPurchaseOrz> queryUserPurchaseOrz(UserPurchaseOrzQuery userPurchaseOrzQuery);

}

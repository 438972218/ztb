package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiUser;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商绩效人员表(VendorKpiUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-22 09:48:10
 */
public interface VendorKpiUserMapper extends IBaseMapper<VendorKpiUser> {

    /**
     * 查询供应商绩效人员表(VendorKpiUser)
     *
     * @param vendorKpiUserQuery 供应商绩效人员表(VendorKpiUser)查询
     * @return {@link List<VendorKpiUser>}
     */
    List<VendorKpiUser> queryVendorKpiUser(VendorKpiUserQuery vendorKpiUserQuery);

}

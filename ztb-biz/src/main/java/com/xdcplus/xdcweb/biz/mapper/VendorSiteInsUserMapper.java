package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsUser;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商现场考察人员表(VendorSiteInsUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-20 15:47:32
 */
public interface VendorSiteInsUserMapper extends IBaseMapper<VendorSiteInsUser> {

    /**
     * 查询供应商现场考察人员表(VendorSiteInsUser)
     *
     * @param vendorSiteInsUserQuery 供应商现场考察人员表(VendorSiteInsUser)查询
     * @return {@link List<VendorSiteInsUser>}
     */
    List<VendorSiteInsUser> queryVendorSiteInsUser(VendorSiteInsUserQuery vendorSiteInsUserQuery);

}

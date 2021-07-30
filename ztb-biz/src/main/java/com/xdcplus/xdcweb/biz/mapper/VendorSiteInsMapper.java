package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteIns;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商现场考察表(VendorSiteIns)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-12 20:52:54
 */
public interface VendorSiteInsMapper extends IBaseMapper<VendorSiteIns> {

    /**
     * 查询供应商现场考察表(VendorSiteIns)
     *
     * @param vendorSiteInsQuery 供应商现场考察表(VendorSiteIns)查询
     * @return {@link List<VendorSiteIns>}
     */
    List<VendorSiteIns> queryVendorSiteIns(VendorSiteInsQuery vendorSiteInsQuery);

}

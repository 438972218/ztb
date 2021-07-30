package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpi;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商绩效考核表(VendorKpi)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-12 20:52:50
 */
public interface VendorKpiMapper extends IBaseMapper<VendorKpi> {

    /**
     * 查询供应商绩效考核表(VendorKpi)
     *
     * @param vendorKpiQuery 供应商绩效考核表(VendorKpi)查询
     * @return {@link List<VendorKpi>}
     */
    List<VendorKpi> queryVendorKpi(VendorKpiQuery vendorKpiQuery);

}

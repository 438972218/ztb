package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetail;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商绩效考核表明细(VendorKpiDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-15 10:53:33
 */
public interface VendorKpiDetailMapper extends IBaseMapper<VendorKpiDetail> {

    /**
     * 查询供应商绩效考核表明细(VendorKpiDetail)
     *
     * @param vendorKpiDetailQuery 供应商绩效考核表明细(VendorKpiDetail)查询
     * @return {@link List<VendorKpiDetail>}
     */
    List<VendorKpiDetail> queryVendorKpiDetail(VendorKpiDetailQuery vendorKpiDetailQuery);

}

package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetail;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商现场考察表明细(VendorSiteInsDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-15 10:53:32
 */
public interface VendorSiteInsDetailMapper extends IBaseMapper<VendorSiteInsDetail> {

    /**
     * 查询供应商现场考察表明细(VendorSiteInsDetail)
     *
     * @param vendorSiteInsDetailQuery 供应商现场考察表明细(VendorSiteInsDetail)查询
     * @return {@link List<VendorSiteInsDetail>}
     */
    List<VendorSiteInsDetail> queryVendorSiteInsDetail(VendorSiteInsDetailQuery vendorSiteInsDetailQuery);

}

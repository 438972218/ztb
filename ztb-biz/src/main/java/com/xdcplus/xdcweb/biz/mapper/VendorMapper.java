package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Vendor;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商(Vendor)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-12 20:52:48
 */
public interface VendorMapper extends IBaseMapper<Vendor> {

    /**
     * 查询供应商(Vendor)
     *
     * @param vendorQuery 供应商(Vendor)查询
     * @return {@link List<Vendor>}
     */
    List<Vendor> queryVendor(VendorQuery vendorQuery);

}

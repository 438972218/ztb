package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCategory;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorRequestCategoryQuery;

import java.util.List;

/**
 * 供应商注册单品类(VendorRequestCategory)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-26 15:47:58
 */
public interface VendorRequestCategoryMapper extends IBaseMapper<VendorRequestCategory> {

    /**
     * 查询供应商注册单品类(VendorRequestCategory)
     *
     * @param vendorRequestCategoryQuery 供应商注册单品类(VendorRequestCategory)查询
     * @return {@link List<VendorRequestCategory>}
     */
    List<VendorRequestCategory> queryVendorRequestCategory(VendorRequestCategoryQuery vendorRequestCategoryQuery);

}

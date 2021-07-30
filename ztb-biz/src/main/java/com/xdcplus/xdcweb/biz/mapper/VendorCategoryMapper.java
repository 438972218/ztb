package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCategory;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorCategoryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商品类(VendorCategory)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-15 10:01:10
 */
public interface VendorCategoryMapper extends IBaseMapper<VendorCategory> {

    /**
     * 查询供应商品类(VendorCategory)
     *
     * @param vendorCategoryQuery 供应商品类(VendorCategory)查询
     * @return {@link List<VendorCategory>}
     */
    List<VendorCategory> queryVendorCategory(VendorCategoryQuery vendorCategoryQuery);

}

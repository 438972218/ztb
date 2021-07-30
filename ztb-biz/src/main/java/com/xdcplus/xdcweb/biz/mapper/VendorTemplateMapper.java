package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplate;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorTemplateQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板表(VendorTemplate)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-15 10:54:09
 */
public interface VendorTemplateMapper extends IBaseMapper<VendorTemplate> {

    /**
     * 查询模板表(VendorTemplate)
     *
     * @param vendorTemplateQuery 模板表(VendorTemplate)查询
     * @return {@link List<VendorTemplate>}
     */
    List<VendorTemplate> queryVendorTemplate(VendorTemplateQuery vendorTemplateQuery);

}

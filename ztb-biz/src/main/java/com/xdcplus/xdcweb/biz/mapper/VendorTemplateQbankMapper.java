package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplateQbank;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorTemplateQbankQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板题库中间表(VendorTemplateQbank)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-15 10:16:46
 */
public interface VendorTemplateQbankMapper extends IBaseMapper<VendorTemplateQbank> {

    /**
     * 查询模板题库中间表(VendorTemplateQbank)
     *
     * @param vendorTemplateQbankQuery 模板题库中间表(VendorTemplateQbank)查询
     * @return {@link List<VendorTemplateQbank>}
     */
    List<VendorTemplateQbank> queryVendorTemplateQbank(VendorTemplateQbankQuery vendorTemplateQbankQuery);

}

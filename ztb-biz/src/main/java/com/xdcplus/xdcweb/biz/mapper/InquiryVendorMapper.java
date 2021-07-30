package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendor;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 询价供应商(InquiryVendor)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-08 10:48:03
 */
public interface InquiryVendorMapper extends IBaseMapper<InquiryVendor> {

    /**
     * 查询询价供应商(InquiryVendor)
     *
     * @param inquiryVendorQuery 询价供应商(InquiryVendor)查询
     * @return {@link List<InquiryVendor>}
     */
    List<InquiryVendor> queryInquiryVendor(InquiryVendorQuery inquiryVendorQuery);

}

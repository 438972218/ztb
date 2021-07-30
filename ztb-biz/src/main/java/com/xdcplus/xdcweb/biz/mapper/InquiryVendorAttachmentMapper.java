package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 询价供应商附件(InquiryVendorAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:20
 */
public interface InquiryVendorAttachmentMapper extends IBaseMapper<InquiryVendorAttachment> {

    /**
     * 查询询价供应商附件(InquiryVendorAttachment)
     *
     * @param inquiryVendorAttachmentQuery 询价供应商附件(InquiryVendorAttachment)查询
     * @return {@link List<InquiryVendorAttachment>}
     */
    List<InquiryVendorAttachment> queryInquiryVendorAttachment(InquiryVendorAttachmentQuery inquiryVendorAttachmentQuery);

}

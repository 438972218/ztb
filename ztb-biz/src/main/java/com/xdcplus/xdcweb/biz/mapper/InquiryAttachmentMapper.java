package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 询价单附件(InquiryAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-05 09:59:33
 */
public interface InquiryAttachmentMapper extends IBaseMapper<InquiryAttachment> {

    /**
     * 查询询价单附件(InquiryAttachment)
     *
     * @param inquiryAttachmentQuery 询价单附件(InquiryAttachment)查询
     * @return {@link List<InquiryAttachment>}
     */
    List<InquiryAttachment> queryInquiryAttachment(InquiryAttachmentQuery inquiryAttachmentQuery);

}

package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.InquiryVendorAttachmentBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryVendorAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.service.InquiryVendorAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 询价供应商附件(InquiryVendorAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:19
 */
@Slf4j
@Service("inquiryVendorAttachmentService")
public class InquiryVendorAttachmentServiceImpl extends InquiryVendorAttachmentBaseServiceImpl<InquiryVendorAttachment, InquiryVendorAttachmentVO, InquiryVendorAttachment, InquiryVendorAttachmentMapper> implements InquiryVendorAttachmentService {


}

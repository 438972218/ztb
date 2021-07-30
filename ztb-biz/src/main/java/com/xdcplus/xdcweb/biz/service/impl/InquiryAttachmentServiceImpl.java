package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.InquiryAttachmentBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryAttachmentVO;
import com.xdcplus.xdcweb.biz.service.InquiryAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 询价单附件(InquiryAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:01
 */
@Slf4j
@Service("inquiryAttachmentService")
public class InquiryAttachmentServiceImpl extends InquiryAttachmentBaseServiceImpl<InquiryAttachment, InquiryAttachmentVO, InquiryAttachment, InquiryAttachmentMapper> implements InquiryAttachmentService {


}

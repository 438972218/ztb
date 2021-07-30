package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.PaidVendorAttachmentBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidVendorAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.service.PaidVendorAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 竞价供应商附件(PaidVendorAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:47
 */
@Slf4j
@Service("paidVendorAttachmentService")
public class PaidVendorAttachmentServiceImpl extends PaidVendorAttachmentBaseServiceImpl<PaidVendorAttachment, PaidVendorAttachmentVO, PaidVendorAttachment, PaidVendorAttachmentMapper> implements PaidVendorAttachmentService {


}

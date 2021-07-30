package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.BidVendorAttachmentBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidVendorAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.service.BidVendorAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 招标投标方附件(BidVendorAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:10
 */
@Slf4j
@Service("bidVendorAttachmentService")
public class BidVendorAttachmentServiceImpl extends BidVendorAttachmentBaseServiceImpl<BidVendorAttachment, BidVendorAttachmentVO, BidVendorAttachment, BidVendorAttachmentMapper> implements BidVendorAttachmentService {


}

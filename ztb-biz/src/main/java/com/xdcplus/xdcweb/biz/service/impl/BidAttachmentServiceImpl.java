package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.BidAttachmentBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidAttachmentVO;
import com.xdcplus.xdcweb.biz.service.BidAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 招标附件(BidAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:00
 */
@Slf4j
@Service("bidAttachmentService")
public class BidAttachmentServiceImpl extends BidAttachmentBaseServiceImpl<BidAttachment, BidAttachmentVO, BidAttachment, BidAttachmentMapper> implements BidAttachmentService {


}

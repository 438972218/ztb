package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.PaidAttachmentBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidAttachmentVO;
import com.xdcplus.xdcweb.biz.service.PaidAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 竞价单附件(PaidAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:40
 */
@Slf4j
@Service("paidAttachmentService")
public class PaidAttachmentServiceImpl extends PaidAttachmentBaseServiceImpl<PaidAttachment, PaidAttachmentVO, PaidAttachment, PaidAttachmentMapper> implements PaidAttachmentService {


}

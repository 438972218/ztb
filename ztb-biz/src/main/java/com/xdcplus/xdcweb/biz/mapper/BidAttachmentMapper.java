package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标附件(BidAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:16:50
 */
public interface BidAttachmentMapper extends IBaseMapper<BidAttachment> {

    /**
     * 查询招标附件(BidAttachment)
     *
     * @param bidAttachmentQuery 招标附件(BidAttachment)查询
     * @return {@link List<BidAttachment>}
     */
    List<BidAttachment> queryBidAttachment(BidAttachmentQuery bidAttachmentQuery);

}

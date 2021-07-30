package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标投标方附件(BidVendorAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:02
 */
public interface BidVendorAttachmentMapper extends IBaseMapper<BidVendorAttachment> {

    /**
     * 查询招标投标方附件(BidVendorAttachment)
     *
     * @param bidVendorAttachmentQuery 招标投标方附件(BidVendorAttachment)查询
     * @return {@link List<BidVendorAttachment>}
     */
    List<BidVendorAttachment> queryBidVendorAttachment(BidVendorAttachmentQuery bidVendorAttachmentQuery);

}

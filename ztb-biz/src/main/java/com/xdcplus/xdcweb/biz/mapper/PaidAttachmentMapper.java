package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价单附件(PaidAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-05 09:59:36
 */
public interface PaidAttachmentMapper extends IBaseMapper<PaidAttachment> {

    /**
     * 查询竞价单附件(PaidAttachment)
     *
     * @param paidAttachmentQuery 竞价单附件(PaidAttachment)查询
     * @return {@link List<PaidAttachment>}
     */
    List<PaidAttachment> queryPaidAttachment(PaidAttachmentQuery paidAttachmentQuery);

}

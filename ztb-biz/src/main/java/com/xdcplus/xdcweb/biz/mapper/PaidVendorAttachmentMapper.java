package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价供应商附件(PaidVendorAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:32
 */
public interface PaidVendorAttachmentMapper extends IBaseMapper<PaidVendorAttachment> {

    /**
     * 查询竞价供应商附件(PaidVendorAttachment)
     *
     * @param paidVendorAttachmentQuery 竞价供应商附件(PaidVendorAttachment)查询
     * @return {@link List<PaidVendorAttachment>}
     */
    List<PaidVendorAttachment> queryPaidVendorAttachment(PaidVendorAttachmentQuery paidVendorAttachmentQuery);

}

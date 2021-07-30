package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReviewDetail;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQualifyReviewDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-12 20:52:52
 */
public interface VendorQualifyReviewDetailMapper extends IBaseMapper<VendorQualifyReviewDetail> {

    /**
     * 查询供应商合格评审表明细(VendorQualifyReviewDetail)
     *
     * @param vendorQualifyReviewDetailQuery 供应商合格评审表明细(VendorQualifyReviewDetail)查询
     * @return {@link List<VendorQualifyReviewDetail>}
     */
    List<VendorQualifyReviewDetail> queryVendorQualifyReviewDetail(VendorQualifyReviewDetailQuery vendorQualifyReviewDetailQuery);

}

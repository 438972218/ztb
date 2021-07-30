package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReview;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQualifyReviewQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商合格评审表(VendorQualifyReview)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-12 20:52:52
 */
public interface VendorQualifyReviewMapper extends IBaseMapper<VendorQualifyReview> {

    /**
     * 查询供应商合格评审表(VendorQualifyReview)
     *
     * @param vendorQualifyReviewQuery 供应商合格评审表(VendorQualifyReview)查询
     * @return {@link List<VendorQualifyReview>}
     */
    List<VendorQualifyReview> queryVendorQualifyReview(VendorQualifyReviewQuery vendorQualifyReviewQuery);

}

package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorQualifyReviewDetailBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReviewDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewDetailVO;


/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:55
 */
public interface VendorQualifyReviewDetailService extends VendorQualifyReviewDetailBaseService<VendorQualifyReviewDetail, VendorQualifyReviewDetailVO, VendorQualifyReviewDetail> {

    /**
     * 删除供应商资格审查由包装细节
     *
     * @param id
     * @return {@link int}
     */
    int deleteVendorQualifyReviewDetailByReviewId(Long id);

}

package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorQualifyReviewDetailBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQualifyReviewDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReviewDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewDetailVO;
import com.xdcplus.xdcweb.biz.service.VendorQualifyReviewDetailService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:55
 */
@Slf4j
@Service("vendorQualifyReviewDetailService")
public class VendorQualifyReviewDetailServiceImpl extends VendorQualifyReviewDetailBaseServiceImpl<VendorQualifyReviewDetail, VendorQualifyReviewDetailVO, VendorQualifyReviewDetail, VendorQualifyReviewDetailMapper> implements VendorQualifyReviewDetailService {


    @Override
    public int deleteVendorQualifyReviewDetailByReviewId(Long id) {
        return vendorQualifyReviewDetailMapper.delete(new QueryWrapper<VendorQualifyReviewDetail>().eq("vendor_qualify_review_id", id));

    }
}

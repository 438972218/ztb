package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewFilterDTO;
import com.xdcplus.xdcweb.biz.generator.VendorQualifyReviewBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReview;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewVO;


/**
 * 供应商合格评审表(VendorQualifyReview)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:52
 */
public interface VendorQualifyReviewService extends VendorQualifyReviewBaseService<VendorQualifyReview, VendorQualifyReviewVO, VendorQualifyReview> {

    /**
     * 添加供应商合格评审表返回VO(VendorQualifyReview)
     *
     * @param vendorQualifyReviewDTO 供应商合格评审表(VendorQualifyReviewDTO)
     * @return {@link Boolean} 是否成功
     */
    VendorQualifyReviewVO saveVendorQualifyReviewReturnVO(VendorQualifyReviewDTO vendorQualifyReviewDTO);

    /**
     * 修改供应商合格评审表以及detail(VendorQualifyReview)
     *
     * @param vendorQualifyReviewDTO 供应商合格评审表(VendorQualifyReviewDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorQualifyReviewWithDetail(VendorQualifyReviewDTO vendorQualifyReviewDTO);

    /**
     * 查询一个并带出附表中数据
     *
     * @param id 供应商合格评审表(VendorQualifyReview)主键
     * @return {@link VendorQualifyReviewVO} 供应商合格评审表(VendorQualifyReview)信息
     */
    VendorQualifyReviewVO showVendorQualifyReview(Long id);


    /**
     * 查询供应商合格评审表(VendorQuestion)
     *
     * @param vendorQualifyReviewFilterDTO 过滤DTO
     * @return {@link PageVO <VendorQualifyReviewVO>} 状态信息
     */
    PageVO<VendorQualifyReviewVO> queryVendorQualifyReviewWithRequest(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO);

}

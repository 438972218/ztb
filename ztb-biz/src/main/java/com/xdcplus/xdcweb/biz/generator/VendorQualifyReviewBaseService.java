package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReview;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewVO;

import java.util.List;

/**
 * 供应商合格评审表(VendorQualifyReview)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:50
 */
public interface VendorQualifyReviewBaseService<S, T, E> extends BaseService<VendorQualifyReview, VendorQualifyReviewVO, VendorQualifyReview> {

    /**
     * 添加供应商合格评审表(VendorQualifyReview)
     *
     * @param vendorQualifyReviewDTO 供应商合格评审表(VendorQualifyReviewDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorQualifyReview(VendorQualifyReviewDTO vendorQualifyReviewDTO);

    /**
     * 修改供应商合格评审表(VendorQualifyReview)
     *
     * @param vendorQualifyReviewDTO 供应商合格评审表(VendorQualifyReviewDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorQualifyReview(VendorQualifyReviewDTO vendorQualifyReviewDTO);

    /**
     * 批量保存或更新供应商合格评审表(VendorQualifyReview)
     *
     * @param vendorQualifyReviewList 供应商合格评审表(VendorQualifyReviewList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorQualifyReview> vendorQualifyReviewList);

    /**
     * 批量保存或更新供应商合格评审表(VendorQualifyReviewDTOList)
     *
     * @param vendorQualifyReviewDTOList 供应商合格评审表(VendorQualifyReviewDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorQualifyReviewDTO> vendorQualifyReviewDTOList);

    /**
     * 删除供应商合格评审表(VendorQualifyReview)
     *
     * @param id 供应商合格评审表(VendorQualifyReview)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorQualifyReviewLogical(Long id);

    /**
     * 查询供应商合格评审表(VendorQualifyReview)
     *
     * @param vendorQualifyReviewFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQualifyReviewVO>} 状态信息
     */
    List<VendorQualifyReviewVO> queryVendorQualifyReviewVOList(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO);

    /**
     * 查询供应商合格评审表(VendorQualifyReview)
     *
     * @param vendorQualifyReviewFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQualifyReviewVO>} 状态信息
     */
    PageVO<VendorQualifyReviewVO> queryVendorQualifyReview(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商合格评审表(VendorQualifyReview)主键
     * @return {@link VendorQualifyReviewVO} 供应商合格评审表(VendorQualifyReview)信息
     */
    VendorQualifyReviewVO queryVendorQualifyReviewById(Long id);


}

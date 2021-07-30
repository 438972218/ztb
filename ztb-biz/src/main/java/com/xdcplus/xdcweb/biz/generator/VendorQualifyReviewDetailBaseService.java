package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReviewDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewDetailVO;

import java.util.List;

/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:52
 */
public interface VendorQualifyReviewDetailBaseService<S, T, E> extends BaseService<VendorQualifyReviewDetail, VendorQualifyReviewDetailVO, VendorQualifyReviewDetail> {

    /**
     * 添加供应商合格评审表明细(VendorQualifyReviewDetail)
     *
     * @param vendorQualifyReviewDetailDTO 供应商合格评审表明细(VendorQualifyReviewDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorQualifyReviewDetail(VendorQualifyReviewDetailDTO vendorQualifyReviewDetailDTO);

    /**
     * 修改供应商合格评审表明细(VendorQualifyReviewDetail)
     *
     * @param vendorQualifyReviewDetailDTO 供应商合格评审表明细(VendorQualifyReviewDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorQualifyReviewDetail(VendorQualifyReviewDetailDTO vendorQualifyReviewDetailDTO);

    /**
     * 批量保存或更新供应商合格评审表明细(VendorQualifyReviewDetail)
     *
     * @param vendorQualifyReviewDetailList 供应商合格评审表明细(VendorQualifyReviewDetailList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorQualifyReviewDetail> vendorQualifyReviewDetailList);

    /**
     * 批量保存或更新供应商合格评审表明细(VendorQualifyReviewDetailDTOList)
     *
     * @param vendorQualifyReviewDetailDTOList 供应商合格评审表明细(VendorQualifyReviewDetailDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorQualifyReviewDetailDTO> vendorQualifyReviewDetailDTOList);

    /**
     * 删除供应商合格评审表明细(VendorQualifyReviewDetail)
     *
     * @param id 供应商合格评审表明细(VendorQualifyReviewDetail)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorQualifyReviewDetailLogical(Long id);

    /**
     * 查询供应商合格评审表明细(VendorQualifyReviewDetail)
     *
     * @param vendorQualifyReviewDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQualifyReviewDetailVO>} 状态信息
     */
    List<VendorQualifyReviewDetailVO> queryVendorQualifyReviewDetailVOList(VendorQualifyReviewDetailFilterDTO vendorQualifyReviewDetailFilterDTO);

    /**
     * 查询供应商合格评审表明细(VendorQualifyReviewDetail)
     *
     * @param vendorQualifyReviewDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQualifyReviewDetailVO>} 状态信息
     */
    PageVO<VendorQualifyReviewDetailVO> queryVendorQualifyReviewDetail(VendorQualifyReviewDetailFilterDTO vendorQualifyReviewDetailFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商合格评审表明细(VendorQualifyReviewDetail)主键
     * @return {@link VendorQualifyReviewDetailVO} 供应商合格评审表明细(VendorQualifyReviewDetail)信息
     */
    VendorQualifyReviewDetailVO queryVendorQualifyReviewDetailById(Long id);


}

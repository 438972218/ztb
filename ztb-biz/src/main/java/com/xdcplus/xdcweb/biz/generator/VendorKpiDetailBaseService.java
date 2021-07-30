package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailVO;

import java.util.List;

/**
 * 供应商绩效考核表明细(VendorKpiDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:42
 */
public interface VendorKpiDetailBaseService<S, T, E> extends BaseService<VendorKpiDetail, VendorKpiDetailVO, VendorKpiDetail> {

    /**
     * 添加供应商绩效考核表明细(VendorKpiDetail)
     *
     * @param vendorKpiDetailDTO 供应商绩效考核表明细(VendorKpiDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorKpiDetail(VendorKpiDetailDTO vendorKpiDetailDTO);

    /**
     * 修改供应商绩效考核表明细(VendorKpiDetail)
     *
     * @param vendorKpiDetailDTO 供应商绩效考核表明细(VendorKpiDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorKpiDetail(VendorKpiDetailDTO vendorKpiDetailDTO);

    /**
     * 批量保存或更新供应商绩效考核表明细(VendorKpiDetail)
     *
     * @param vendorKpiDetailList 供应商绩效考核表明细(VendorKpiDetailList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorKpiDetail> vendorKpiDetailList);

    /**
     * 批量保存或更新供应商绩效考核表明细(VendorKpiDetailDTOList)
     *
     * @param vendorKpiDetailDTOList 供应商绩效考核表明细(VendorKpiDetailDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorKpiDetailDTO> vendorKpiDetailDTOList);

    /**
     * 删除供应商绩效考核表明细(VendorKpiDetail)
     *
     * @param id 供应商绩效考核表明细(VendorKpiDetail)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorKpiDetailLogical(Long id);

    /**
     * 查询供应商绩效考核表明细(VendorKpiDetail)
     *
     * @param vendorKpiDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiDetailVO>} 状态信息
     */
    List<VendorKpiDetailVO> queryVendorKpiDetailVOList(VendorKpiDetailFilterDTO vendorKpiDetailFilterDTO);

    /**
     * 查询供应商绩效考核表明细(VendorKpiDetail)
     *
     * @param vendorKpiDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiDetailVO>} 状态信息
     */
    PageVO<VendorKpiDetailVO> queryVendorKpiDetail(VendorKpiDetailFilterDTO vendorKpiDetailFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商绩效考核表明细(VendorKpiDetail)主键
     * @return {@link VendorKpiDetailVO} 供应商绩效考核表明细(VendorKpiDetail)信息
     */
    VendorKpiDetailVO queryVendorKpiDetailById(Long id);


}

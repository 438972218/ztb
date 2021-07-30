package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorMaterialVO;

import java.util.List;

/**
 * 询价供应商物品(InquiryVendorMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:19
 */
public interface InquiryVendorMaterialBaseService<S, T, E> extends BaseService<InquiryVendorMaterial, InquiryVendorMaterialVO, InquiryVendorMaterial> {

    /**
     * 添加询价供应商物品(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialDTO 询价供应商物品(InquiryVendorMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveInquiryVendorMaterial(InquiryVendorMaterialDTO inquiryVendorMaterialDTO);

    /**
     * 修改询价供应商物品(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialDTO 询价供应商物品(InquiryVendorMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInquiryVendorMaterial(InquiryVendorMaterialDTO inquiryVendorMaterialDTO);

    /**
     * 批量保存或更新询价供应商物品(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialList 询价供应商物品(InquiryVendorMaterialList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<InquiryVendorMaterial> inquiryVendorMaterialList);

    /**
     * 批量保存或更新询价供应商物品(InquiryVendorMaterialDTOList)
     *
     * @param inquiryVendorMaterialDTOList 询价供应商物品(InquiryVendorMaterialDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<InquiryVendorMaterialDTO> inquiryVendorMaterialDTOList);

    /**
     * 删除询价供应商物品(InquiryVendorMaterial)
     *
     * @param id 询价供应商物品(InquiryVendorMaterial)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteInquiryVendorMaterialLogical(Long id);

    /**
     * 查询询价供应商物品(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryVendorMaterialVO>} 状态信息
     */
    List<InquiryVendorMaterialVO> queryInquiryVendorMaterialVOList(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO);

    /**
     * 查询询价供应商物品(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryVendorMaterialVO>} 状态信息
     */
    PageVO<InquiryVendorMaterialVO> queryInquiryVendorMaterial(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO);

    /**
     * 查询一个
     *
     * @param id 询价供应商物品(InquiryVendorMaterial)主键
     * @return {@link InquiryVendorMaterialVO} 询价供应商物品(InquiryVendorMaterial)信息
     */
    InquiryVendorMaterialVO queryInquiryVendorMaterialById(Long id);


}

package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryMaterialVO;

import java.util.List;

/**
 * 询价物品(InquiryMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:01
 */
public interface InquiryMaterialBaseService<S, T, E> extends BaseService<InquiryMaterial, InquiryMaterialVO, InquiryMaterial> {

    /**
     * 添加询价物品(InquiryMaterial)
     *
     * @param inquiryMaterialDTO 询价物品(InquiryMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveInquiryMaterial(InquiryMaterialDTO inquiryMaterialDTO);

    /**
     * 修改询价物品(InquiryMaterial)
     *
     * @param inquiryMaterialDTO 询价物品(InquiryMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInquiryMaterial(InquiryMaterialDTO inquiryMaterialDTO);

    /**
     * 批量保存或更新询价物品(InquiryMaterial)
     *
     * @param inquiryMaterialList 询价物品(InquiryMaterialList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<InquiryMaterial> inquiryMaterialList);

    /**
     * 批量保存或更新询价物品(InquiryMaterialDTOList)
     *
     * @param inquiryMaterialDTOList 询价物品(InquiryMaterialDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<InquiryMaterialDTO> inquiryMaterialDTOList);

    /**
     * 删除询价物品(InquiryMaterial)
     *
     * @param id 询价物品(InquiryMaterial)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteInquiryMaterialLogical(Long id);

    /**
     * 查询询价物品(InquiryMaterial)
     *
     * @param inquiryMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryMaterialVO>} 状态信息
     */
    List<InquiryMaterialVO> queryInquiryMaterialVOList(InquiryMaterialFilterDTO inquiryMaterialFilterDTO);

    /**
     * 查询询价物品(InquiryMaterial)
     *
     * @param inquiryMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryMaterialVO>} 状态信息
     */
    PageVO<InquiryMaterialVO> queryInquiryMaterial(InquiryMaterialFilterDTO inquiryMaterialFilterDTO);

    /**
     * 查询一个
     *
     * @param id 询价物品(InquiryMaterial)主键
     * @return {@link InquiryMaterialVO} 询价物品(InquiryMaterial)信息
     */
    InquiryMaterialVO queryInquiryMaterialById(Long id);


}

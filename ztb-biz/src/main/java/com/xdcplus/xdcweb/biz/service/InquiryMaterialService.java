package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.generator.InquiryMaterialBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryMaterialVO;

import java.util.List;


/**
 * 询价物品(InquiryMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:05
 */
public interface InquiryMaterialService extends InquiryMaterialBaseService<InquiryMaterial, InquiryMaterialVO, InquiryMaterial> {

    /**
     * 查询询价物品(还比价)(InquiryMaterial)
     *
     * @param inquiryMaterialFilterDTO 过程状态过滤DTO
     * @return {@link List <InquiryMaterialVO>} 状态信息
     */
    List<InquiryMaterialVO> queryInquiryMaterialsWithDicker(InquiryMaterialFilterDTO inquiryMaterialFilterDTO);

    /**
     * 查询询价物品(核价)(InquiryMaterial)
     *
     * @param inquiryMaterialFilterDTO 过程状态过滤DTO
     * @return {@link List<InquiryMaterialVO>} 状态信息
     */
    List<InquiryMaterialVO> queryInquiryMaterialsWithPricing(InquiryMaterialFilterDTO inquiryMaterialFilterDTO);

    /**
     * 查询询价物品(供应商)(InquiryMaterial)
     *
     * @param inquiryMaterialFilterDTO 过程状态过滤DTO
     * @return {@link List<InquiryMaterialVO>} 状态信息
     */
    List<InquiryMaterialVO> queryInquiryMaterialsWithVendor(InquiryMaterialFilterDTO inquiryMaterialFilterDTO);


}

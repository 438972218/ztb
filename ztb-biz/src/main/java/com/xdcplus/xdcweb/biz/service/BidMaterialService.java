package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.generator.BidMaterialBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;

import java.util.List;


/**
 * 招标物品(BidMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:01
 */
public interface BidMaterialService extends BidMaterialBaseService<BidMaterial, BidMaterialVO, BidMaterial> {

    /**
     * 查询招标物品(评标与定标)(BidMaterial)
     *
     * @param bidMaterialFilterDTO 过程状态过滤DTO
     * @return {@link List < BidMaterialVO >} 状态信息
     */
    List<BidMaterialVO> queryBidMaterialsWithPricing(BidMaterialFilterDTO bidMaterialFilterDTO);

    /**
     * 查询招标物品(供应商)(BidMaterial)
     *
     * @param bidMaterialFilterDTO 过程状态过滤DTO
     * @return {@link List<BidMaterialVO>} 状态信息
     */
    List<BidMaterialVO> queryBidMaterialsWithVendor(BidMaterialFilterDTO bidMaterialFilterDTO);


}

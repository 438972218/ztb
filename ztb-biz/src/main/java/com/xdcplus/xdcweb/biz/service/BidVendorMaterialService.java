package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.BidVendorMaterialBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorMaterialVO;

import java.util.List;


/**
 * 招标投标方物品(BidVendorMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:12
 */
public interface BidVendorMaterialService extends BidVendorMaterialBaseService<BidVendorMaterial, BidVendorMaterialVO, BidVendorMaterial> {

    /**
     * desc查询调查供应商的材料
     *
     * @param bidVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link List < BidVendorMaterial >}
     */
    List<BidVendorMaterial> queryBidVendorMaterialsDesc(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO);

    /**
     * 得到报价次数
     *
     * @param bidVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link Integer}
     */
    Integer getOfferingNumber(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO);

    /**
     * 查询页面vodesc
     *
     * @param bidVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link PageVO < BidVendorMaterialVO >}
     */
    PageVO<BidVendorMaterialVO> queryPageVODesc(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO);

    /**
     * 根据物品id查询供应商最新报价(BidVendorMaterial)
     *
     * @param bidVendorMaterialQuery 招标供应商物品(BidVendorMaterialQuery)查询
     * @return {@link List<BidVendorMaterialVO>}
     */
    List<BidVendorMaterialVO> queryNewBidVendorMaterialByMaterialId(BidVendorMaterialQuery bidVendorMaterialQuery);

    /**
     * 根据供应商id查询供应商所有物品的总价(BidVendorMaterial)
     *
     * @param bidVendorMaterialQuery 供应商物品(BidVendorMaterial)查询
     * @return {@link Double}
     */
    Double getTotalPriceByBidVendorId(BidVendorMaterialQuery bidVendorMaterialQuery);

    /**
     * 招标单供应商提交报价(BidVendorMaterial)
     *
     * @param bidVendorMaterialDTO 招标供应商物品(BidVendorMaterial)
     * @return {@link }
     */
    Boolean submitOffer(BidVendorMaterialDTO bidVendorMaterialDTO);

    /**
     * @param bidVendorMaterialDTO 供应商物品(BidVendorMaterial)查询
     * @return {@link List<  BidVendorMaterialVO  >}
     */
    List<BidVendorMaterialVO> queryAllocatedRanking(BidVendorMaterialDTO bidVendorMaterialDTO);


}

package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标投标方物品(BidVendorMaterial)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:06
 */
public interface BidVendorMaterialMapper extends IBaseMapper<BidVendorMaterial> {

    /**
     * 查询招标投标方物品(BidVendorMaterial)
     *
     * @param bidVendorMaterialQuery 招标投标方物品(BidVendorMaterial)查询
     * @return {@link List<BidVendorMaterial>}
     */
    List<BidVendorMaterial> queryBidVendorMaterial(BidVendorMaterialQuery bidVendorMaterialQuery);

    /**
     * 根据物品id查询供应商最新报价(BidVendorMaterial)
     *
     * @param bidVendorMaterialQuery 询价供应商物品(BidVendorMaterial)查询
     * @return {@link List< BidVendorMaterial >}
     */
    List<BidVendorMaterial> queryNewBidVendorMaterialByMaterialId(BidVendorMaterialQuery bidVendorMaterialQuery);

    /**
     * 根据供应商id查询供应商所有物品的总价(BidVendorMaterial)
     *
     * @param bidVendorMaterialQuery 供应商物品(BidVendorMaterial)查询
     * @return {@link Double}
     */
    Double getTotalPriceByBidVendorId(BidVendorMaterialQuery bidVendorMaterialQuery);

    /**
     * 根据sheetid查询供应商保存状态的报价(BidVendorMaterial)
     *
     * @param id
     * @return {@link List<BidVendorMaterial>}
     */
    List<BidVendorMaterial> querySaveBidVendorMaterialBySheetId(Long id);

}

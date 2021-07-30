package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 询价供应商物品(InquiryVendorMaterial)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:25
 */
public interface InquiryVendorMaterialMapper extends IBaseMapper<InquiryVendorMaterial> {

    /**
     * 查询询价供应商物品(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialQuery 询价供应商物品(InquiryVendorMaterial)查询
     * @return {@link List<InquiryVendorMaterial>}
     */
    List<InquiryVendorMaterial> queryInquiryVendorMaterial(InquiryVendorMaterialQuery inquiryVendorMaterialQuery);

    /**
     * 根据物品id查询供应商最新报价(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialQuery 询价供应商物品(InquiryVendorMaterial)查询
     * @return {@link List<InquiryVendorMaterial>}
     */
    List<InquiryVendorMaterial> queryNewInquiryVendorMaterialByMaterialId(InquiryVendorMaterialQuery inquiryVendorMaterialQuery);

    /**
     * 根据sheetid查询供应商保存状态的报价(InquiryVendorMaterial)
     *
     * @param id
     * @return {@link List<InquiryVendorMaterial>}
     */
    List<InquiryVendorMaterial> querySaveInquiryVendorMaterialBySheetId(Long id);

    /**
     * 根据sheetid查询供应商保存状态的报价(InquiryVendorMaterial)
     *
     * @param id
     * @return {@link List<InquiryVendorMaterial>}
     */
    List<InquiryVendorMaterial> queryOfferingSaveInquiryVendorMaterialBySheetId(Long id);

}

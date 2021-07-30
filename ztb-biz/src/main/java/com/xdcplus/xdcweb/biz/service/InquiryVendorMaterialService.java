package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.InquiryVendorMaterialBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorMaterialVO;

import java.util.List;


/**
 * 询价供应商物品(InquiryVendorMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:26
 */
public interface InquiryVendorMaterialService extends InquiryVendorMaterialBaseService<InquiryVendorMaterial, InquiryVendorMaterialVO, InquiryVendorMaterial> {

    /**
     * desc查询调查供应商的材料
     *
     * @param inquiryVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link List <InquiryVendorMaterial>}
     */
    List<InquiryVendorMaterial> queryInquiryVendorMaterialsDesc(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO);

    /**
     * 得到报价次数
     *
     * @param inquiryVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link Integer}
     */
    Integer getOfferingNumber(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO);

    /**
     * 查询页面vodesc
     *
     * @param inquiryVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link PageVO <InquiryVendorMaterialVO>}
     */
    PageVO<InquiryVendorMaterialVO> queryPageVODesc(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO);

    /**
     * 根据物品id查询供应商最新报价(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialQuery 询价供应商物品(InquiryVendorMaterial)查询
     * @return {@link List<InquiryVendorMaterialVO>}
     */
    List<InquiryVendorMaterialVO> queryNewInquiryVendorMaterialByMaterialId(InquiryVendorMaterialQuery inquiryVendorMaterialQuery);

    /**
     * 根据物品id查询供应商最新报价(核价)(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialQuery 询价供应商物品(InquiryVendorMaterial)查询
     * @return {@link List<InquiryVendorMaterialVO>}
     */
    List<InquiryVendorMaterialVO> queryNewInquiryVendorMaterialByMaterialIdWithPricing(InquiryVendorMaterialQuery inquiryVendorMaterialQuery);


    /**
     * 询价单用户还价(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialDTO 询价供应商物品(InquiryVendorMaterial)查询
     * @return {@link }
     */
    Boolean dicker(InquiryVendorMaterialDTO inquiryVendorMaterialDTO);

    /**
     * 询价单供应商提交报价(InquiryVendorMaterial)
     *
     * @param inquiryVendorMaterialDTO 询价供应商物品(InquiryVendorMaterial)
     * @return {@link }
     */
    Boolean submitOffer(InquiryVendorMaterialDTO inquiryVendorMaterialDTO);

    /**
     * @param inquiryVendorMaterialDTO 询价供应商物品(InquiryVendorMaterial)查询
     * @return {@link List<  InquiryVendorMaterialVO  >}
     */
    List<InquiryVendorMaterialVO> queryAllocatedRanking(InquiryVendorMaterialDTO inquiryVendorMaterialDTO);

}

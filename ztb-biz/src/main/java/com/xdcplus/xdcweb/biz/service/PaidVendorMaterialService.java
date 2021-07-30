package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.xdcweb.biz.generator.PaidVendorMaterialBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorMaterialVO;

import java.util.List;


/**
 * 竞价供应商物品(PaidVendorMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:48
 */
public interface PaidVendorMaterialService extends PaidVendorMaterialBaseService<PaidVendorMaterial, PaidVendorMaterialVO, PaidVendorMaterial> {
    /**
     * 得到报价次数
     *
     * @param paidVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link Integer}
     */
    Integer getOfferingNumber(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO);


    /**
     * desc查询调查供应商的材料
     *
     * @param paidVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link List <  PaidVendorMaterial  >}
     */
    List<PaidVendorMaterialVO> queryPaidVendorMaterialsDesc(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO);

    /**
     * 查询页面vodesc
     *
     * @param paidVendorMaterialFilterDTO 调查供应商材料过滤dto
     * @return {@link PageVO <  PaidVendorMaterialVO  >}
     */
    PageVO<PaidVendorMaterialVO> queryPageVODesc(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO);

    /**
     * 竞价(PaidVendorMaterial)
     *
     * @param paidVendorMaterialDTO 竞价供应商物品(PaidVendorMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean offeringPaidVendorMaterial(PaidVendorMaterialDTO paidVendorMaterialDTO);

    PaidMaterialVO queryRanking(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO);

    /**
     * 根据物品id查询供应商最新报价(PaidVendorMaterial)
     *
     * @param paidVendorMaterialQuery 竞价供应商物品(PaidVendorMaterial)查询
     * @return {@link List< PaidVendorMaterialVO >}
     */
    List<PaidVendorMaterialVO> queryNewPaidVendorMaterialByMaterialId(PaidVendorMaterialQuery paidVendorMaterialQuery);

    /**
     * @param paidVendorMaterialDTO 竞价供应商物品(PaidVendorMaterial)查询
     * @return {@link List< PaidVendorMaterialVO >}
     */
    List<PaidVendorMaterialVO> queryAllocatedRanking(PaidVendorMaterialDTO paidVendorMaterialDTO);

}

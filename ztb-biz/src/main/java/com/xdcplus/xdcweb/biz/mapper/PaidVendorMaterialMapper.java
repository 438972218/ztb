package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价供应商物品(PaidVendorMaterial)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:38
 */
public interface PaidVendorMaterialMapper extends IBaseMapper<PaidVendorMaterial> {

    /**
     * 查询竞价供应商物品(PaidVendorMaterial)
     *
     * @param paidVendorMaterialQuery 竞价供应商物品(PaidVendorMaterial)查询
     * @return {@link List<PaidVendorMaterial>}
     */
    List<PaidVendorMaterial> queryPaidVendorMaterial(PaidVendorMaterialQuery paidVendorMaterialQuery);

    Integer queryRankingByVendorId(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO);

    /**
     * 根据物品id查询供应商最新报价(PaidVendorMaterial)
     *
     * @param paidVendorMaterialQuery 供应商物品(PaidVendorMaterial)查询
     * @return {@link List< PaidVendorMaterial >}
     */
    List<PaidVendorMaterial> queryNewPaidVendorMaterialByMaterialId(PaidVendorMaterialQuery paidVendorMaterialQuery);

}

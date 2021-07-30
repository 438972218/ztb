package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.generator.PaidMaterialBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;

import java.util.List;


/**
 * 竞价物品(PaidMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:41
 */
public interface PaidMaterialService extends PaidMaterialBaseService<PaidMaterial, PaidMaterialVO, PaidMaterial> {
    /**
     * 查询竞价物品(核价)(PaidMaterial)
     *
     * @param paidMaterialFilterDTO 过程状态过滤DTO
     * @return {@link List < PaidMaterialVO >} 状态信息
     */
    List<PaidMaterialVO> queryPaidMaterialsWithPricing(PaidMaterialFilterDTO paidMaterialFilterDTO);

}

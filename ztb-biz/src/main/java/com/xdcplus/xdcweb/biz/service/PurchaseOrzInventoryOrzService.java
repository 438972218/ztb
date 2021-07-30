package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzDTO;
import com.xdcplus.xdcweb.biz.generator.PurchaseOrzInventoryOrzBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrzInventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzInventoryOrzVO;


/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:41
 */
public interface PurchaseOrzInventoryOrzService extends PurchaseOrzInventoryOrzBaseService<PurchaseOrzInventoryOrz, PurchaseOrzInventoryOrzVO, PurchaseOrzInventoryOrz> {

    /**
     * 维护采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param purchaseOrzInventoryOrzDTO 过程状态过滤DTO
     */
    Boolean maintenance(PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO);


    /**
     * 获取采购和工厂对应关系
     *
     * @param purchaseOrzId 采购组织id
     * @return {@link PurchaseOrzInventoryOrzVO}
     */
    PurchaseOrzInventoryOrzVO getMaintenance(Long purchaseOrzId);

}

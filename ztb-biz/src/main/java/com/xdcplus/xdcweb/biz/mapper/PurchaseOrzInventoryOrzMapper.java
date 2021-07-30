package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrzInventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.query.PurchaseOrzInventoryOrzQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-14 10:43:28
 */
public interface PurchaseOrzInventoryOrzMapper extends IBaseMapper<PurchaseOrzInventoryOrz> {

    /**
     * 查询采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param purchaseOrzInventoryOrzQuery 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)查询
     * @return {@link List<PurchaseOrzInventoryOrz>}
     */
    List<PurchaseOrzInventoryOrz> queryPurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzQuery purchaseOrzInventoryOrzQuery);

}

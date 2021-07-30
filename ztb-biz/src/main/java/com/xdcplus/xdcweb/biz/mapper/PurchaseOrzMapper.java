package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.query.PurchaseOrzQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购组织(PurchaseOrz)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-08 11:35:43
 */
public interface PurchaseOrzMapper extends IBaseMapper<PurchaseOrz> {

    /**
     * 查询采购组织(PurchaseOrz)
     *
     * @param purchaseOrzQuery 采购组织(PurchaseOrz)查询
     * @return {@link List<PurchaseOrz>}
     */
    List<PurchaseOrz> queryPurchaseOrz(PurchaseOrzQuery purchaseOrzQuery);

}

package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.generator.InventoryOrzBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InventoryOrzVO;

import java.util.List;


/**
 * 库存组织(InventoryOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:52:45
 */
public interface InventoryOrzService extends InventoryOrzBaseService<InventoryOrz, InventoryOrzVO, InventoryOrz> {

    /**
     * 查询公司库存组织树状(InventoryOrz)
     *
     * @return {@link List < CompanyVO > } 状态信息
     */
    List<CompanyVO> queryInventoryOrzTree();

    /**
     * 查询工厂树状(InventoryOrz)
     *
     * @return {@link List<CompanyVO> } 状态信息
     */
    List<CompanyVO> findFactoryTree();

    /**
     * 查询工厂树状根据采购组织ID(筛选)(InventoryOrz)
     *
     * @return {@link List<CompanyVO> } 状态信息
     */
    List<CompanyVO> findFactoryTreeBySelect(Long purchaseOrzId);

}

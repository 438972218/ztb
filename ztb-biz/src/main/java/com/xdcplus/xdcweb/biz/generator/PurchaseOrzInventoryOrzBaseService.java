package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrzInventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzInventoryOrzVO;

import java.util.List;

/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:40
 */
public interface PurchaseOrzInventoryOrzBaseService<S, T, E> extends BaseService<PurchaseOrzInventoryOrz, PurchaseOrzInventoryOrzVO, PurchaseOrzInventoryOrz> {

    /**
     * 添加采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param purchaseOrzInventoryOrzDTO 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO);

    /**
     * 修改采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param purchaseOrzInventoryOrzDTO 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO);

    /**
     * 批量保存或更新采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param purchaseOrzInventoryOrzList 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrzList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PurchaseOrzInventoryOrz> purchaseOrzInventoryOrzList);

    /**
     * 批量保存或更新采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrzDTOList)
     *
     * @param purchaseOrzInventoryOrzDTOList 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrzDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PurchaseOrzInventoryOrzDTO> purchaseOrzInventoryOrzDTOList);

    /**
     * 删除采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param id 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePurchaseOrzInventoryOrzLogical(Long id);

    /**
     * 查询采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param purchaseOrzInventoryOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PurchaseOrzInventoryOrzVO>} 状态信息
     */
    List<PurchaseOrzInventoryOrzVO> queryPurchaseOrzInventoryOrzVOList(PurchaseOrzInventoryOrzFilterDTO purchaseOrzInventoryOrzFilterDTO);

    /**
     * 查询采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)
     *
     * @param purchaseOrzInventoryOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PurchaseOrzInventoryOrzVO>} 状态信息
     */
    PageVO<PurchaseOrzInventoryOrzVO> queryPurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzFilterDTO purchaseOrzInventoryOrzFilterDTO);

    /**
     * 查询一个
     *
     * @param id 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)主键
     * @return {@link PurchaseOrzInventoryOrzVO} 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)信息
     */
    PurchaseOrzInventoryOrzVO queryPurchaseOrzInventoryOrzById(Long id);


}

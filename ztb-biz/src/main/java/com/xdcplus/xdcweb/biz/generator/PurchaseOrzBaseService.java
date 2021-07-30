package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzVO;

import java.util.List;

/**
 * 采购组织(PurchaseOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:39
 */
public interface PurchaseOrzBaseService<S, T, E> extends BaseService<PurchaseOrz, PurchaseOrzVO, PurchaseOrz> {

    /**
     * 添加采购组织(PurchaseOrz)
     *
     * @param purchaseOrzDTO 采购组织(PurchaseOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePurchaseOrz(PurchaseOrzDTO purchaseOrzDTO);

    /**
     * 修改采购组织(PurchaseOrz)
     *
     * @param purchaseOrzDTO 采购组织(PurchaseOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePurchaseOrz(PurchaseOrzDTO purchaseOrzDTO);

    /**
     * 批量保存或更新采购组织(PurchaseOrz)
     *
     * @param purchaseOrzList 采购组织(PurchaseOrzList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PurchaseOrz> purchaseOrzList);

    /**
     * 批量保存或更新采购组织(PurchaseOrzDTOList)
     *
     * @param purchaseOrzDTOList 采购组织(PurchaseOrzDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PurchaseOrzDTO> purchaseOrzDTOList);

    /**
     * 删除采购组织(PurchaseOrz)
     *
     * @param id 采购组织(PurchaseOrz)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePurchaseOrzLogical(Long id);

    /**
     * 查询采购组织(PurchaseOrz)
     *
     * @param purchaseOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PurchaseOrzVO>} 状态信息
     */
    List<PurchaseOrzVO> queryPurchaseOrzVOList(PurchaseOrzFilterDTO purchaseOrzFilterDTO);

    /**
     * 查询采购组织(PurchaseOrz)
     *
     * @param purchaseOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PurchaseOrzVO>} 状态信息
     */
    PageVO<PurchaseOrzVO> queryPurchaseOrz(PurchaseOrzFilterDTO purchaseOrzFilterDTO);

    /**
     * 查询一个
     *
     * @param id 采购组织(PurchaseOrz)主键
     * @return {@link PurchaseOrzVO} 采购组织(PurchaseOrz)信息
     */
    PurchaseOrzVO queryPurchaseOrzById(Long id);


}

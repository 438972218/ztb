package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InventoryOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InventoryOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InventoryOrzVO;

import java.util.List;

/**
 * 库存组织(InventoryOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:52:43
 */
public interface InventoryOrzBaseService<S, T, E> extends BaseService<InventoryOrz, InventoryOrzVO, InventoryOrz> {

    /**
     * 添加库存组织(InventoryOrz)
     *
     * @param inventoryOrzDTO 库存组织(InventoryOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveInventoryOrz(InventoryOrzDTO inventoryOrzDTO);

    /**
     * 修改库存组织(InventoryOrz)
     *
     * @param inventoryOrzDTO 库存组织(InventoryOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInventoryOrz(InventoryOrzDTO inventoryOrzDTO);

    /**
     * 批量保存或更新库存组织(InventoryOrz)
     *
     * @param inventoryOrzList 库存组织(InventoryOrzList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<InventoryOrz> inventoryOrzList);

    /**
     * 批量保存或更新库存组织(InventoryOrzDTOList)
     *
     * @param inventoryOrzDTOList 库存组织(InventoryOrzDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<InventoryOrzDTO> inventoryOrzDTOList);

    /**
     * 删除库存组织(InventoryOrz)
     *
     * @param id 库存组织(InventoryOrz)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteInventoryOrzLogical(Long id);

    /**
     * 查询库存组织(InventoryOrz)
     *
     * @param inventoryOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InventoryOrzVO>} 状态信息
     */
    List<InventoryOrzVO> queryInventoryOrzVOList(InventoryOrzFilterDTO inventoryOrzFilterDTO);

    /**
     * 查询库存组织(InventoryOrz)
     *
     * @param inventoryOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InventoryOrzVO>} 状态信息
     */
    PageVO<InventoryOrzVO> queryInventoryOrz(InventoryOrzFilterDTO inventoryOrzFilterDTO);

    /**
     * 查询一个
     *
     * @param id 库存组织(InventoryOrz)主键
     * @return {@link InventoryOrzVO} 库存组织(InventoryOrz)信息
     */
    InventoryOrzVO queryInventoryOrzById(Long id);


}

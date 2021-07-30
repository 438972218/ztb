package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Item;
import com.xdcplus.xdcweb.biz.common.pojo.dto.ItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.ItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ItemVO;

import java.util.List;

/**
 * 物料管理(Item)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:52:46
 */
public interface ItemBaseService<S, T, E> extends BaseService<Item, ItemVO, Item> {

    /**
     * 添加物料管理(Item)
     *
     * @param itemDTO 物料管理(ItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveItem(ItemDTO itemDTO);

    /**
     * 修改物料管理(Item)
     *
     * @param itemDTO 物料管理(ItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateItem(ItemDTO itemDTO);

    /**
     * 批量保存或更新物料管理(Item)
     *
     * @param itemList 物料管理(ItemList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<Item> itemList);

    /**
     * 批量保存或更新物料管理(ItemDTOList)
     *
     * @param itemDTOList 物料管理(ItemDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<ItemDTO> itemDTOList);

    /**
     * 删除物料管理(Item)
     *
     * @param id 物料管理(Item)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteItemLogical(Long id);

    /**
     * 查询物料管理(Item)
     *
     * @param itemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ItemVO>} 状态信息
     */
    List<ItemVO> queryItemVOList(ItemFilterDTO itemFilterDTO);

    /**
     * 查询物料管理(Item)
     *
     * @param itemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ItemVO>} 状态信息
     */
    PageVO<ItemVO> queryItem(ItemFilterDTO itemFilterDTO);

    /**
     * 查询一个
     *
     * @param id 物料管理(Item)主键
     * @return {@link ItemVO} 物料管理(Item)信息
     */
    ItemVO queryItemById(Long id);


}

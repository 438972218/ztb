package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Item;
import com.xdcplus.xdcweb.biz.common.pojo.query.ItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料管理(Item)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-02 10:28:13
 */
public interface ItemMapper extends IBaseMapper<Item> {

    /**
     * 查询物料管理(Item)
     *
     * @param itemQuery 物料管理(Item)查询
     * @return {@link List<Item>}
     */
    List<Item> queryItem(ItemQuery itemQuery);

}

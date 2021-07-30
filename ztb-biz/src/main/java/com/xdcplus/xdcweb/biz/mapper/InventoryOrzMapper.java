package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.query.InventoryOrzQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 库存组织(InventoryOrz)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-08 11:35:42
 */
public interface InventoryOrzMapper extends IBaseMapper<InventoryOrz> {

    /**
     * 查询库存组织(InventoryOrz)
     *
     * @param inventoryOrzQuery 库存组织(InventoryOrz)查询
     * @return {@link List<InventoryOrz>}
     */
    List<InventoryOrz> queryInventoryOrz(InventoryOrzQuery inventoryOrzQuery);

    Map<Long,List<CompanyVO>> factoryTree();

}

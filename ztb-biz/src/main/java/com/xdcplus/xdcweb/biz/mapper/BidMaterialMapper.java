package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标物品(BidMaterial)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:36
 */
public interface BidMaterialMapper extends IBaseMapper<BidMaterial> {

    /**
     * 查询招标物品(BidMaterial)
     *
     * @param bidMaterialQuery 招标物品(BidMaterial)查询
     * @return {@link List<BidMaterial>}
     */
    List<BidMaterial> queryBidMaterial(BidMaterialQuery bidMaterialQuery);

}

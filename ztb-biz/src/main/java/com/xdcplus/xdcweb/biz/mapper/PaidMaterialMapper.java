package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价物品(PaidMaterial)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:19:29
 */
public interface PaidMaterialMapper extends IBaseMapper<PaidMaterial> {

    /**
     * 查询竞价物品(PaidMaterial)
     *
     * @param paidMaterialQuery 竞价物品(PaidMaterial)查询
     * @return {@link List<PaidMaterial>}
     */
    List<PaidMaterial> queryPaidMaterial(PaidMaterialQuery paidMaterialQuery);

}

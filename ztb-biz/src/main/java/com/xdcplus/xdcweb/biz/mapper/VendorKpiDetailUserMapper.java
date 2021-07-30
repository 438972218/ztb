package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiDetailUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商绩效人员评价表(VendorKpiDetailUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-22 10:18:21
 */
public interface VendorKpiDetailUserMapper extends IBaseMapper<VendorKpiDetailUser> {

    /**
     * 查询供应商绩效人员评价表(VendorKpiDetailUser)
     *
     * @param vendorKpiDetailUserQuery 供应商绩效人员评价表(VendorKpiDetailUser)查询
     * @return {@link List<VendorKpiDetailUser>}
     */
    List<VendorKpiDetailUser> queryVendorKpiDetailUser(VendorKpiDetailUserQuery vendorKpiDetailUserQuery);

}

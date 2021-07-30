package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsDetailUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-20 15:47:28
 */
public interface VendorSiteInsDetailUserMapper extends IBaseMapper<VendorSiteInsDetailUser> {

    /**
     * 查询供应商现场考察人员评价表(VendorSiteInsDetailUser)
     *
     * @param vendorSiteInsDetailUserQuery 供应商现场考察人员评价表(VendorSiteInsDetailUser)查询
     * @return {@link List<VendorSiteInsDetailUser>}
     */
    List<VendorSiteInsDetailUser> queryVendorSiteInsDetailUser(VendorSiteInsDetailUserQuery vendorSiteInsDetailUserQuery);

}

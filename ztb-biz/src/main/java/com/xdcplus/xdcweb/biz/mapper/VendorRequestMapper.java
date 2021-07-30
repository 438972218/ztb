package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequest;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorRequestQuery;

import java.util.List;

/**
 * 供应商注册单(VendorRequest)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-26 15:31:58
 */
public interface VendorRequestMapper extends IBaseMapper<VendorRequest> {

    /**
     * 查询供应商注册单(VendorRequest)
     *
     * @param vendorRequestQuery 供应商注册单(VendorRequest)查询
     * @return {@link List<VendorRequest>}
     */
    List<VendorRequest> queryVendorRequest(VendorRequestQuery vendorRequestQuery);

}

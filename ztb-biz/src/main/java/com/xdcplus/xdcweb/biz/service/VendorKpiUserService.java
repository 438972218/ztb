package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorKpiUserBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiUser;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiUserVO;


/**
 * 供应商绩效人员表(VendorKpiUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:50
 */
public interface VendorKpiUserService extends VendorKpiUserBaseService<VendorKpiUser, VendorKpiUserVO, VendorKpiUser> {

    /**
     * 根据kpiId删除数据
     *
     * @param id 供应商绩效考核表(VendorKpi)主键
     * @return {@link int} 供应商绩效人员表(VendorKpiUser)信息
     */
    int deleteVendorKpiUserByKpiId(Long id);

}

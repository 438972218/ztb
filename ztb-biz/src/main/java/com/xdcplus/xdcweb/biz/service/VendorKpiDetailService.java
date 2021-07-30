package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorKpiDetailBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailVO;


/**
 * 供应商绩效考核表明细(VendorKpiDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:45
 */
public interface VendorKpiDetailService extends VendorKpiDetailBaseService<VendorKpiDetail, VendorKpiDetailVO, VendorKpiDetail> {

    /**
     * 删除绩效考核表细节
     *
     * @param id
     * @return {@link int}
     */
    int deleteVendorKpiDetailByKpiId(Long id);

}

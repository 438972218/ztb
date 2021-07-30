package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorSiteInsDetailBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailVO;


/**
 * 供应商现场考察表明细(VendorSiteInsDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:14
 */
public interface VendorSiteInsDetailService extends VendorSiteInsDetailBaseService<VendorSiteInsDetail, VendorSiteInsDetailVO, VendorSiteInsDetail> {

    /**
     * 删除供应商现场考察表由包装细节
     *
     * @param id
     * @return {@link int}
     */
    int deleteVendorSiteInsBySiteInsId(Long id);

}

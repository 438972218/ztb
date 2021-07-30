package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorSiteInsUserBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsUser;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsUserVO;


/**
 * 供应商现场考察人员表(VendorSiteInsUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:22
 */
public interface VendorSiteInsUserService extends VendorSiteInsUserBaseService<VendorSiteInsUser, VendorSiteInsUserVO, VendorSiteInsUser> {

    /**
     * 删除供应商现场考察人员由包装细节
     *
     * @param id
     * @return {@link int}
     */
    int deleteVendorSiteInsBySiteInsId(Long id);

}

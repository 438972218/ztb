package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorCertificateBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCertificateVO;


/**
 * 供应商证书(VendorCertificate)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:40
 */
public interface VendorCertificateService extends VendorCertificateBaseService<VendorCertificate, VendorCertificateVO, VendorCertificate> {

    /**
     * 根据vendorId 删除证书
     *
     * @param id 供应商(根据vendorId)
     * @return {@link int}
     */
    int deleteVendorCertificateByVendorId(Long id);

}

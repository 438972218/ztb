package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorRequestCertificateBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCertificateVO;


/**
 * 供应商注册单证书(VendorRequestCertificate)表服务接口层
 *
 * @author makejava
 * @since 2021-07-27 11:08:58
 */
public interface VendorRequestCertificateService extends VendorRequestCertificateBaseService<VendorRequestCertificate, VendorRequestCertificateVO, VendorRequestCertificate> {

    int deleteVendorRequestCertificateByRequestId(Long id);
}

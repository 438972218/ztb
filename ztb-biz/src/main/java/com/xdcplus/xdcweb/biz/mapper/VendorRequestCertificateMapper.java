package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorRequestCertificateQuery;

import java.util.List;

/**
 * 供应商注册单证书(VendorRequestCertificate)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-26 15:47:59
 */
public interface VendorRequestCertificateMapper extends IBaseMapper<VendorRequestCertificate> {

    /**
     * 查询供应商注册单证书(VendorRequestCertificate)
     *
     * @param vendorRequestCertificateQuery 供应商注册单证书(VendorRequestCertificate)查询
     * @return {@link List<VendorRequestCertificate>}
     */
    List<VendorRequestCertificate> queryVendorRequestCertificate(VendorRequestCertificateQuery vendorRequestCertificateQuery);

}

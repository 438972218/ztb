package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorCertificateQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商证书(VendorCertificate)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-12 20:52:49
 */
public interface VendorCertificateMapper extends IBaseMapper<VendorCertificate> {

    /**
     * 查询供应商证书(VendorCertificate)
     *
     * @param vendorCertificateQuery 供应商证书(VendorCertificate)查询
     * @return {@link List<VendorCertificate>}
     */
    List<VendorCertificate> queryVendorCertificate(VendorCertificateQuery vendorCertificateQuery);

}

package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorRequestCertificateBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorRequestCertificateMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCertificateVO;
import com.xdcplus.xdcweb.biz.service.VendorRequestCertificateService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商注册单证书(VendorRequestCertificate)表服务实现类
 *
 * @author makejava
 * @since 2021-07-27 11:08:58
 */
@Slf4j
@Service("vendorRequestCertificateService")
public class VendorRequestCertificateServiceImpl extends VendorRequestCertificateBaseServiceImpl<VendorRequestCertificate, VendorRequestCertificateVO, VendorRequestCertificate, VendorRequestCertificateMapper> implements VendorRequestCertificateService {

    @Override
    public int deleteVendorRequestCertificateByRequestId(Long id) {
        return vendorRequestCertificateMapper.delete(new QueryWrapper<VendorRequestCertificate>().eq("vendor_request_id",id));
    }
}

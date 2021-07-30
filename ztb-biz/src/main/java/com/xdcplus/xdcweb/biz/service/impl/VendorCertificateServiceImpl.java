package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorCertificateBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorCertificateMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCertificateVO;
import com.xdcplus.xdcweb.biz.service.VendorCertificateService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商证书(VendorCertificate)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:40
 */
@Slf4j
@Service("vendorCertificateService")
public class VendorCertificateServiceImpl extends VendorCertificateBaseServiceImpl<VendorCertificate, VendorCertificateVO, VendorCertificate, VendorCertificateMapper> implements VendorCertificateService {


    @Override
    public int deleteVendorCertificateByVendorId(Long id) {
        return vendorCertificateMapper.delete(new QueryWrapper<VendorCertificate>().eq("vendor_id",id));
    }
}

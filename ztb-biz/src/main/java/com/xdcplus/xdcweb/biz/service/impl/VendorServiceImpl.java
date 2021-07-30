package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorDTO;
import com.xdcplus.xdcweb.biz.generator.impl.VendorBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Vendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorVO;
import com.xdcplus.xdcweb.biz.service.VendorCertificateService;
import com.xdcplus.xdcweb.biz.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 供应商(Vendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:36
 */
@Slf4j
@Service("vendorService")
public class VendorServiceImpl extends VendorBaseServiceImpl<Vendor, VendorVO, Vendor, VendorMapper> implements VendorService {

    @Autowired
    private VendorCertificateService vendorCertificateService;

    @Override
    public Boolean updateVendorWithDetail(VendorDTO vendorDTO) {
        Vendor vendor = this.getById(vendorDTO.getId());
        if (ObjectUtil.isNull(vendor)) {
            log.error("updateVendorQuestion() The VendorQuestion does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        BeanUtil.copyProperties(vendorDTO, vendor);
        vendor.setUpdatedUser(vendorDTO.getUpdatedUser());
        vendor.setUpdatedTime(DateUtil.current());
        Boolean result = this.updateById(vendor);
        vendorCertificateService.deleteVendorCertificateByVendorId(vendorDTO.getId());

        if (CollectionUtil.isEmpty(vendorDTO.getVendorCertificateDTOS())) {
            return result;
        }

        List<VendorCertificateDTO> vendorCertificateDTOS = vendorDTO.getVendorCertificateDTOS();

        vendorCertificateDTOS.forEach(vendorCertificateDTO -> {
            vendorCertificateDTO.setVendorId(vendorDTO.getId());
            vendorCertificateService.saveVendorCertificate(vendorCertificateDTO);
        });

        return result;
    }

    @Override
    public VendorVO showVendorById(Long vendorId) {
        Assert.notNull(vendorId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        VendorVO vendorVO = this.objectConversion(this.getById(vendorId));

        VendorCertificateFilterDTO vendorCertificateFilterDTO = new VendorCertificateFilterDTO();
        vendorCertificateFilterDTO.setVendorId(vendorId);
        vendorVO.setVendorCertificateVOS(vendorCertificateService.queryVendorCertificateVOList(vendorCertificateFilterDTO));

        return vendorVO;
    }
}

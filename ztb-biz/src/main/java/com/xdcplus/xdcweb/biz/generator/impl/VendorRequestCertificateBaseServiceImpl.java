package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorRequestCertificateMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCertificateVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorRequestCertificateQuery;
import com.xdcplus.xdcweb.biz.generator.VendorRequestCertificateBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.utils.BeanUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供应商注册单证书(VendorRequestCertificate)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:55
 */
public class VendorRequestCertificateBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorRequestCertificate, VendorRequestCertificateVO, VendorRequestCertificate, VendorRequestCertificateMapper> implements VendorRequestCertificateBaseService<S, T, E> {

    @Autowired
    protected VendorRequestCertificateMapper vendorRequestCertificateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorRequestCertificate(VendorRequestCertificateDTO vendorRequestCertificateDTO) {

        VendorRequestCertificate vendorRequestCertificate = vendorRequestCertificateMapper.selectById(vendorRequestCertificateDTO.getId());
        if (ObjectUtil.isNotNull(vendorRequestCertificate)) {
            log.error("saveVendorRequestCertificate() The VendorRequestCertificate already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorRequestCertificate = new VendorRequestCertificate();
        BeanUtil.copyProperties(vendorRequestCertificateDTO, vendorRequestCertificate);
        vendorRequestCertificate.setCreatedTime(DateUtil.current());
        vendorRequestCertificate.setDeleted(0);

        return this.save(vendorRequestCertificate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorRequestCertificate(VendorRequestCertificateDTO vendorRequestCertificateDTO) {

        VendorRequestCertificate vendorRequestCertificate = this.getById(vendorRequestCertificateDTO.getId());
        if (ObjectUtil.isNull(vendorRequestCertificate)) {
            log.error("updateVendorRequestCertificate() The VendorRequestCertificate does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorRequestCertificateDTO, vendorRequestCertificate);
        vendorRequestCertificate.setUpdatedUser(vendorRequestCertificateDTO.getUpdatedUser());
        vendorRequestCertificate.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorRequestCertificate);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorRequestCertificate> vendorRequestCertificateList) {

        vendorRequestCertificateList.forEach(vendorRequestCertificate -> {
            VendorRequestCertificate vendorRequestCertificateParam = new VendorRequestCertificate();
            vendorRequestCertificateParam.setId(vendorRequestCertificate.getId());
            if (ObjectUtil.isNotNull(vendorRequestCertificate.getId())) {
                vendorRequestCertificate.setId(vendorRequestCertificate.getId());
                vendorRequestCertificate.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorRequestCertificate> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorRequestCertificate::getId, vendorRequestCertificate.getId());
                update(vendorRequestCertificate, lambdaUpdate);
            } else {
                vendorRequestCertificate.setCreatedTime(DateUtil.current());
                vendorRequestCertificate.setDeleted(0);
                save(vendorRequestCertificate);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorRequestCertificateDTO> vendorRequestCertificateDTOList) {

        List<VendorRequestCertificate> vendorRequestCertificateList = BeanUtils.copyProperties(vendorRequestCertificateDTOList, VendorRequestCertificate.class);
        return saveOrUpdateBatch(vendorRequestCertificateList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorRequestCertificateLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorRequestCertificate vendorRequestCertificate = this.getById(id);

        if (ObjectUtil.isNull(vendorRequestCertificate)) {
            log.error("deleteVendorRequestCertificate() The VendorRequestCertificate does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorRequestCertificate.setDeleted(1);

        return this.updateById(vendorRequestCertificate);
    }

    private List<VendorRequestCertificate> queryVendorRequestCertificateList(VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestCertificateFilterDTO)) {
            vendorRequestCertificateFilterDTO = new VendorRequestCertificateFilterDTO();
        }
        vendorRequestCertificateFilterDTO.setDeleted(0);
        VendorRequestCertificateQuery vendorRequestCertificateQuery = BeanUtil.copyProperties(vendorRequestCertificateFilterDTO, VendorRequestCertificateQuery.class);

        return vendorRequestCertificateMapper.queryVendorRequestCertificate(vendorRequestCertificateQuery);
    }

    @Override
    public List<VendorRequestCertificateVO> queryVendorRequestCertificateVOList(VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestCertificateFilterDTO)) {
            vendorRequestCertificateFilterDTO = new VendorRequestCertificateFilterDTO();
        }
        vendorRequestCertificateFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorRequestCertificateList(vendorRequestCertificateFilterDTO));
    }

    @Override
    public PageVO<VendorRequestCertificateVO> queryVendorRequestCertificate(VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestCertificateFilterDTO)) {
            vendorRequestCertificateFilterDTO = new VendorRequestCertificateFilterDTO();
        }
        vendorRequestCertificateFilterDTO.setDeleted(0);
        PageVO<VendorRequestCertificateVO> pageVO = new PageVO<>();

        if (vendorRequestCertificateFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorRequestCertificateFilterDTO.getCurrentPage(), vendorRequestCertificateFilterDTO.getPageSize(),
                    vendorRequestCertificateFilterDTO.getOrderType(), vendorRequestCertificateFilterDTO.getOrderField());
        }

        List<VendorRequestCertificate> vendorRequestCertificateList = queryVendorRequestCertificateList(vendorRequestCertificateFilterDTO);

        PageInfo<VendorRequestCertificate> pageInfo = new PageInfo<>(vendorRequestCertificateList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorRequestCertificateList));

        return pageVO;
    }

    @Override
    public VendorRequestCertificateVO queryVendorRequestCertificateById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

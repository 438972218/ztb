package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorCertificateMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCertificateVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorCertificateQuery;
import com.xdcplus.xdcweb.biz.generator.VendorCertificateBaseService;
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
 * 供应商证书(VendorCertificate)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:44
 */
public class VendorCertificateBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorCertificate, VendorCertificateVO, VendorCertificate, VendorCertificateMapper> implements VendorCertificateBaseService<S, T, E> {

    @Autowired
    protected VendorCertificateMapper vendorCertificateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorCertificate(VendorCertificateDTO vendorCertificateDTO) {

        VendorCertificate vendorCertificate = vendorCertificateMapper.selectById(vendorCertificateDTO.getId());
        if (ObjectUtil.isNotNull(vendorCertificate)) {
            log.error("saveVendorCertificate() The VendorCertificate already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorCertificate = new VendorCertificate();
        BeanUtil.copyProperties(vendorCertificateDTO, vendorCertificate);
        vendorCertificate.setCreatedTime(DateUtil.current());
        vendorCertificate.setDeleted(0);

        return this.save(vendorCertificate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorCertificate(VendorCertificateDTO vendorCertificateDTO) {

        VendorCertificate vendorCertificate = this.getById(vendorCertificateDTO.getId());
        if (ObjectUtil.isNull(vendorCertificate)) {
            log.error("updateVendorCertificate() The VendorCertificate does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorCertificateDTO, vendorCertificate);
        vendorCertificate.setUpdatedUser(vendorCertificateDTO.getUpdatedUser());
        vendorCertificate.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorCertificate);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorCertificate> vendorCertificateList) {

        vendorCertificateList.forEach(vendorCertificate -> {
            VendorCertificate vendorCertificateParam = new VendorCertificate();
            vendorCertificateParam.setId(vendorCertificate.getId());
            if (ObjectUtil.isNotNull(vendorCertificate.getId())) {
                vendorCertificate.setId(vendorCertificate.getId());
                vendorCertificate.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorCertificate> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorCertificate::getId, vendorCertificate.getId());
                update(vendorCertificate, lambdaUpdate);
            } else {
                vendorCertificate.setCreatedTime(DateUtil.current());
                vendorCertificate.setDeleted(0);
                save(vendorCertificate);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorCertificateDTO> vendorCertificateDTOList) {

        List<VendorCertificate> vendorCertificateList = BeanUtils.copyProperties(vendorCertificateDTOList, VendorCertificate.class);
        return saveOrUpdateBatch(vendorCertificateList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorCertificateLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorCertificate vendorCertificate = this.getById(id);

        if (ObjectUtil.isNull(vendorCertificate)) {
            log.error("deleteVendorCertificate() The VendorCertificate does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorCertificate.setDeleted(1);

        return this.updateById(vendorCertificate);
    }

    private List<VendorCertificate> queryVendorCertificateList(VendorCertificateFilterDTO vendorCertificateFilterDTO) {
        if (ObjectUtil.isNull(vendorCertificateFilterDTO)) {
            vendorCertificateFilterDTO = new VendorCertificateFilterDTO();
        }
        vendorCertificateFilterDTO.setDeleted(0);
        VendorCertificateQuery vendorCertificateQuery = BeanUtil.copyProperties(vendorCertificateFilterDTO, VendorCertificateQuery.class);

        return vendorCertificateMapper.queryVendorCertificate(vendorCertificateQuery);
    }

    @Override
    public List<VendorCertificateVO> queryVendorCertificateVOList(VendorCertificateFilterDTO vendorCertificateFilterDTO) {
        if (ObjectUtil.isNull(vendorCertificateFilterDTO)) {
            vendorCertificateFilterDTO = new VendorCertificateFilterDTO();
        }
        vendorCertificateFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorCertificateList(vendorCertificateFilterDTO));
    }

    @Override
    public PageVO<VendorCertificateVO> queryVendorCertificate(VendorCertificateFilterDTO vendorCertificateFilterDTO) {
        if (ObjectUtil.isNull(vendorCertificateFilterDTO)) {
            vendorCertificateFilterDTO = new VendorCertificateFilterDTO();
        }
        vendorCertificateFilterDTO.setDeleted(0);
        PageVO<VendorCertificateVO> pageVO = new PageVO<>();

        if (vendorCertificateFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorCertificateFilterDTO.getCurrentPage(), vendorCertificateFilterDTO.getPageSize(),
                    vendorCertificateFilterDTO.getOrderType(), vendorCertificateFilterDTO.getOrderField());
        }

        List<VendorCertificate> vendorCertificateList = queryVendorCertificateList(vendorCertificateFilterDTO);

        PageInfo<VendorCertificate> pageInfo = new PageInfo<>(vendorCertificateList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorCertificateList));

        return pageVO;
    }

    @Override
    public VendorCertificateVO queryVendorCertificateById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

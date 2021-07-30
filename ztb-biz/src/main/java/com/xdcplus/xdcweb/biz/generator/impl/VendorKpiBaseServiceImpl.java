package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpi;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiQuery;
import com.xdcplus.xdcweb.biz.generator.VendorKpiBaseService;
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
 * 供应商绩效考核表(VendorKpi)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:45
 */
public class VendorKpiBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorKpi, VendorKpiVO, VendorKpi, VendorKpiMapper> implements VendorKpiBaseService<S, T, E> {

    @Autowired
    protected VendorKpiMapper vendorKpiMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorKpi(VendorKpiDTO vendorKpiDTO) {

        VendorKpi vendorKpi = vendorKpiMapper.selectById(vendorKpiDTO.getId());
        if (ObjectUtil.isNotNull(vendorKpi)) {
            log.error("saveVendorKpi() The VendorKpi already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorKpi = new VendorKpi();
        BeanUtil.copyProperties(vendorKpiDTO, vendorKpi);
        vendorKpi.setCreatedTime(DateUtil.current());
        vendorKpi.setDeleted(0);

        return this.save(vendorKpi);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorKpi(VendorKpiDTO vendorKpiDTO) {

        VendorKpi vendorKpi = this.getById(vendorKpiDTO.getId());
        if (ObjectUtil.isNull(vendorKpi)) {
            log.error("updateVendorKpi() The VendorKpi does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorKpiDTO, vendorKpi);
        vendorKpi.setUpdatedUser(vendorKpiDTO.getUpdatedUser());
        vendorKpi.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorKpi);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorKpi> vendorKpiList) {

        vendorKpiList.forEach(vendorKpi -> {
            VendorKpi vendorKpiParam = new VendorKpi();
            vendorKpiParam.setId(vendorKpi.getId());
            if (ObjectUtil.isNotNull(vendorKpi.getId())) {
                vendorKpi.setId(vendorKpi.getId());
                vendorKpi.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorKpi> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorKpi::getId, vendorKpi.getId());
                update(vendorKpi, lambdaUpdate);
            } else {
                vendorKpi.setCreatedTime(DateUtil.current());
                vendorKpi.setDeleted(0);
                save(vendorKpi);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorKpiDTO> vendorKpiDTOList) {

        List<VendorKpi> vendorKpiList = BeanUtils.copyProperties(vendorKpiDTOList, VendorKpi.class);
        return saveOrUpdateBatch(vendorKpiList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorKpiLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorKpi vendorKpi = this.getById(id);

        if (ObjectUtil.isNull(vendorKpi)) {
            log.error("deleteVendorKpi() The VendorKpi does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorKpi.setDeleted(1);

        return this.updateById(vendorKpi);
    }

    private List<VendorKpi> queryVendorKpiList(VendorKpiFilterDTO vendorKpiFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiFilterDTO)) {
            vendorKpiFilterDTO = new VendorKpiFilterDTO();
        }
        vendorKpiFilterDTO.setDeleted(0);
        VendorKpiQuery vendorKpiQuery = BeanUtil.copyProperties(vendorKpiFilterDTO, VendorKpiQuery.class);

        return vendorKpiMapper.queryVendorKpi(vendorKpiQuery);
    }

    @Override
    public List<VendorKpiVO> queryVendorKpiVOList(VendorKpiFilterDTO vendorKpiFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiFilterDTO)) {
            vendorKpiFilterDTO = new VendorKpiFilterDTO();
        }
        vendorKpiFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorKpiList(vendorKpiFilterDTO));
    }

    @Override
    public PageVO<VendorKpiVO> queryVendorKpi(VendorKpiFilterDTO vendorKpiFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiFilterDTO)) {
            vendorKpiFilterDTO = new VendorKpiFilterDTO();
        }
        vendorKpiFilterDTO.setDeleted(0);
        PageVO<VendorKpiVO> pageVO = new PageVO<>();

        if (vendorKpiFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorKpiFilterDTO.getCurrentPage(), vendorKpiFilterDTO.getPageSize(),
                    vendorKpiFilterDTO.getOrderType(), vendorKpiFilterDTO.getOrderField());
        }

        List<VendorKpi> vendorKpiList = queryVendorKpiList(vendorKpiFilterDTO);

        PageInfo<VendorKpi> pageInfo = new PageInfo<>(vendorKpiList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorKpiList));

        return pageVO;
    }

    @Override
    public VendorKpiVO queryVendorKpiById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

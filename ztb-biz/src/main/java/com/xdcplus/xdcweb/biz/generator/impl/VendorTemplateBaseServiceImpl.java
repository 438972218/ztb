package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorTemplateMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplate;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorTemplateQuery;
import com.xdcplus.xdcweb.biz.generator.VendorTemplateBaseService;
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
 * 模板表(VendorTemplate)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:59
 */
public class VendorTemplateBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorTemplate, VendorTemplateVO, VendorTemplate, VendorTemplateMapper> implements VendorTemplateBaseService<S, T, E> {

    @Autowired
    protected VendorTemplateMapper vendorTemplateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorTemplate(VendorTemplateDTO vendorTemplateDTO) {

        VendorTemplate vendorTemplate = vendorTemplateMapper.selectById(vendorTemplateDTO.getId());
        if (ObjectUtil.isNotNull(vendorTemplate)) {
            log.error("saveVendorTemplate() The VendorTemplate already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorTemplate = new VendorTemplate();
        BeanUtil.copyProperties(vendorTemplateDTO, vendorTemplate);
        vendorTemplate.setCreatedTime(DateUtil.current());
        vendorTemplate.setDeleted(0);

        return this.save(vendorTemplate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorTemplate(VendorTemplateDTO vendorTemplateDTO) {

        VendorTemplate vendorTemplate = this.getById(vendorTemplateDTO.getId());
        if (ObjectUtil.isNull(vendorTemplate)) {
            log.error("updateVendorTemplate() The VendorTemplate does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorTemplateDTO, vendorTemplate);
        vendorTemplate.setUpdatedUser(vendorTemplateDTO.getUpdatedUser());
        vendorTemplate.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorTemplate);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorTemplate> vendorTemplateList) {

        vendorTemplateList.forEach(vendorTemplate -> {
            VendorTemplate vendorTemplateParam = new VendorTemplate();
            vendorTemplateParam.setId(vendorTemplate.getId());
            if (ObjectUtil.isNotNull(vendorTemplate.getId())) {
                vendorTemplate.setId(vendorTemplate.getId());
                vendorTemplate.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorTemplate> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorTemplate::getId, vendorTemplate.getId());
                update(vendorTemplate, lambdaUpdate);
            } else {
                vendorTemplate.setCreatedTime(DateUtil.current());
                vendorTemplate.setDeleted(0);
                save(vendorTemplate);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorTemplateDTO> vendorTemplateDTOList) {

        List<VendorTemplate> vendorTemplateList = BeanUtils.copyProperties(vendorTemplateDTOList, VendorTemplate.class);
        return saveOrUpdateBatch(vendorTemplateList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorTemplateLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorTemplate vendorTemplate = this.getById(id);

        if (ObjectUtil.isNull(vendorTemplate)) {
            log.error("deleteVendorTemplate() The VendorTemplate does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorTemplate.setDeleted(1);

        return this.updateById(vendorTemplate);
    }

    private List<VendorTemplate> queryVendorTemplateList(VendorTemplateFilterDTO vendorTemplateFilterDTO) {
        if (ObjectUtil.isNull(vendorTemplateFilterDTO)) {
            vendorTemplateFilterDTO = new VendorTemplateFilterDTO();
        }
        vendorTemplateFilterDTO.setDeleted(0);
        VendorTemplateQuery vendorTemplateQuery = BeanUtil.copyProperties(vendorTemplateFilterDTO, VendorTemplateQuery.class);

        return vendorTemplateMapper.queryVendorTemplate(vendorTemplateQuery);
    }

    @Override
    public List<VendorTemplateVO> queryVendorTemplateVOList(VendorTemplateFilterDTO vendorTemplateFilterDTO) {
        if (ObjectUtil.isNull(vendorTemplateFilterDTO)) {
            vendorTemplateFilterDTO = new VendorTemplateFilterDTO();
        }
        vendorTemplateFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorTemplateList(vendorTemplateFilterDTO));
    }

    @Override
    public PageVO<VendorTemplateVO> queryVendorTemplate(VendorTemplateFilterDTO vendorTemplateFilterDTO) {
        if (ObjectUtil.isNull(vendorTemplateFilterDTO)) {
            vendorTemplateFilterDTO = new VendorTemplateFilterDTO();
        }
        vendorTemplateFilterDTO.setDeleted(0);
        PageVO<VendorTemplateVO> pageVO = new PageVO<>();

        if (vendorTemplateFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorTemplateFilterDTO.getCurrentPage(), vendorTemplateFilterDTO.getPageSize(),
                    vendorTemplateFilterDTO.getOrderType(), vendorTemplateFilterDTO.getOrderField());
        }

        List<VendorTemplate> vendorTemplateList = queryVendorTemplateList(vendorTemplateFilterDTO);

        PageInfo<VendorTemplate> pageInfo = new PageInfo<>(vendorTemplateList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorTemplateList));

        return pageVO;
    }

    @Override
    public VendorTemplateVO queryVendorTemplateById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

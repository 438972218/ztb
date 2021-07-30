package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryVendorMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.InquiryVendorMaterialBaseService;
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
 * 询价供应商物品(InquiryVendorMaterial)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:11
 */
public class InquiryVendorMaterialBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<InquiryVendorMaterial, InquiryVendorMaterialVO, InquiryVendorMaterial, InquiryVendorMaterialMapper> implements InquiryVendorMaterialBaseService<S, T, E> {

    @Autowired
    protected InquiryVendorMaterialMapper inquiryVendorMaterialMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveInquiryVendorMaterial(InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        InquiryVendorMaterial inquiryVendorMaterial = inquiryVendorMaterialMapper.selectById(inquiryVendorMaterialDTO.getId());
        if (ObjectUtil.isNotNull(inquiryVendorMaterial)) {
            log.error("saveInquiryVendorMaterial() The InquiryVendorMaterial already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inquiryVendorMaterial = new InquiryVendorMaterial();
        BeanUtil.copyProperties(inquiryVendorMaterialDTO, inquiryVendorMaterial);
        inquiryVendorMaterial.setCreatedTime(DateUtil.current());
        inquiryVendorMaterial.setDeleted(0);

        return this.save(inquiryVendorMaterial);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateInquiryVendorMaterial(InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        InquiryVendorMaterial inquiryVendorMaterial = this.getById(inquiryVendorMaterialDTO.getId());
        if (ObjectUtil.isNull(inquiryVendorMaterial)) {
            log.error("updateInquiryVendorMaterial() The InquiryVendorMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(inquiryVendorMaterialDTO, inquiryVendorMaterial);
        inquiryVendorMaterial.setUpdatedUser(inquiryVendorMaterialDTO.getUpdatedUser());
        inquiryVendorMaterial.setUpdatedTime(DateUtil.current());

        return this.updateById(inquiryVendorMaterial);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<InquiryVendorMaterial> inquiryVendorMaterialList) {

        inquiryVendorMaterialList.forEach(inquiryVendorMaterial -> {
            InquiryVendorMaterial inquiryVendorMaterialParam = new InquiryVendorMaterial();
            inquiryVendorMaterialParam.setId(inquiryVendorMaterial.getId());
            if (ObjectUtil.isNotNull(inquiryVendorMaterial.getId())) {
                inquiryVendorMaterial.setId(inquiryVendorMaterial.getId());
                inquiryVendorMaterial.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<InquiryVendorMaterial> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(InquiryVendorMaterial::getId, inquiryVendorMaterial.getId());
                update(inquiryVendorMaterial, lambdaUpdate);
            } else {
                inquiryVendorMaterial.setCreatedTime(DateUtil.current());
                inquiryVendorMaterial.setDeleted(0);
                save(inquiryVendorMaterial);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<InquiryVendorMaterialDTO> inquiryVendorMaterialDTOList) {

        List<InquiryVendorMaterial> inquiryVendorMaterialList = BeanUtils.copyProperties(inquiryVendorMaterialDTOList, InquiryVendorMaterial.class);
        return saveOrUpdateBatch(inquiryVendorMaterialList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteInquiryVendorMaterialLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        InquiryVendorMaterial inquiryVendorMaterial = this.getById(id);

        if (ObjectUtil.isNull(inquiryVendorMaterial)) {
            log.error("deleteInquiryVendorMaterial() The InquiryVendorMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        inquiryVendorMaterial.setDeleted(1);

        return this.updateById(inquiryVendorMaterial);
    }

    private List<InquiryVendorMaterial> queryInquiryVendorMaterialList(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorMaterialFilterDTO)) {
            inquiryVendorMaterialFilterDTO = new InquiryVendorMaterialFilterDTO();
        }
        inquiryVendorMaterialFilterDTO.setDeleted(0);
        InquiryVendorMaterialQuery inquiryVendorMaterialQuery = BeanUtil.copyProperties(inquiryVendorMaterialFilterDTO, InquiryVendorMaterialQuery.class);

        return inquiryVendorMaterialMapper.queryInquiryVendorMaterial(inquiryVendorMaterialQuery);
    }

    @Override
    public List<InquiryVendorMaterialVO> queryInquiryVendorMaterialVOList(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorMaterialFilterDTO)) {
            inquiryVendorMaterialFilterDTO = new InquiryVendorMaterialFilterDTO();
        }
        inquiryVendorMaterialFilterDTO.setDeleted(0);
        return this.objectConversion(queryInquiryVendorMaterialList(inquiryVendorMaterialFilterDTO));
    }

    @Override
    public PageVO<InquiryVendorMaterialVO> queryInquiryVendorMaterial(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorMaterialFilterDTO)) {
            inquiryVendorMaterialFilterDTO = new InquiryVendorMaterialFilterDTO();
        }
        inquiryVendorMaterialFilterDTO.setDeleted(0);
        PageVO<InquiryVendorMaterialVO> pageVO = new PageVO<>();

        if (inquiryVendorMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquiryVendorMaterialFilterDTO.getCurrentPage(), inquiryVendorMaterialFilterDTO.getPageSize(),
                    inquiryVendorMaterialFilterDTO.getOrderType(), inquiryVendorMaterialFilterDTO.getOrderField());
        }

        List<InquiryVendorMaterial> inquiryVendorMaterialList = queryInquiryVendorMaterialList(inquiryVendorMaterialFilterDTO);

        PageInfo<InquiryVendorMaterial> pageInfo = new PageInfo<>(inquiryVendorMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inquiryVendorMaterialList));

        return pageVO;
    }

    @Override
    public InquiryVendorMaterialVO queryInquiryVendorMaterialById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

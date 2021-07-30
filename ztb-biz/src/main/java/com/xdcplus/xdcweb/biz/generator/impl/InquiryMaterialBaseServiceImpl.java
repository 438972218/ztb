package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.InquiryMaterialBaseService;
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
 * 询价物品(InquiryMaterial)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:08
 */
public class InquiryMaterialBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<InquiryMaterial, InquiryMaterialVO, InquiryMaterial, InquiryMaterialMapper> implements InquiryMaterialBaseService<S, T, E> {

    @Autowired
    protected InquiryMaterialMapper inquiryMaterialMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveInquiryMaterial(InquiryMaterialDTO inquiryMaterialDTO) {

        InquiryMaterial inquiryMaterial = inquiryMaterialMapper.selectById(inquiryMaterialDTO.getId());
        if (ObjectUtil.isNotNull(inquiryMaterial)) {
            log.error("saveInquiryMaterial() The InquiryMaterial already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inquiryMaterial = new InquiryMaterial();
        BeanUtil.copyProperties(inquiryMaterialDTO, inquiryMaterial);
        inquiryMaterial.setCreatedTime(DateUtil.current());
        inquiryMaterial.setDeleted(0);

        return this.save(inquiryMaterial);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateInquiryMaterial(InquiryMaterialDTO inquiryMaterialDTO) {

        InquiryMaterial inquiryMaterial = this.getById(inquiryMaterialDTO.getId());
        if (ObjectUtil.isNull(inquiryMaterial)) {
            log.error("updateInquiryMaterial() The InquiryMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(inquiryMaterialDTO, inquiryMaterial);
        inquiryMaterial.setUpdatedUser(inquiryMaterialDTO.getUpdatedUser());
        inquiryMaterial.setUpdatedTime(DateUtil.current());

        return this.updateById(inquiryMaterial);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<InquiryMaterial> inquiryMaterialList) {

        inquiryMaterialList.forEach(inquiryMaterial -> {
            InquiryMaterial inquiryMaterialParam = new InquiryMaterial();
            inquiryMaterialParam.setId(inquiryMaterial.getId());
            if (ObjectUtil.isNotNull(inquiryMaterial.getId())) {
                inquiryMaterial.setId(inquiryMaterial.getId());
                inquiryMaterial.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<InquiryMaterial> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(InquiryMaterial::getId, inquiryMaterial.getId());
                update(inquiryMaterial, lambdaUpdate);
            } else {
                inquiryMaterial.setCreatedTime(DateUtil.current());
                inquiryMaterial.setDeleted(0);
                save(inquiryMaterial);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<InquiryMaterialDTO> inquiryMaterialDTOList) {

        List<InquiryMaterial> inquiryMaterialList = BeanUtils.copyProperties(inquiryMaterialDTOList, InquiryMaterial.class);
        return saveOrUpdateBatch(inquiryMaterialList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteInquiryMaterialLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        InquiryMaterial inquiryMaterial = this.getById(id);

        if (ObjectUtil.isNull(inquiryMaterial)) {
            log.error("deleteInquiryMaterial() The InquiryMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        inquiryMaterial.setDeleted(1);

        return this.updateById(inquiryMaterial);
    }

    private List<InquiryMaterial> queryInquiryMaterialList(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {
        if (ObjectUtil.isNull(inquiryMaterialFilterDTO)) {
            inquiryMaterialFilterDTO = new InquiryMaterialFilterDTO();
        }
        inquiryMaterialFilterDTO.setDeleted(0);
        InquiryMaterialQuery inquiryMaterialQuery = BeanUtil.copyProperties(inquiryMaterialFilterDTO, InquiryMaterialQuery.class);

        return inquiryMaterialMapper.queryInquiryMaterial(inquiryMaterialQuery);
    }

    @Override
    public List<InquiryMaterialVO> queryInquiryMaterialVOList(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {
        if (ObjectUtil.isNull(inquiryMaterialFilterDTO)) {
            inquiryMaterialFilterDTO = new InquiryMaterialFilterDTO();
        }
        inquiryMaterialFilterDTO.setDeleted(0);
        return this.objectConversion(queryInquiryMaterialList(inquiryMaterialFilterDTO));
    }

    @Override
    public PageVO<InquiryMaterialVO> queryInquiryMaterial(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {
        if (ObjectUtil.isNull(inquiryMaterialFilterDTO)) {
            inquiryMaterialFilterDTO = new InquiryMaterialFilterDTO();
        }
        inquiryMaterialFilterDTO.setDeleted(0);
        PageVO<InquiryMaterialVO> pageVO = new PageVO<>();

        if (inquiryMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquiryMaterialFilterDTO.getCurrentPage(), inquiryMaterialFilterDTO.getPageSize(),
                    inquiryMaterialFilterDTO.getOrderType(), inquiryMaterialFilterDTO.getOrderField());
        }

        List<InquiryMaterial> inquiryMaterialList = queryInquiryMaterialList(inquiryMaterialFilterDTO);

        PageInfo<InquiryMaterial> pageInfo = new PageInfo<>(inquiryMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inquiryMaterialList));

        return pageVO;
    }

    @Override
    public InquiryMaterialVO queryInquiryMaterialById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

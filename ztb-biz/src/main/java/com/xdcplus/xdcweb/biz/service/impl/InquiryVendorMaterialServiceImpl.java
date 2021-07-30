package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendor;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorVO;
import com.xdcplus.xdcweb.biz.generator.impl.InquiryVendorMaterialBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryVendorMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorMaterialVO;
import com.xdcplus.xdcweb.biz.service.InquiryMaterialService;
import com.xdcplus.xdcweb.biz.service.InquiryVendorMaterialService;
import com.xdcplus.xdcweb.biz.service.InquiryVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 询价供应商物品(InquiryVendorMaterial)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:26
 */
@Slf4j
@Service("inquiryVendorMaterialService")
public class InquiryVendorMaterialServiceImpl extends InquiryVendorMaterialBaseServiceImpl<InquiryVendorMaterial, InquiryVendorMaterialVO, InquiryVendorMaterial, InquiryVendorMaterialMapper> implements InquiryVendorMaterialService {

    @Autowired
    private InquiryVendorService inquiryVendorService;

    @Autowired
    private InquiryMaterialService inquiryMaterialService;

    @Override
    public List<InquiryVendorMaterial> queryInquiryVendorMaterialsDesc(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (inquiryVendorMaterialFilterDTO.getMaterialId() != null) {
            queryWrapper.eq("material_id", inquiryVendorMaterialFilterDTO.getMaterialId());
        }
        if (inquiryVendorMaterialFilterDTO.getInquiryVendorId() != null) {
            queryWrapper.eq("inquiry_vendor_id", inquiryVendorMaterialFilterDTO.getInquiryVendorId());
        }
        if (inquiryVendorMaterialFilterDTO.getStatus() != null) {
            queryWrapper.eq("status", inquiryVendorMaterialFilterDTO.getStatus());
        }
        queryWrapper.orderByDesc("offering_number");
        List<InquiryVendorMaterial> inquiryVendorMaterials = inquiryVendorMaterialMapper.selectList(queryWrapper);

        return inquiryVendorMaterials;

    }

    @Override
    public Integer getOfferingNumber(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {
        List<InquiryVendorMaterial> inquiryVendorMaterials = queryInquiryVendorMaterialsDesc(inquiryVendorMaterialFilterDTO);
        if (CollectionUtil.isEmpty(inquiryVendorMaterials)) {
            return 1;
        } else {
            return inquiryVendorMaterials.get(0).getOfferingNumber() + 1;
        }
    }

    @Override
    public PageVO<InquiryVendorMaterialVO> queryPageVODesc(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {
        PageVO<InquiryVendorMaterialVO> pageVO = new PageVO<>();

        if (inquiryVendorMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquiryVendorMaterialFilterDTO.getCurrentPage(), inquiryVendorMaterialFilterDTO.getPageSize(),
                    inquiryVendorMaterialFilterDTO.getOrderType(), inquiryVendorMaterialFilterDTO.getOrderField());
        }

        List<InquiryVendorMaterial> inquiryVendorMaterialList = queryInquiryVendorMaterialsDesc(inquiryVendorMaterialFilterDTO);

        PageInfo<InquiryVendorMaterial> pageInfo = new PageInfo<>(inquiryVendorMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inquiryVendorMaterialList));

        return pageVO;
    }

    @Override
    public List<InquiryVendorMaterialVO> queryNewInquiryVendorMaterialByMaterialId(InquiryVendorMaterialQuery inquiryVendorMaterialQuery) {
        List<InquiryVendorMaterial> inquiryVendorMaterials = inquiryVendorMaterialMapper.queryNewInquiryVendorMaterialByMaterialId(inquiryVendorMaterialQuery);
        if (CollUtil.isEmpty(inquiryVendorMaterials)) {
            return null;
        }
        List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS = this.objectConversion(inquiryVendorMaterials);
        combineInquiryVendorVO(inquiryVendorMaterialVOS);
        return inquiryVendorMaterialVOS;
    }

    @Override
    public List<InquiryVendorMaterialVO> queryNewInquiryVendorMaterialByMaterialIdWithPricing(InquiryVendorMaterialQuery inquiryVendorMaterialQuery) {
        List<InquiryVendorMaterial> inquiryVendorMaterials = inquiryVendorMaterialMapper.queryNewInquiryVendorMaterialByMaterialId(inquiryVendorMaterialQuery);
        if (CollUtil.isEmpty(inquiryVendorMaterials)) {
            return null;
        }
        List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS = this.objectConversion(inquiryVendorMaterials);
        inquiryVendorMaterialVOS.forEach(inquiryVendorMaterialVO -> {
            if (inquiryVendorMaterialVO.getMaterialId() != null) {
                InquiryMaterialVO inquiryMaterialVO = inquiryMaterialService.queryInquiryMaterialById(inquiryVendorMaterialVO.getMaterialId());
                inquiryVendorMaterialVO.setMaterialQuantity(inquiryMaterialVO.getQuantity());
            }
        });
        combineInquiryVendorVO(inquiryVendorMaterialVOS);
        return inquiryVendorMaterialVOS;
    }

    @Override
    public Boolean dicker(InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        Long inquirySheetId = inquiryVendorMaterialDTO.getInquirySheetId();
        List<InquiryVendorMaterial> inquiryVendorMaterials = inquiryVendorMaterialMapper.queryOfferingSaveInquiryVendorMaterialBySheetId(inquiryVendorMaterialDTO.getInquirySheetId());

        for (InquiryVendorMaterial inquiryVendorMaterial : inquiryVendorMaterials) {
            inquiryVendorMaterial.setStatus("提交");
            //修改还价信息
            Boolean result = updateInquiryVendorMaterial(BeanUtil.copyProperties(inquiryVendorMaterial, InquiryVendorMaterialDTO.class));
            //判断是否放弃询价
            String abandonInquiry = inquiryVendorMaterial.getAbandonInquiry();
            if (abandonInquiry != null) {
                //修改供应商状态
                InquiryVendor inquiryVendor = new InquiryVendor();
                inquiryVendor.setId(inquiryVendorMaterial.getInquiryVendorId());
                inquiryVendor.setVendorStatus("已拒绝");
                inquiryVendorService.updateById(inquiryVendor);
            } else {
                //修改供应商状态
                InquiryVendor inquiryVendor = new InquiryVendor();
                inquiryVendor.setId(inquiryVendorMaterial.getInquiryVendorId());
                inquiryVendor.setVendorStatus("未报价");
                inquiryVendorService.updateById(inquiryVendor);
                //新增空报价明细
                InquiryVendorMaterialDTO newInquiryVendorMaterialDTO = new InquiryVendorMaterialDTO();
                newInquiryVendorMaterialDTO.setMaterialId(inquiryVendorMaterial.getMaterialId());
                newInquiryVendorMaterialDTO.setInquiryVendorId(inquiryVendorMaterial.getInquiryVendorId());
                //得到报价次数
                Integer offeringNumber = getOfferingNumber(BeanUtil.copyProperties(newInquiryVendorMaterialDTO, InquiryVendorMaterialFilterDTO.class));
                newInquiryVendorMaterialDTO.setOfferingNumber(offeringNumber);
                newInquiryVendorMaterialDTO.setStatus("保存");
                InquiryVendorMaterialVO inquiryVendorMaterialVO = queryInquiryVendorMaterialById(inquiryVendorMaterial.getId());
                newInquiryVendorMaterialDTO.setOfferingPeriodFrom(inquiryVendorMaterialVO.getOfferingPeriodFrom());
                newInquiryVendorMaterialDTO.setOfferingPeriodTo(inquiryVendorMaterialVO.getOfferingPeriodTo());
                newInquiryVendorMaterialDTO.setDeliveryDate(inquiryVendorMaterialVO.getDeliveryDate());
                newInquiryVendorMaterialDTO.setMinSupplyQuantity(inquiryVendorMaterialVO.getMinSupplyQuantity());
                newInquiryVendorMaterialDTO.setUnit(inquiryVendorMaterialVO.getUnit());
                saveInquiryVendorMaterial(newInquiryVendorMaterialDTO);
            }
        }

        return true;
    }

    @Override
    public Boolean submitOffer(InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {
        List<InquiryVendorMaterial> inquiryVendorMaterials = inquiryVendorMaterialMapper.querySaveInquiryVendorMaterialBySheetId(inquiryVendorMaterialDTO.getInquirySheetId());
        if (CollUtil.isEmpty(inquiryVendorMaterials)) {
            log.error("InquiryVendorMaterialServiceImpl.submitOffer() get saveInquiryVendorMaterial failed");
            throw new InvException(ResponseEnum.INQUIRY_VENDOR_MATERIAL_SELECT_FAIL);
        }
        //判断所有保存状态的明细的报价是否为空
        Long priceNullCount = inquiryVendorMaterials.stream().filter(inquiryVendorMaterial -> inquiryVendorMaterial.getPrice() == null).count();
        if (priceNullCount != 0) {
            log.error("InquiryVendorMaterialServiceImpl.submitOffer() price is null");
            throw new InvException(ResponseEnum.INQUIRY_VENDOR_MATERIAL_PRICE_NULL);
        }
        //修改明细状态
        inquiryVendorMaterials.forEach(inquiryVendorMaterial -> {
            inquiryVendorMaterial.setStatus("提交");
            inquiryVendorMaterialMapper.updateById(inquiryVendorMaterial);
        });
        //修改供应商状态
        InquiryVendor inquiryVendor = new InquiryVendor();
        inquiryVendor.setId(inquiryVendorMaterialDTO.getInquiryVendorId());
        inquiryVendor.setVendorStatus("已报价");

        return inquiryVendorService.updateById(inquiryVendor);
    }

    @Override
    public List<InquiryVendorMaterialVO> queryAllocatedRanking(InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("material_id", inquiryVendorMaterialDTO.getMaterialId());
        queryWrapper.isNotNull("allotted_quantity");
        List<InquiryVendorMaterial> inquiryVendorMaterials = inquiryVendorMaterialMapper.selectList(queryWrapper);
        List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS = this.objectConversion(inquiryVendorMaterials);
        combineInquiryVendorVO(inquiryVendorMaterialVOS);

        return inquiryVendorMaterialVOS;
    }

    private void combineInquiryVendorVO(List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS) {
        inquiryVendorMaterialVOS.forEach(inquiryVendorMaterialVO -> {
            if (inquiryVendorMaterialVO.getInquiryVendorId() != null) {
                InquiryVendorVO inquiryVendorVO = inquiryVendorService.queryInquiryVendorById(inquiryVendorMaterialVO.getInquiryVendorId());
                inquiryVendorMaterialVO.setInquiryVendorVO(inquiryVendorVO);
            }
        });
    }



}

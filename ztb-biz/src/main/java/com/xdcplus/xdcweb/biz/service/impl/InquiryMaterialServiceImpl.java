package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorVO;
import com.xdcplus.xdcweb.biz.generator.impl.InquiryMaterialBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryMaterialVO;
import com.xdcplus.xdcweb.biz.service.InquiryMaterialService;
import com.xdcplus.xdcweb.biz.service.InquiryVendorMaterialService;
import com.xdcplus.xdcweb.biz.service.InquiryVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 询价物品(InquiryMaterial)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:05
 */
@Slf4j
@Service("inquiryMaterialService")
public class InquiryMaterialServiceImpl extends InquiryMaterialBaseServiceImpl<InquiryMaterial, InquiryMaterialVO, InquiryMaterial, InquiryMaterialMapper> implements InquiryMaterialService {

    @Autowired
    private InquiryVendorMaterialService inquiryVendorMaterialService;

    @Autowired
    private InquiryVendorService inquiryVendorService;

    @Override
    public List<InquiryMaterialVO> queryInquiryMaterialsWithDicker(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {
        List<InquiryMaterialVO> inquiryMaterialVOS = queryInquiryMaterialVOList(inquiryMaterialFilterDTO);
        if (CollUtil.isEmpty(inquiryMaterialVOS)) {
            return null;
        }
        for (InquiryMaterialVO inquiryMaterialVO : inquiryMaterialVOS) {
            InquiryVendorMaterialQuery inquiryVendorMaterialQuery = new InquiryVendorMaterialQuery();
            inquiryVendorMaterialQuery.setMaterialId(inquiryMaterialVO.getId());
            List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS = inquiryVendorMaterialService.queryNewInquiryVendorMaterialByMaterialId(inquiryVendorMaterialQuery);
            if (CollUtil.isEmpty(inquiryVendorMaterialVOS)) {
                inquiryMaterialVO.setOfferInformation("已提交");
            } else {
                inquiryVendorMaterialVOS.forEach(inquiryVendorMaterialVO -> {
                    if (inquiryVendorMaterialVO.getInquiryVendorVO().getVendorStatus().equals("已报价")) {
                        inquiryMaterialVO.setOfferInformation("未保存");
                    } else {
                        inquiryMaterialVO.setOfferInformation("已保存");
                    }
                });
            }
        }

        return inquiryMaterialVOS;
    }

    @Override
    public List<InquiryMaterialVO> queryInquiryMaterialsWithPricing(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {
        List<InquiryMaterialVO> inquiryMaterialVOS = queryInquiryMaterialVOList(inquiryMaterialFilterDTO);
        if (CollUtil.isEmpty(inquiryMaterialVOS)) {
            return null;
        }
        for (InquiryMaterialVO inquiryMaterialVO : inquiryMaterialVOS) {
            InquiryVendorMaterialQuery inquiryVendorMaterialQuery = new InquiryVendorMaterialQuery();
            inquiryVendorMaterialQuery.setMaterialId(inquiryMaterialVO.getId());
            List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS = inquiryVendorMaterialService.queryNewInquiryVendorMaterialByMaterialId(inquiryVendorMaterialQuery);
            if (CollUtil.isNotEmpty(inquiryVendorMaterialVOS)) {
                Double costTotalPrice = 0.0;
                Double totalQuantity = 0.0;
                for (InquiryVendorMaterialVO inquiryVendorMaterialVO : inquiryVendorMaterialVOS) {
                    if (inquiryVendorMaterialVO.getAllottedQuantity() != null) {
                        costTotalPrice += (inquiryVendorMaterialVO.getPrice() * Double.parseDouble(inquiryVendorMaterialVO.getAllottedQuantity()));
                        totalQuantity += Double.parseDouble(inquiryVendorMaterialVO.getAllottedQuantity());
                    }
                }
                if (costTotalPrice != 0.0) {
                    Double costPrice = costTotalPrice / totalQuantity;
                    inquiryMaterialVO.setCostPrice(costPrice);
                    inquiryMaterialVO.setCostTotalPrice(costTotalPrice);
                }
            }
        }

        return inquiryMaterialVOS;
    }

    @Override
    public List<InquiryMaterialVO> queryInquiryMaterialsWithVendor(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {
        Long sheetId = inquiryMaterialFilterDTO.getInquirySheetId();
//        Long inquiryVendorId = inquiryMaterialFilterDTO.getInquiryVendorId();
        String vendorCode = inquiryMaterialFilterDTO.getVendorCode();

        InquiryVendorFilterDTO inquiryVendorFilterDTO = new InquiryVendorFilterDTO();
        inquiryVendorFilterDTO.setInquirySheetId(sheetId);
        inquiryVendorFilterDTO.setVendorCode(vendorCode);
        List<InquiryVendorVO> inquiryVendorVOS = inquiryVendorService.queryInquiryVendorVOList(inquiryVendorFilterDTO);
        if (CollUtil.isEmpty(inquiryVendorVOS)) {
            log.error("queryInquiryMaterialsWithVendor() The inquiryVendor not found");
            throw new InvException(ResponseEnum.INQUIRY_VENDOR_SELECT_FAIL);
        }
        Long inquiryVendorId = inquiryVendorVOS.get(0).getInquirySheetId();
        List<InquiryMaterialVO> inquiryMaterialVOS = queryInquiryMaterialVOList(inquiryMaterialFilterDTO);
        if (CollUtil.isEmpty(inquiryMaterialVOS)) {
            return null;
        }
        for (InquiryMaterialVO inquiryMaterialVO : inquiryMaterialVOS) {
            //查询出最新报价
            InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO = new InquiryVendorMaterialFilterDTO();
            inquiryVendorMaterialFilterDTO.setMaterialId(inquiryMaterialVO.getId());
            inquiryVendorMaterialFilterDTO.setInquiryVendorId(inquiryVendorId);
            inquiryVendorMaterialFilterDTO.setStatus("提交");
            List<InquiryVendorMaterial> inquiryVendorMaterials = inquiryVendorMaterialService.queryInquiryVendorMaterialsDesc(inquiryVendorMaterialFilterDTO);
            if (CollUtil.isNotEmpty(inquiryVendorMaterials)) {
                inquiryMaterialVO.setNewPrice(inquiryVendorMaterials.get(0).getPrice());
                inquiryMaterialVO.setNewDickerPrice(inquiryVendorMaterials.get(0).getDickerPrice());
                inquiryMaterialVO.setNewDickerReason(inquiryVendorMaterials.get(0).getDickerReason());
            }
            //查询出上一次报价
            InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO1 = new InquiryVendorMaterialFilterDTO();
            inquiryVendorMaterialFilterDTO1.setMaterialId(inquiryMaterialVO.getId());
            inquiryVendorMaterialFilterDTO1.setInquiryVendorId(inquiryVendorId);
            inquiryVendorMaterialFilterDTO1.setStatus("保存");
            List<InquiryVendorMaterial> inquiryVendorMaterials1 = inquiryVendorMaterialService.queryInquiryVendorMaterialsDesc(inquiryVendorMaterialFilterDTO1);
            if (CollUtil.isNotEmpty(inquiryVendorMaterials)) {
                inquiryMaterialVO.setLastPrice(inquiryVendorMaterials1.get(0).getPrice());
            }
        }

        return inquiryMaterialVOS;
    }

}

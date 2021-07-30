package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.generator.impl.InquirySheetBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquirySheetMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquirySheet;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquirySheetVO;
import com.xdcplus.xdcweb.biz.service.InquiryAttachmentService;
import com.xdcplus.xdcweb.biz.service.InquiryMaterialService;
import com.xdcplus.xdcweb.biz.service.InquirySheetService;
import com.xdcplus.xdcweb.biz.service.InquiryVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 询价单(InquirySheet)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:10
 */
@Slf4j
@Service("inquirySheetService")
public class InquirySheetServiceImpl extends InquirySheetBaseServiceImpl<InquirySheet, InquirySheetVO, InquirySheet, InquirySheetMapper> implements InquirySheetService {

    @Autowired
    private InquiryMaterialService inquiryMaterialService;

    @Autowired
    private InquiryVendorService inquiryVendorService;

    @Autowired
    private InquiryAttachmentService inquiryAttachmentService;

    @Override
    public InquirySheetVO saveInquirySheetReturnVO(InquirySheetDTO inquirySheetDTO) {
        InquirySheet inquirySheet = inquirySheetMapper.selectById(inquirySheetDTO.getId());
        if (ObjectUtil.isNotNull(inquirySheet)) {
            log.error("saveInquirySheet() The InquirySheet already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inquirySheet = new InquirySheet();
        BeanUtil.copyProperties(inquirySheetDTO, inquirySheet);
        inquirySheet.setCreatedTime(DateUtil.current());

        boolean result = this.save(inquirySheet);
        InquirySheetVO inquirySheetVO = BeanUtil.copyProperties(inquirySheet, InquirySheetVO.class);
        if (result) {
            return inquirySheetVO;
        } else {
            return null;
        }
    }

    @Override
    public InquirySheetVO showInquirySheetById(Long id) {
        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        InquirySheetVO inquirySheetVO = this.objectConversion(this.getById(id));

        InquiryMaterialFilterDTO inquiryMaterialFilterDTO = new InquiryMaterialFilterDTO();
        inquiryMaterialFilterDTO.setInquirySheetId(id);
        inquirySheetVO.setInquiryMaterialVOS(inquiryMaterialService.queryInquiryMaterialVOList(inquiryMaterialFilterDTO));

        InquiryVendorFilterDTO inquiryVendorFilterDTO = new InquiryVendorFilterDTO();
        inquiryVendorFilterDTO.setInquirySheetId(id);
        inquirySheetVO.setInquiryVendorVOS(inquiryVendorService.queryInquiryVendorVOList(inquiryVendorFilterDTO));

        InquiryAttachmentFilterDTO inquiryAttachmentFilterDTO = new InquiryAttachmentFilterDTO();
        inquiryAttachmentFilterDTO.setInquirySheetId(id);
        inquirySheetVO.setInquiryAttachmentVOS(inquiryAttachmentService.queryInquiryAttachmentVOList(inquiryAttachmentFilterDTO));

        return inquirySheetVO;
    }

    @Override
    public PageVO<InquirySheetVO> queryInquirySheetByVendor(InquirySheetFilterDTO inquirySheetFilterDTO) {

        PageVO<InquirySheetVO> pageVO = new PageVO<>();

        if (inquirySheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquirySheetFilterDTO.getCurrentPage(), inquirySheetFilterDTO.getPageSize(),
                    inquirySheetFilterDTO.getOrderType(), inquirySheetFilterDTO.getOrderField());
        }

        //得到requestId不为空的BidSheet
        List<InquirySheet> inquirySheetList = inquirySheetMapper.selectList(new QueryWrapper<InquirySheet>().isNotNull("request_id").eq("deleted", 0));

        List<InquirySheetVO> inquirySheetVOS = new ArrayList<>();
        for (InquirySheet inquirySheet : inquirySheetList) {
            InquirySheetVO inquirySheetVO = BeanUtil.copyProperties(inquirySheet, InquirySheetVO.class);
            //判断询价方式
            if (inquirySheet.getInquiryMode().equals("公开")) {
                //供应商状态
                List<InquiryVendor> inquiryVendors = inquiryVendorService.list(new QueryWrapper<InquiryVendor>()
                        .eq("vendor_code", inquirySheetFilterDTO.getVendorCode())
                        .eq("inquiry_sheet_id", inquirySheet.getId())
                        .eq("deleted", 0));
                if (CollUtil.isEmpty(inquiryVendors)) {
                    inquirySheetVO.setVendorStatus("待参与");
                }
                if (inquirySheetVO.getRequestId() == 0) {
                    continue;
                }
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(inquirySheetVO.getRequestId());
                inquirySheetVO.setOddNumber(requestVO.getOddNumber());
                inquirySheetVO.setRequestStatusName(requestVO.getStatus().getName());
            } else {
                //供应商状态
                List<InquiryVendor> inquiryVendors = inquiryVendorService.list(new QueryWrapper<InquiryVendor>()
                        .eq("vendor_code", inquirySheetFilterDTO.getVendorCode())
                        .eq("inquiry_sheet_id", inquirySheet.getId())
                        .eq("deleted", 0));
                if (CollUtil.isEmpty(inquiryVendors)) {
                    continue;
                }
                InquiryVendor inquiryVendor = inquiryVendors.get(0);

                if (inquirySheetVO.getRequestId() == 0) {
                    continue;
                }
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(inquirySheetVO.getRequestId());
                inquirySheetVO.setOddNumber(requestVO.getOddNumber());
                inquirySheetVO.setRequestStatusName(requestVO.getStatus().getName());
                if(StrUtil.equals(requestVO.getStatus().getName(),"已发布")){
                    if (inquiryVendor.getVendorStatus() == null) {
                        inquirySheetVO.setVendorStatus("待参与");
                    } else {
                        inquirySheetVO.setVendorStatus(inquiryVendor.getVendorStatus());
                    }
                }else{
                    if (inquiryVendor.getVendorStatus() == null) {
                        inquirySheetVO.setVendorStatus("未参与");
                    } else {
                        inquirySheetVO.setVendorStatus(inquiryVendor.getVendorStatus());
                    }
                }

            }
            inquirySheetVOS.add(inquirySheetVO);
        }

        PageInfo<InquirySheetVO> pageInfo = new PageInfo<>(inquirySheetVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, inquirySheetVOS);

        return pageVO;
    }

    @Override
    public PageVO<InquirySheetVO> queryInquirySheetWithRequest(InquirySheetFilterDTO inquirySheetFilterDTO) {
        PageVO<InquirySheetVO> pageVO = new PageVO<>();

        if (inquirySheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquirySheetFilterDTO.getCurrentPage(), inquirySheetFilterDTO.getPageSize(),
                    inquirySheetFilterDTO.getOrderType(), inquirySheetFilterDTO.getOrderField());
        }

        //得到requestId不为空的BidSheet
        List<InquirySheetVO> inquirySheetVOS = queryInquirySheetVOList(null);
        if (CollectionUtil.isEmpty(inquirySheetVOS)) {
            return null;
        }
        for (InquirySheetVO inquirySheetVO : inquirySheetVOS) {
            if (inquirySheetVO.getRequestId() == null || inquirySheetVO.getRequestId() == 0) {
                inquirySheetVO.setRequestStatusName("待提交");
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(inquirySheetVO.getRequestId());
                inquirySheetVO.setOddNumber(requestVO.getOddNumber());
                inquirySheetVO.setRequestStatusName(requestVO.getStatus().getName());
            }
        }

        PageInfo<InquirySheetVO> pageInfo = new PageInfo<>(inquirySheetVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, inquirySheetVOS);

        return pageVO;
    }


}

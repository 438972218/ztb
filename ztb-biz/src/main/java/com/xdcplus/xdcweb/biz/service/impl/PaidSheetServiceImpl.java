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
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.generator.impl.PaidSheetBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidSheetMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidSheet;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.xdcweb.biz.service.PaidAttachmentService;
import com.xdcplus.xdcweb.biz.service.PaidMaterialService;
import com.xdcplus.xdcweb.biz.service.PaidSheetService;
import com.xdcplus.xdcweb.biz.service.PaidVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 竞价单(PaidSheet)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:44
 */
@Slf4j
@Service("paidSheetService")
public class PaidSheetServiceImpl extends PaidSheetBaseServiceImpl<PaidSheet, PaidSheetVO, PaidSheet, PaidSheetMapper> implements PaidSheetService {

    @Autowired
    private PaidMaterialService paidMaterialService;

    @Autowired
    private PaidVendorService paidVendorService;

    @Autowired
    private PaidAttachmentService paidAttachmentService;

    @Override
    public PaidSheetVO savePaidSheetReturnVO(PaidSheetDTO paidSheetDTO) {
        PaidSheet paidSheet = paidSheetMapper.selectById(paidSheetDTO.getId());
        if (ObjectUtil.isNotNull(paidSheet)) {
            log.error("savePaidSheet() The PaidSheet already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        paidSheet = new PaidSheet();
        BeanUtil.copyProperties(paidSheetDTO, paidSheet);
        paidSheet.setCreatedTime(DateUtil.current());

        boolean result = this.save(paidSheet);
        PaidSheetVO paidSheetVO = BeanUtil.copyProperties(paidSheet, PaidSheetVO.class);
        if (result) {
            return paidSheetVO;
        } else {
            return null;
        }
    }

    @Override
    public PaidSheetVO showPaidSheetById(Long id) {
        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        PaidSheetVO paidSheetVO = this.objectConversion(this.getById(id));

        PaidMaterialFilterDTO paidMaterialFilterDTO = new PaidMaterialFilterDTO();
        paidMaterialFilterDTO.setPaidSheetId(id);
        paidSheetVO.setPaidMaterialVOS(paidMaterialService.queryPaidMaterialVOList(paidMaterialFilterDTO));

        PaidVendorFilterDTO paidVendorFilterDTO = new PaidVendorFilterDTO();
        paidVendorFilterDTO.setPaidSheetId(id);
        paidSheetVO.setPaidVendorVOS(paidVendorService.queryPaidVendorVOList(paidVendorFilterDTO));

        PaidAttachmentFilterDTO paidAttachmentFilterDTO = new PaidAttachmentFilterDTO();
        paidAttachmentFilterDTO.setPaidSheetId(id);
        paidSheetVO.setPaidAttachmentVOS(paidAttachmentService.queryPaidAttachmentVOList(paidAttachmentFilterDTO));

        return paidSheetVO;
    }

    @Override
    public PageVO<PaidSheetVO> queryPaidSheetByVendor(PaidSheetFilterDTO paidSheetFilterDTO) {
        PageVO<PaidSheetVO> pageVO = new PageVO<>();

        if (paidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidSheetFilterDTO.getCurrentPage(), paidSheetFilterDTO.getPageSize(),
                    paidSheetFilterDTO.getOrderType(), paidSheetFilterDTO.getOrderField());
        }

        //得到requestId不为空的BidSheet
        List<PaidSheet> paidSheetList = paidSheetMapper.selectList(new QueryWrapper<PaidSheet>().isNotNull("request_id").eq("deleted", "0"));

        List<PaidSheetVO> paidSheetVOS = new ArrayList<>();
        for (PaidSheet paidSheet : paidSheetList) {
            //供应商状态
            List<PaidVendor> paidVendors = paidVendorService.list(new QueryWrapper<PaidVendor>()
                    .eq("vendor_code", paidSheetFilterDTO.getVendorCode())
                    .eq("paid_sheet_id", paidSheet.getId()));
            if (CollUtil.isEmpty(paidVendors)) {
                continue;
            }
            PaidVendor paidVendor = paidVendors.get(0);
            PaidSheetVO paidSheetVO = BeanUtil.copyProperties(paidSheet, PaidSheetVO.class);
            if (paidVendor.getVendorStatus() == null) {
                paidSheetVO.setVendorStatus("待参与");
            } else {
                paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
            }


            if (paidSheetVO.getRequestId() == null || paidSheetVO.getRequestId() == 0) {
                continue;
            }
            //表单状态
            RequestVO requestVO = IeRequestResponseUtils.queryRequestById(paidSheetVO.getRequestId());

            paidSheetVO.setOddNumber(requestVO.getOddNumber());
            paidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            if(StrUtil.equals(requestVO.getStatus().getName(),"已发布")){
                if (paidVendor.getVendorStatus() == null) {
                    paidSheetVO.setVendorStatus("待参与");
                } else {
                    paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
                }
            }else{
                if (paidVendor.getVendorStatus() == null) {
                    paidSheetVO.setVendorStatus("未参与");
                } else {
                    paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
                }
            }
            paidSheetVOS.add(paidSheetVO);
        }

        PageInfo<PaidSheetVO> pageInfo = new PageInfo<>(paidSheetVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, paidSheetVOS);

        return pageVO;
    }

    @Override
    public PageVO<PaidSheetVO> queryPaidSheetWithRequest(PaidSheetFilterDTO paidSheetFilterDTO) {
        PageVO<PaidSheetVO> pageVO = new PageVO<>();

        if (paidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidSheetFilterDTO.getCurrentPage(), paidSheetFilterDTO.getPageSize(),
                    paidSheetFilterDTO.getOrderType(), paidSheetFilterDTO.getOrderField());
        }

        List<PaidSheetVO> paidSheetVOS = queryPaidSheetVOList(paidSheetFilterDTO);
        if (CollectionUtil.isEmpty(paidSheetVOS)) {
            return null;
        }
        for (PaidSheetVO paidSheetVO : paidSheetVOS) {
            if (paidSheetVO.getRequestId() == null || paidSheetVO.getRequestId() == 0) {
                paidSheetVO.setRequestStatusName("待提交");
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(paidSheetVO.getRequestId());
                paidSheetVO.setOddNumber(requestVO.getOddNumber());
                paidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            }
        }
        if(paidSheetFilterDTO.getRequestStatusName()!=null){
            paidSheetVOS = paidSheetVOS.stream().filter(a ->
                    StrUtil.equals(a.getRequestStatusName(), paidSheetFilterDTO.getRequestStatusName())).collect(Collectors.toList());
        }

        PageInfo<PaidSheetVO> pageInfo = new PageInfo<>(paidSheetVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, paidSheetVOS);

        return pageVO;
    }



}

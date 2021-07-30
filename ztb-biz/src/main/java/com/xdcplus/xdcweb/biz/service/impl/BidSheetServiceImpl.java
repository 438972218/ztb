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
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.generator.impl.BidSheetBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidSheetMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSheet;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSheetVO;
import com.xdcplus.xdcweb.biz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * bid招标单(BidSheet)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:04
 */
@Slf4j
@Service("bidSheetService")
public class BidSheetServiceImpl extends BidSheetBaseServiceImpl<BidSheet, BidSheetVO, BidSheet, BidSheetMapper> implements BidSheetService {

    @Autowired
    private BidPreTrialItemService bidPreTrialItemService;

    @Autowired
    private BidSpecialistService bidSpecialistService;

    @Autowired
    private BidScoringElementsService bidScoringElementsService;

    @Autowired
    private BidMaterialService bidMaterialService;

    @Autowired
    private BidVendorService bidVendorService;

    @Autowired
    private BidAttachmentService bidAttachmentService;

    @Override
    public BidSheetVO saveBidSheetReturnVO(BidSheetDTO bidSheetDTO) {
        BidSheet bidSheet = bidSheetMapper.selectById(bidSheetDTO.getId());
        if (ObjectUtil.isNotNull(bidSheet)) {
            log.error("saveBidSheet() The BidSheet already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidSheet = new BidSheet();
        BeanUtil.copyProperties(bidSheetDTO, bidSheet);
        bidSheet.setCreatedTime(DateUtil.current());
        boolean result = this.save(bidSheet);
        BidSheetVO bidSheetVO = BeanUtil.copyProperties(bidSheet, BidSheetVO.class);
        if (result) {
            return bidSheetVO;
        } else {
            return null;
        }
    }

    @Override
    public BidSheetVO showBidSheetById(Long id) {
        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        BidSheetVO bidSheetVO = this.objectConversion(this.getById(id));

        BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO = new BidPreTrialItemFilterDTO();
        bidPreTrialItemFilterDTO.setBidSheetId(id);
        bidSheetVO.setBidPreTrialItemVOS(bidPreTrialItemService.queryBidPreTrialItemVOList(bidPreTrialItemFilterDTO));

        BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
        bidSpecialistFilterDTO.setBidSheetId(id);
        bidSheetVO.setBidSpecialistVOS(bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO));

        BidScoringElementsFilterDTO bidScoringElementsFilterDTO = new BidScoringElementsFilterDTO();
        bidScoringElementsFilterDTO.setBidSheetId(id);
        bidSheetVO.setBidScoringElementsVOS(bidScoringElementsService.queryBidScoringElementsVOList(bidScoringElementsFilterDTO));

        BidMaterialFilterDTO bidMaterialFilterDTO = new BidMaterialFilterDTO();
        bidMaterialFilterDTO.setBidSheetId(id);
        bidSheetVO.setBidMaterialVOS(bidMaterialService.queryBidMaterialVOList(bidMaterialFilterDTO));

        BidVendorFilterDTO bidVendorFilterDTO = new BidVendorFilterDTO();
        bidVendorFilterDTO.setBidSheetId(id);
        bidSheetVO.setBidVendorVOS(bidVendorService.queryBidVendorVOList(bidVendorFilterDTO));

        BidAttachmentFilterDTO bidAttachmentFilterDTO = new BidAttachmentFilterDTO();
        bidAttachmentFilterDTO.setBidSheetId(id);
        bidSheetVO.setBidAttachmentVOS(bidAttachmentService.queryBidAttachmentVOList(bidAttachmentFilterDTO));

        return bidSheetVO;
    }

    @Override
    public PageVO<BidSheetVO> queryBidSheetByVendor(BidSheetFilterDTO bidSheetFilterDTO) {
        PageVO<BidSheetVO> pageVO = new PageVO<>();

        if (bidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSheetFilterDTO.getCurrentPage(), bidSheetFilterDTO.getPageSize(),
                    bidSheetFilterDTO.getOrderType(), bidSheetFilterDTO.getOrderField());
        }

        //得到requestId不为空的BidSheet
        List<BidSheet> bidSheetList = bidSheetMapper.selectList(new QueryWrapper<BidSheet>().isNotNull("request_id"));

        List<BidSheetVO> bidSheetVOS = new ArrayList<>();
        for (BidSheet bidSheet : bidSheetList) {
            //供应商状态
            List<BidVendor> bidVendors = bidVendorService.list(new QueryWrapper<BidVendor>()
                    .eq("vendor_code", bidSheetFilterDTO.getVendorCode())
                    .eq("bid_sheet_id", bidSheet.getId()));
            if (CollUtil.isEmpty(bidVendors)) {
                continue;
            }
            BidVendor bidVendor = bidVendors.get(0);
            BidSheetVO bidSheetVO = BeanUtil.copyProperties(bidSheet, BidSheetVO.class);

            if (bidSheetVO.getRequestId() == null || bidSheetVO.getRequestId() == 0) {
                continue;
            }
            //表单状态
            RequestVO requestVO = IeRequestResponseUtils.queryRequestById(bidSheetVO.getRequestId());
            bidSheetVO.setOddNumber(requestVO.getOddNumber());
            bidSheetVO.setRequestStatusName(requestVO.getStatus().getName());

            if(StrUtil.equals(requestVO.getStatus().getName(),"待申请")){
                if (bidVendor.getVendorStatus() == null) {
                    bidSheetVO.setVendorStatus("待参与");
                } else {
                    bidSheetVO.setVendorStatus(bidVendor.getVendorStatus());
                }
            }else{
                if (bidVendor.getVendorStatus() == null) {
                    bidSheetVO.setVendorStatus("未参与");
                } else {
                    bidSheetVO.setVendorStatus(bidVendor.getVendorStatus());
                }
            }
            bidSheetVOS.add(bidSheetVO);
        }

        PageInfo<BidSheetVO> pageInfo = new PageInfo<>(bidSheetVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, bidSheetVOS);

        return pageVO;
    }

    @Override
    public PageVO<BidSheetVO> queryBidSheetWithRequest(BidSheetFilterDTO bidSheetFilterDTO) {
        PageVO<BidSheetVO> pageVO = new PageVO<>();

        if (bidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSheetFilterDTO.getCurrentPage(), bidSheetFilterDTO.getPageSize(),
                    bidSheetFilterDTO.getOrderType(), bidSheetFilterDTO.getOrderField());
        }

        //得到BidSheet
        List<BidSheetVO> bidSheetVOS = queryBidSheetVOList(null);
        if (CollectionUtil.isEmpty(bidSheetVOS)) {
            return null;
        }
        for (BidSheetVO bidSheetVO : bidSheetVOS) {
            if (bidSheetVO.getRequestId() == null || bidSheetVO.getRequestId() == 0) {
                bidSheetVO.setRequestStatusName("待提交");
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(bidSheetVO.getRequestId());
                bidSheetVO.setOddNumber(requestVO.getOddNumber());
                bidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            }
        }

        PageInfo<BidSheetVO> pageInfo = new PageInfo<>(bidSheetVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, bidSheetVOS);

        return pageVO;
    }


}

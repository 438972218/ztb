package com.xdcplus.xdcweb.biz.service.impl;

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
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.xdcweb.biz.generator.impl.BidVendorMaterialBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidVendorMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.service.BidMaterialService;
import com.xdcplus.xdcweb.biz.service.BidVendorMaterialService;
import com.xdcplus.xdcweb.biz.service.BidVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 招标投标方物品(BidVendorMaterial)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:12
 */
@Slf4j
@Service("bidVendorMaterialService")
public class BidVendorMaterialServiceImpl extends BidVendorMaterialBaseServiceImpl<BidVendorMaterial, BidVendorMaterialVO, BidVendorMaterial, BidVendorMaterialMapper> implements BidVendorMaterialService {

    @Autowired
    private BidVendorService bidVendorService;

    @Autowired
    private BidMaterialService bidMaterialService;

    @Override
    public List<BidVendorMaterial> queryBidVendorMaterialsDesc(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (bidVendorMaterialFilterDTO.getMaterialId() != null) {
            queryWrapper.eq("material_id", bidVendorMaterialFilterDTO.getMaterialId());
        }
        if (bidVendorMaterialFilterDTO.getBidVendorId() != null) {
            queryWrapper.eq("bid_vendor_id", bidVendorMaterialFilterDTO.getBidVendorId());
        }
        queryWrapper.orderByDesc("offering_number");
        List<BidVendorMaterial> bidVendorMaterials = bidVendorMaterialMapper.selectList(queryWrapper);

        return bidVendorMaterials;

    }

    @Override
    public Integer getOfferingNumber(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {
        List<BidVendorMaterial> bidVendorMaterials = queryBidVendorMaterialsDesc(bidVendorMaterialFilterDTO);
        if (CollectionUtil.isEmpty(bidVendorMaterials)) {
            return 1;
        } else {
            return bidVendorMaterials.get(0).getOfferingNumber() + 1;
        }
    }

    @Override
    public PageVO<BidVendorMaterialVO> queryPageVODesc(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {
        PageVO<BidVendorMaterialVO> pageVO = new PageVO<>();

        if (bidVendorMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidVendorMaterialFilterDTO.getCurrentPage(), bidVendorMaterialFilterDTO.getPageSize(),
                    bidVendorMaterialFilterDTO.getOrderType(), bidVendorMaterialFilterDTO.getOrderField());
        }

        List<BidVendorMaterial> bidVendorMaterialList = queryBidVendorMaterialsDesc(bidVendorMaterialFilterDTO);

        PageInfo<BidVendorMaterial> pageInfo = new PageInfo<>(bidVendorMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidVendorMaterialList));

        return pageVO;
    }

    @Override
    public List<BidVendorMaterialVO> queryNewBidVendorMaterialByMaterialId(BidVendorMaterialQuery bidVendorMaterialQuery) {
        List<BidVendorMaterial> bidVendorMaterials = bidVendorMaterialMapper.queryNewBidVendorMaterialByMaterialId(bidVendorMaterialQuery);
        if (CollUtil.isEmpty(bidVendorMaterials)) {
            return null;
        }
        List<BidVendorMaterialVO> bidVendorMaterialVOS = this.objectConversion(bidVendorMaterials);
        bidVendorMaterialVOS.forEach(bidVendorMaterialVO -> {
            if (bidVendorMaterialVO.getMaterialId() != null) {
                BidMaterialVO bidMaterialVO = bidMaterialService.queryBidMaterialById(bidVendorMaterialVO.getMaterialId());
                bidVendorMaterialVO.setMaterialQuantity(bidMaterialVO.getDemandedQuantity());
            }
        });
        combineBidVendorVO(bidVendorMaterialVOS);
        return bidVendorMaterialVOS;
    }

    @Override
    public Double getTotalPriceByBidVendorId(BidVendorMaterialQuery bidVendorMaterialQuery) {
        return bidVendorMaterialMapper.getTotalPriceByBidVendorId(bidVendorMaterialQuery);
    }

    @Override
    public Boolean submitOffer(BidVendorMaterialDTO bidVendorMaterialDTO) {
        List<BidVendorMaterial> bidVendorMaterials = bidVendorMaterialMapper.querySaveBidVendorMaterialBySheetId(bidVendorMaterialDTO.getBidSheetId());
        if (CollUtil.isEmpty(bidVendorMaterials)) {
            log.error("BidVendorMaterialServiceImpl.submitOffer() get saveBidVendorMaterial failed");
            throw new InvException(ResponseEnum.INQUIRY_VENDOR_MATERIAL_SELECT_FAIL);
        }
        //判断所有保存状态的明细的报价是否为空
        Long priceNullCount = bidVendorMaterials.stream().filter(bidVendorMaterial -> bidVendorMaterial.getPrice() == null).count();
        if (priceNullCount != 0) {
            log.error("BidVendorMaterialServiceImpl.submitOffer() price is null");
            throw new InvException(ResponseEnum.INQUIRY_VENDOR_MATERIAL_PRICE_NULL);
        }
        //修改明细状态
        bidVendorMaterials.forEach(bidVendorMaterial -> {
            bidVendorMaterial.setStatus("提交");
            bidVendorMaterialMapper.updateById(bidVendorMaterial);
        });
        //修改供应商状态
        BidVendor bidVendor = new BidVendor();
        bidVendor.setId(bidVendorMaterialDTO.getBidVendorId());
        bidVendor.setVendorStatus("已投标");

        return bidVendorService.updateById(bidVendor);
    }

    @Override
    public List<BidVendorMaterialVO> queryAllocatedRanking(BidVendorMaterialDTO bidVendorMaterialDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("material_id", bidVendorMaterialDTO.getMaterialId());
        queryWrapper.isNotNull("allotted_quantity");
        List<BidVendorMaterial> bidVendorMaterials = bidVendorMaterialMapper.selectList(queryWrapper);
        List<BidVendorMaterialVO> bidVendorMaterialVOS = this.objectConversion(bidVendorMaterials);
        combineBidVendorVO(bidVendorMaterialVOS);

        return bidVendorMaterialVOS;
    }

    private void combineBidVendorVO(List<BidVendorMaterialVO> bidVendorMaterialVOS) {
        bidVendorMaterialVOS.forEach(bidVendorMaterialVO -> {
            if (bidVendorMaterialVO.getBidVendorId() != null) {
                BidVendorVO bidVendorVO = bidVendorService.queryBidVendorById(bidVendorMaterialVO.getBidVendorId());
                bidVendorMaterialVO.setBidVendorVO(bidVendorVO);
            }
        });
    }

}

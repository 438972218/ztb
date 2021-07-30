package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.xdcweb.biz.generator.impl.BidMaterialBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;
import com.xdcplus.xdcweb.biz.service.BidMaterialService;
import com.xdcplus.xdcweb.biz.service.BidVendorMaterialService;
import com.xdcplus.xdcweb.biz.service.BidVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 招标物品(BidMaterial)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:01
 */
@Slf4j
@Service("bidMaterialService")
public class BidMaterialServiceImpl extends BidMaterialBaseServiceImpl<BidMaterial, BidMaterialVO, BidMaterial, BidMaterialMapper> implements BidMaterialService {

    @Autowired
    BidVendorMaterialService bidVendorMaterialService;

    @Autowired
    BidVendorService bidVendorService;

    @Override
    public List<BidMaterialVO> queryBidMaterialsWithPricing(BidMaterialFilterDTO bidMaterialFilterDTO) {
        List<BidMaterialVO> bidMaterialVOS = queryBidMaterialVOList(bidMaterialFilterDTO);
        if (CollUtil.isEmpty(bidMaterialVOS)) {
            return null;
        }
        for (BidMaterialVO bidMaterialVO : bidMaterialVOS) {
            BidVendorMaterialQuery bidVendorMaterialQuery = new BidVendorMaterialQuery();
            bidVendorMaterialQuery.setMaterialId(bidMaterialVO.getId());
            List<BidVendorMaterialVO> bidVendorMaterialVOS = bidVendorMaterialService.queryNewBidVendorMaterialByMaterialId(bidVendorMaterialQuery);
            if (CollUtil.isNotEmpty(bidVendorMaterialVOS)) {
                Double costTotalPrice = 0.0;
                Double totalQuantity = 0.0;
                for (BidVendorMaterialVO bidVendorMaterialVO : bidVendorMaterialVOS) {
                    if (bidVendorMaterialVO.getAllottedQuantity() != null) {
                        costTotalPrice += (bidVendorMaterialVO.getPrice() * bidVendorMaterialVO.getAllottedQuantity());
                        totalQuantity += bidVendorMaterialVO.getAllottedQuantity();
                    }
                }
                if (costTotalPrice != 0.0) {
                    Double costPrice = costTotalPrice / totalQuantity;
                    bidMaterialVO.setCostPrice(costPrice);
                    bidMaterialVO.setCostTotalPrice(costTotalPrice);
                }
            }
        }

        return bidMaterialVOS;
    }

    @Override
    public List<BidMaterialVO> queryBidMaterialsWithVendor(BidMaterialFilterDTO bidMaterialFilterDTO) {
        Long sheetId = bidMaterialFilterDTO.getBidSheetId();
//        Long bidVendorId = bidMaterialFilterDTO.getBidVendorId();
        String vendorCode = bidMaterialFilterDTO.getVendorCode();

        BidVendorFilterDTO bidVendorFilterDTO = new BidVendorFilterDTO();
        bidVendorFilterDTO.setBidSheetId(sheetId);
        bidVendorFilterDTO.setVendorCode(vendorCode);
        List<BidVendorVO> bidVendorVOS = bidVendorService.queryBidVendorVOList(bidVendorFilterDTO);
        if (CollUtil.isEmpty(bidVendorVOS)) {
            log.error("queryBidMaterialsWithVendor() The bidVendor not found");
            throw new InvException(ResponseEnum.INQUIRY_VENDOR_SELECT_FAIL);
        }
        Long bidVendorId = bidVendorVOS.get(0).getBidSheetId();
        List<BidMaterialVO> bidMaterialVOS = queryBidMaterialVOList(bidMaterialFilterDTO);
        if (CollUtil.isEmpty(bidMaterialVOS)) {
            return null;
        }
        for (BidMaterialVO bidMaterialVO : bidMaterialVOS) {
            //查询出最新报价
            BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO = new BidVendorMaterialFilterDTO();
            bidVendorMaterialFilterDTO.setMaterialId(bidMaterialVO.getId());
            bidVendorMaterialFilterDTO.setBidVendorId(bidVendorId);
            bidVendorMaterialFilterDTO.setStatus("提交");
            List<BidVendorMaterial> bidVendorMaterials = bidVendorMaterialService.queryBidVendorMaterialsDesc(bidVendorMaterialFilterDTO);
            if (CollUtil.isNotEmpty(bidVendorMaterials)) {
                bidMaterialVO.setNewPrice(bidVendorMaterials.get(0).getPrice());
            }
            //查询出上一次报价
            BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO1 = new BidVendorMaterialFilterDTO();
            bidVendorMaterialFilterDTO1.setMaterialId(bidMaterialVO.getId());
            bidVendorMaterialFilterDTO1.setBidVendorId(bidVendorId);
            bidVendorMaterialFilterDTO1.setStatus("保存");
            List<BidVendorMaterial> bidVendorMaterials1 = bidVendorMaterialService.queryBidVendorMaterialsDesc(bidVendorMaterialFilterDTO1);
            if (CollUtil.isNotEmpty(bidVendorMaterials)) {
                bidMaterialVO.setLastPrice(bidVendorMaterials1.get(0).getPrice());
            }
        }

        return bidMaterialVOS;
    }


}

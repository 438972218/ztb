package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.generator.impl.BidVendorBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidVendorMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.xdcweb.biz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 招标投标方(BidVendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:09
 */
@Slf4j
@Service("bidVendorService")
public class BidVendorServiceImpl extends BidVendorBaseServiceImpl<BidVendor, BidVendorVO, BidVendor, BidVendorMapper> implements BidVendorService {

    @Autowired
    private BidVendorMaterialService bidVendorMaterialService;

    @Autowired
    private BidMaterialService bidMaterialService;

    @Autowired
    private BidSpecialistScoreService bidSpecialistScoreService;

    @Override
    public List<BidVendorVO> queryBidVendorWithTotalScore(BidVendorFilterDTO bidVendorFilterDTO) {
        BidVendorQuery bidVendorQuery = BeanUtil.copyProperties(bidVendorFilterDTO, BidVendorQuery.class);
        List<BidVendorVO> bidVendorVOS = this.objectConversion(bidVendorMapper.queryBidVendor(bidVendorQuery));
        Long sheetId = bidVendorFilterDTO.getBidSheetId();

        for (BidVendorVO bidVendorVO : bidVendorVOS) {
            BidVendorMaterialQuery bidVendorMaterialQuery = new BidVendorMaterialQuery();
            bidVendorMaterialQuery.setBidVendorId(bidVendorVO.getId());
            bidVendorMaterialQuery.setBidSheetId(sheetId);

            BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO =new BidSpecialistScoreFilterDTO();
            Double totalScore = bidSpecialistScoreService.getTotalScoreByVendorId(bidSpecialistScoreFilterDTO);
            bidVendorVO.setTotalScore(totalScore);
        }

        return bidVendorVOS;
    }

    @Override
    public Boolean updateBidVendorStatus(BidVendorDTO bidVendorDTO) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("bid_sheet_id", bidVendorDTO.getBidSheetId());
        updateWrapper.eq("vendor_code", bidVendorDTO.getVendorCode());
        BidVendor bidVendor = new BidVendor();
        bidVendor.setVendorStatus(bidVendorDTO.getVendorStatus());
        int result = bidVendorMapper.update(bidVendor, updateWrapper);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateBidVendorStatusByPricing(Long sheetId) {
        BidMaterialFilterDTO bidMaterialFilterDTO = new BidMaterialFilterDTO();
        bidMaterialFilterDTO.setBidSheetId(sheetId);
        List<BidMaterialVO> bidMaterialVOS = bidMaterialService.queryBidMaterialVOList(bidMaterialFilterDTO);
        if (CollectionUtil.isEmpty(bidMaterialVOS)) {
            return true;
        }
        BidVendorMaterialQuery bidVendorMaterialQuery = new BidVendorMaterialQuery();
        bidVendorMaterialQuery.setMaterialId(bidMaterialVOS.get(0).getId());
        List<BidVendorMaterialVO> bidVendorMaterialVOS = bidVendorMaterialService.queryNewBidVendorMaterialByMaterialId(bidVendorMaterialQuery);
        if(CollectionUtil.isEmpty(bidVendorMaterialVOS)){
            log.error("updateBidVendorStatusByPricing() queryNewBidVendorMaterialByMaterialId failed");
            throw new InvException(ResponseEnum.BID_REQUEST_SELECT_FAIL);
        }
        for (BidVendorMaterialVO bidVendorMaterialVO : bidVendorMaterialVOS) {
            if (bidVendorMaterialVO.getAllottedQuantity() != null) {
                BidVendor bidVendor = new BidVendor();
                bidVendor.setId(bidVendorMaterialVO.getBidVendorId());
                bidVendor.setVendorStatus("已中签");
                updateById(bidVendor);
            }
        }

        return true;
    }

    @Override
    public Boolean updateBidVendorTotalPrice(BidVendorDTO bidVendorDTO) {
        BidVendorMaterialQuery bidVendorMaterialQuery =new BidVendorMaterialQuery();
        bidVendorMaterialQuery.setBidVendorId(bidVendorDTO.getId());
        Double totalPrice = bidVendorMaterialService.getTotalPriceByBidVendorId(bidVendorMaterialQuery);
        bidVendorDTO.setTotalPrice(totalPrice);

        return updateBidVendor(bidVendorDTO);
    }

}

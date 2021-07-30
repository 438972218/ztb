package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorPreTrialItemVO;
import com.xdcplus.xdcweb.biz.generator.impl.BidPreTrialItemBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidPreTrialItemMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidPreTrialItemVO;
import com.xdcplus.xdcweb.biz.service.BidPreTrialItemService;
import com.xdcplus.xdcweb.biz.service.BidVendorPreTrialItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 预审项(BidPreTrialItem)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:03
 */
@Slf4j
@Service("bidPreTrialItemService")
public class BidPreTrialItemServiceImpl extends BidPreTrialItemBaseServiceImpl<BidPreTrialItem, BidPreTrialItemVO, BidPreTrialItem, BidPreTrialItemMapper> implements BidPreTrialItemService {

    @Autowired
    private BidVendorPreTrialItemService bidVendorPreTrialItemService;

    @Override
    public List<BidPreTrialItemVO> queryBidPreTrialItemsByVendor(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO) {
        List<BidPreTrialItemVO> bidPreTrialItemVOS = queryBidPreTrialItemVOList(bidPreTrialItemFilterDTO);
        if (CollectionUtil.isEmpty(bidPreTrialItemVOS)) {
            return null;
        }
        for (BidPreTrialItemVO bidPreTrialItemVO : bidPreTrialItemVOS) {
            BidVendorPreTrialItemFilterDTO bidVendorPreTrialItemFilterDTO = new BidVendorPreTrialItemFilterDTO();
            bidVendorPreTrialItemFilterDTO.setBidVendorId(bidPreTrialItemFilterDTO.getBidVendorId());
            bidVendorPreTrialItemFilterDTO.setTrialItemId(bidPreTrialItemVO.getId());
            List<BidVendorPreTrialItemVO> bidVendorPreTrialItemVOS = bidVendorPreTrialItemService.queryBidVendorPreTrialItemVOList(bidVendorPreTrialItemFilterDTO);
            if (CollectionUtil.isNotEmpty(bidVendorPreTrialItemVOS)) {
                bidPreTrialItemVO.setBidVendorPreTrialItemVO(bidVendorPreTrialItemVOS.get(0));
            }
        }
        return bidPreTrialItemVOS;
    }

}

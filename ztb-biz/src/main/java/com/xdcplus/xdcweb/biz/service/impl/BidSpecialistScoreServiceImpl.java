package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.xdcweb.biz.generator.impl.BidSpecialistScoreBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidSpecialistScoreMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistScoreVO;
import com.xdcplus.xdcweb.biz.service.BidSpecialistScoreService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 招标专家评分(BidSpecialistScore)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:07
 */
@Slf4j
@Service("bidSpecialistScoreService")
public class BidSpecialistScoreServiceImpl extends BidSpecialistScoreBaseServiceImpl<BidSpecialistScore, BidSpecialistScoreVO, BidSpecialistScore, BidSpecialistScoreMapper> implements BidSpecialistScoreService {


    @Override
    public Double getTotalScoreByVendorId(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {
        List<BidSpecialistScoreVO> bidSpecialistScoreVOS = queryBidSpecialistScoreVOList(bidSpecialistScoreFilterDTO);
        final Double[] totalScore = {0.0};
        bidSpecialistScoreVOS.forEach(bidSpecialistScoreVO -> {
            totalScore[0] +=Double.valueOf(bidSpecialistScoreVO.getScore());
        });
        return totalScore[0];
    }

    @Override
    public PageVO<BidSpecialistScoreVO> queryBidSpecialistScoreWithSpecialist(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {
        if (ObjectUtil.isNull(bidSpecialistScoreFilterDTO)) {
            bidSpecialistScoreFilterDTO = new BidSpecialistScoreFilterDTO();
        }
        bidSpecialistScoreFilterDTO.setDeleted(0);
        PageVO<BidSpecialistScoreVO> pageVO = new PageVO<>();

        if (bidSpecialistScoreFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSpecialistScoreFilterDTO.getCurrentPage(), bidSpecialistScoreFilterDTO.getPageSize(),
                    bidSpecialistScoreFilterDTO.getOrderType(), bidSpecialistScoreFilterDTO.getOrderField());
        }

        List<BidSpecialistScore> bidSpecialistScoreList =  bidSpecialistScoreMapper.queryBidSpecialistScoreBySheetId(bidSpecialistScoreFilterDTO);
        if(CollectionUtil.isEmpty(bidSpecialistScoreList)){
            log.error("queryBidSpecialistScoreWithSpecialist() select SpecialistScore failed");
            throw new InvException(ResponseEnum.BID_SPECIALIST_SELECT_FAIL);
        }
        List<BidSpecialistScoreVO> bidSpecialistScoreVOS = this.objectConversion(bidSpecialistScoreList);
        for (BidSpecialistScoreVO bidSpecialistScoreVO : bidSpecialistScoreVOS) {
            BidVendorVO bidVendorVO = bidVendorService.queryBidVendorById(bidSpecialistScoreVO.getBidVendorId());
            bidSpecialistScoreVO.setBidVendorVO(bidVendorVO);
        }

        PageInfo<BidSpecialistScoreVO> pageInfo = new PageInfo<>(bidSpecialistScoreVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, bidSpecialistScoreVOS);

        return pageVO;
    }
}

package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.BidScoringElementsBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidScoringElementsMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidScoringElements;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidScoringElementsVO;
import com.xdcplus.xdcweb.biz.service.BidScoringElementsService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 评分要素(BidScoringElements)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:03
 */
@Slf4j
@Service("bidScoringElementsService")
public class BidScoringElementsServiceImpl extends BidScoringElementsBaseServiceImpl<BidScoringElements, BidScoringElementsVO, BidScoringElements, BidScoringElementsMapper> implements BidScoringElementsService {


}

package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidScoringElements;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidScoringElementsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评分要素(BidScoringElements)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:04
 */
public interface BidScoringElementsMapper extends IBaseMapper<BidScoringElements> {

    /**
     * 查询评分要素(BidScoringElements)
     *
     * @param bidScoringElementsQuery 评分要素(BidScoringElements)查询
     * @return {@link List<BidScoringElements>}
     */
    List<BidScoringElements> queryBidScoringElements(BidScoringElementsQuery bidScoringElementsQuery);

}

package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidSpecialistScoreQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标专家评分(BidSpecialistScore)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:15
 */
public interface BidSpecialistScoreMapper extends IBaseMapper<BidSpecialistScore> {

    /**
     * 查询招标专家评分(BidSpecialistScore)
     *
     * @param bidSpecialistScoreQuery 招标专家评分(BidSpecialistScore)查询
     * @return {@link List<BidSpecialistScore>}
     */
    List<BidSpecialistScore> queryBidSpecialistScore(BidSpecialistScoreQuery bidSpecialistScoreQuery);

    List<BidSpecialistScore> queryBidSpecialistScoreBySheetId(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO);

}

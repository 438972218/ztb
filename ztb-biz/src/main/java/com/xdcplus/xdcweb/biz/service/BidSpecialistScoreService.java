package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.xdcweb.biz.generator.BidSpecialistScoreBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistScoreVO;


/**
 * 招标专家评分(BidSpecialistScore)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:07
 */
public interface BidSpecialistScoreService extends BidSpecialistScoreBaseService<BidSpecialistScore, BidSpecialistScoreVO, BidSpecialistScore> {

    Double getTotalScoreByVendorId(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO);

    /**
     * 查询招标专家评分(BidSpecialistScore)
     *
     * @param bidSpecialistScoreFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <BidSpecialistScoreVO>} 状态信息
     */
    PageVO<BidSpecialistScoreVO> queryBidSpecialistScoreWithSpecialist(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO);
}

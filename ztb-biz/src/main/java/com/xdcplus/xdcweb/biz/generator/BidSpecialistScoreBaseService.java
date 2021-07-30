package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistScoreVO;

import java.util.List;

/**
 * 招标专家评分(BidSpecialistScore)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:05
 */
public interface BidSpecialistScoreBaseService<S, T, E> extends BaseService<BidSpecialistScore, BidSpecialistScoreVO, BidSpecialistScore> {

    /**
     * 添加招标专家评分(BidSpecialistScore)
     *
     * @param bidSpecialistScoreDTO 招标专家评分(BidSpecialistScoreDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidSpecialistScore(BidSpecialistScoreDTO bidSpecialistScoreDTO);

    /**
     * 修改招标专家评分(BidSpecialistScore)
     *
     * @param bidSpecialistScoreDTO 招标专家评分(BidSpecialistScoreDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidSpecialistScore(BidSpecialistScoreDTO bidSpecialistScoreDTO);

    /**
     * 批量保存或更新招标专家评分(BidSpecialistScore)
     *
     * @param bidSpecialistScoreList 招标专家评分(BidSpecialistScoreList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidSpecialistScore> bidSpecialistScoreList);

    /**
     * 批量保存或更新招标专家评分(BidSpecialistScoreDTOList)
     *
     * @param bidSpecialistScoreDTOList 招标专家评分(BidSpecialistScoreDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidSpecialistScoreDTO> bidSpecialistScoreDTOList);

    /**
     * 删除招标专家评分(BidSpecialistScore)
     *
     * @param id 招标专家评分(BidSpecialistScore)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidSpecialistScoreLogical(Long id);

    /**
     * 查询招标专家评分(BidSpecialistScore)
     *
     * @param bidSpecialistScoreFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidSpecialistScoreVO>} 状态信息
     */
    List<BidSpecialistScoreVO> queryBidSpecialistScoreVOList(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO);

    /**
     * 查询招标专家评分(BidSpecialistScore)
     *
     * @param bidSpecialistScoreFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidSpecialistScoreVO>} 状态信息
     */
    PageVO<BidSpecialistScoreVO> queryBidSpecialistScore(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO);

    /**
     * 查询一个
     *
     * @param id 招标专家评分(BidSpecialistScore)主键
     * @return {@link BidSpecialistScoreVO} 招标专家评分(BidSpecialistScore)信息
     */
    BidSpecialistScoreVO queryBidSpecialistScoreById(Long id);

}

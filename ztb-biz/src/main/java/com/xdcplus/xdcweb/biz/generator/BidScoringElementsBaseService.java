package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidScoringElements;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidScoringElementsDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidScoringElementsFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidScoringElementsVO;

import java.util.List;

/**
 * 评分要素(BidScoringElements)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:03
 */
public interface BidScoringElementsBaseService<S, T, E> extends BaseService<BidScoringElements, BidScoringElementsVO, BidScoringElements> {

    /**
     * 添加评分要素(BidScoringElements)
     *
     * @param bidScoringElementsDTO 评分要素(BidScoringElementsDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidScoringElements(BidScoringElementsDTO bidScoringElementsDTO);

    /**
     * 修改评分要素(BidScoringElements)
     *
     * @param bidScoringElementsDTO 评分要素(BidScoringElementsDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidScoringElements(BidScoringElementsDTO bidScoringElementsDTO);

    /**
     * 批量保存或更新评分要素(BidScoringElements)
     *
     * @param bidScoringElementsList 评分要素(BidScoringElementsList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidScoringElements> bidScoringElementsList);

    /**
     * 批量保存或更新评分要素(BidScoringElementsDTOList)
     *
     * @param bidScoringElementsDTOList 评分要素(BidScoringElementsDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidScoringElementsDTO> bidScoringElementsDTOList);

    /**
     * 删除评分要素(BidScoringElements)
     *
     * @param id 评分要素(BidScoringElements)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidScoringElementsLogical(Long id);

    /**
     * 查询评分要素(BidScoringElements)
     *
     * @param bidScoringElementsFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidScoringElementsVO>} 状态信息
     */
    List<BidScoringElementsVO> queryBidScoringElementsVOList(BidScoringElementsFilterDTO bidScoringElementsFilterDTO);

    /**
     * 查询评分要素(BidScoringElements)
     *
     * @param bidScoringElementsFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidScoringElementsVO>} 状态信息
     */
    PageVO<BidScoringElementsVO> queryBidScoringElements(BidScoringElementsFilterDTO bidScoringElementsFilterDTO);

    /**
     * 查询一个
     *
     * @param id 评分要素(BidScoringElements)主键
     * @return {@link BidScoringElementsVO} 评分要素(BidScoringElements)信息
     */
    BidScoringElementsVO queryBidScoringElementsById(Long id);


}

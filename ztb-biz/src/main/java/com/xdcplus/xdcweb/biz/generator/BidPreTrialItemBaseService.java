package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidPreTrialItemVO;

import java.util.List;

/**
 * 预审项(BidPreTrialItem)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:01
 */
public interface BidPreTrialItemBaseService<S, T, E> extends BaseService<BidPreTrialItem, BidPreTrialItemVO, BidPreTrialItem> {

    /**
     * 添加预审项(BidPreTrialItem)
     *
     * @param bidPreTrialItemDTO 预审项(BidPreTrialItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidPreTrialItem(BidPreTrialItemDTO bidPreTrialItemDTO);

    /**
     * 修改预审项(BidPreTrialItem)
     *
     * @param bidPreTrialItemDTO 预审项(BidPreTrialItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidPreTrialItem(BidPreTrialItemDTO bidPreTrialItemDTO);

    /**
     * 批量保存或更新预审项(BidPreTrialItem)
     *
     * @param bidPreTrialItemList 预审项(BidPreTrialItemList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidPreTrialItem> bidPreTrialItemList);

    /**
     * 批量保存或更新预审项(BidPreTrialItemDTOList)
     *
     * @param bidPreTrialItemDTOList 预审项(BidPreTrialItemDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidPreTrialItemDTO> bidPreTrialItemDTOList);

    /**
     * 删除预审项(BidPreTrialItem)
     *
     * @param id 预审项(BidPreTrialItem)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidPreTrialItemLogical(Long id);

    /**
     * 查询预审项(BidPreTrialItem)
     *
     * @param bidPreTrialItemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidPreTrialItemVO>} 状态信息
     */
    List<BidPreTrialItemVO> queryBidPreTrialItemVOList(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO);

    /**
     * 查询预审项(BidPreTrialItem)
     *
     * @param bidPreTrialItemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidPreTrialItemVO>} 状态信息
     */
    PageVO<BidPreTrialItemVO> queryBidPreTrialItem(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO);

    /**
     * 查询一个
     *
     * @param id 预审项(BidPreTrialItem)主键
     * @return {@link BidPreTrialItemVO} 预审项(BidPreTrialItem)信息
     */
    BidPreTrialItemVO queryBidPreTrialItemById(Long id);


}

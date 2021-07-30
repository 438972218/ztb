package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorPreTrialItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorPreTrialItemVO;

import java.util.List;

/**
 * 招标投标方预审项(BidVendorPreTrialItem)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:12
 */
public interface BidVendorPreTrialItemBaseService<S, T, E> extends BaseService<BidVendorPreTrialItem, BidVendorPreTrialItemVO, BidVendorPreTrialItem> {

    /**
     * 添加招标投标方预审项(BidVendorPreTrialItem)
     *
     * @param bidVendorPreTrialItemDTO 招标投标方预审项(BidVendorPreTrialItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidVendorPreTrialItem(BidVendorPreTrialItemDTO bidVendorPreTrialItemDTO);

    /**
     * 修改招标投标方预审项(BidVendorPreTrialItem)
     *
     * @param bidVendorPreTrialItemDTO 招标投标方预审项(BidVendorPreTrialItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidVendorPreTrialItem(BidVendorPreTrialItemDTO bidVendorPreTrialItemDTO);

    /**
     * 批量保存或更新招标投标方预审项(BidVendorPreTrialItem)
     *
     * @param bidVendorPreTrialItemList 招标投标方预审项(BidVendorPreTrialItemList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidVendorPreTrialItem> bidVendorPreTrialItemList);

    /**
     * 批量保存或更新招标投标方预审项(BidVendorPreTrialItemDTOList)
     *
     * @param bidVendorPreTrialItemDTOList 招标投标方预审项(BidVendorPreTrialItemDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidVendorPreTrialItemDTO> bidVendorPreTrialItemDTOList);

    /**
     * 删除招标投标方预审项(BidVendorPreTrialItem)
     *
     * @param id 招标投标方预审项(BidVendorPreTrialItem)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidVendorPreTrialItemLogical(Long id);

    /**
     * 查询招标投标方预审项(BidVendorPreTrialItem)
     *
     * @param bidVendorPreTrialItemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorPreTrialItemVO>} 状态信息
     */
    List<BidVendorPreTrialItemVO> queryBidVendorPreTrialItemVOList(BidVendorPreTrialItemFilterDTO bidVendorPreTrialItemFilterDTO);

    /**
     * 查询招标投标方预审项(BidVendorPreTrialItem)
     *
     * @param bidVendorPreTrialItemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorPreTrialItemVO>} 状态信息
     */
    PageVO<BidVendorPreTrialItemVO> queryBidVendorPreTrialItem(BidVendorPreTrialItemFilterDTO bidVendorPreTrialItemFilterDTO);

    /**
     * 查询一个
     *
     * @param id 招标投标方预审项(BidVendorPreTrialItem)主键
     * @return {@link BidVendorPreTrialItemVO} 招标投标方预审项(BidVendorPreTrialItem)信息
     */
    BidVendorPreTrialItemVO queryBidVendorPreTrialItemById(Long id);


}

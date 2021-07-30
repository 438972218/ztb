package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.generator.BidPreTrialItemBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidPreTrialItemVO;

import java.util.List;


/**
 * 预审项(BidPreTrialItem)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:03
 */
public interface BidPreTrialItemService extends BidPreTrialItemBaseService<BidPreTrialItem, BidPreTrialItemVO, BidPreTrialItem> {

    /**
     * 查询预审项(供应商)(BidPreTrialItem)
     *
     * @param bidPreTrialItemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <BidPreTrialItemVO>} 状态信息
     */
    List<BidPreTrialItemVO> queryBidPreTrialItemsByVendor(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO);



}

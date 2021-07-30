package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSheetFilterDTO;
import com.xdcplus.xdcweb.biz.generator.BidSheetBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSheet;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSheetVO;


/**
 * bid招标单(BidSheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:03
 */
public interface BidSheetService extends BidSheetBaseService<BidSheet, BidSheetVO, BidSheet> {

    /**
     * 添加bid招标单(BidSheet)返回VO
     *
     * @param bidSheetDTO bid招标单(BidSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    BidSheetVO saveBidSheetReturnVO(BidSheetDTO bidSheetDTO);

    /**
     * 查询一个并带出附表中数据
     *
     * @param id bid招标单(BidSheet)主键
     * @return {@link BidSheetVO} bid招标单(BidSheet)信息
     */
    BidSheetVO showBidSheetById(Long id);

    /**
     * 查询招标单(供应商)(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <  BidSheetVO  >} 状态信息
     */
    PageVO<BidSheetVO> queryBidSheetByVendor(BidSheetFilterDTO bidSheetFilterDTO);

    /**
     * 查询招标单(request)(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<  BidSheetVO  >} 状态信息
     */
    PageVO<BidSheetVO> queryBidSheetWithRequest(BidSheetFilterDTO bidSheetFilterDTO);


}

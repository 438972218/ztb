package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquirySheetDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquirySheetFilterDTO;
import com.xdcplus.xdcweb.biz.generator.InquirySheetBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquirySheet;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquirySheetVO;


/**
 * 询价单(InquirySheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:10
 */
public interface InquirySheetService extends InquirySheetBaseService<InquirySheet, InquirySheetVO, InquirySheet> {

    /**
     * 添加询价单(InquirySheet)返回VO
     *
     * @param inquirySheetDTO 询价单(InquirySheetDTO)
     * @return {@link Boolean} 是否成功
     */
    InquirySheetVO saveInquirySheetReturnVO(InquirySheetDTO inquirySheetDTO);

    /**
     * 查询一个并带出附表中数据
     *
     * @param id 询价单(InquirySheet)主键
     * @return {@link InquirySheetVO} 询价单(InquirySheet)信息
     */
    InquirySheetVO showInquirySheetById(Long id);

    /**
     * 查询询价单(供应商)(InquirySheet)
     *
     * @param inquirySheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <InquirySheetVO>} 状态信息
     */
    PageVO<InquirySheetVO> queryInquirySheetByVendor(InquirySheetFilterDTO inquirySheetFilterDTO);

    /**
     * 查询询价单(request)(InquirySheet)
     *
     * @param inquirySheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquirySheetVO>} 状态信息
     */
    PageVO<InquirySheetVO> queryInquirySheetWithRequest(InquirySheetFilterDTO inquirySheetFilterDTO);

}

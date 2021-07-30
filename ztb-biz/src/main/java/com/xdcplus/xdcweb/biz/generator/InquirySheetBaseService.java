package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquirySheet;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquirySheetDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquirySheetFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquirySheetVO;

import java.util.List;

/**
 * 询价单(InquirySheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:06
 */
public interface InquirySheetBaseService<S, T, E> extends BaseService<InquirySheet, InquirySheetVO, InquirySheet> {

    /**
     * 添加询价单(InquirySheet)
     *
     * @param inquirySheetDTO 询价单(InquirySheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveInquirySheet(InquirySheetDTO inquirySheetDTO);

    /**
     * 修改询价单(InquirySheet)
     *
     * @param inquirySheetDTO 询价单(InquirySheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInquirySheet(InquirySheetDTO inquirySheetDTO);

    /**
     * 批量保存或更新询价单(InquirySheet)
     *
     * @param inquirySheetList 询价单(InquirySheetList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<InquirySheet> inquirySheetList);

    /**
     * 批量保存或更新询价单(InquirySheetDTOList)
     *
     * @param inquirySheetDTOList 询价单(InquirySheetDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<InquirySheetDTO> inquirySheetDTOList);

    /**
     * 删除询价单(InquirySheet)
     *
     * @param id 询价单(InquirySheet)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteInquirySheetLogical(Long id);

    /**
     * 查询询价单(InquirySheet)
     *
     * @param inquirySheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquirySheetVO>} 状态信息
     */
    List<InquirySheetVO> queryInquirySheetVOList(InquirySheetFilterDTO inquirySheetFilterDTO);

    /**
     * 查询询价单(InquirySheet)
     *
     * @param inquirySheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquirySheetVO>} 状态信息
     */
    PageVO<InquirySheetVO> queryInquirySheet(InquirySheetFilterDTO inquirySheetFilterDTO);

    /**
     * 查询一个
     *
     * @param id 询价单(InquirySheet)主键
     * @return {@link InquirySheetVO} 询价单(InquirySheet)信息
     */
    InquirySheetVO queryInquirySheetById(Long id);


}

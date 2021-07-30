package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorAttachmentVO;

import java.util.List;

/**
 * 询价供应商附件(InquiryVendorAttachment)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:14
 */
public interface InquiryVendorAttachmentBaseService<S, T, E> extends BaseService<InquiryVendorAttachment, InquiryVendorAttachmentVO, InquiryVendorAttachment> {

    /**
     * 添加询价供应商附件(InquiryVendorAttachment)
     *
     * @param inquiryVendorAttachmentDTO 询价供应商附件(InquiryVendorAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveInquiryVendorAttachment(InquiryVendorAttachmentDTO inquiryVendorAttachmentDTO);

    /**
     * 修改询价供应商附件(InquiryVendorAttachment)
     *
     * @param inquiryVendorAttachmentDTO 询价供应商附件(InquiryVendorAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInquiryVendorAttachment(InquiryVendorAttachmentDTO inquiryVendorAttachmentDTO);

    /**
     * 批量保存或更新询价供应商附件(InquiryVendorAttachment)
     *
     * @param inquiryVendorAttachmentList 询价供应商附件(InquiryVendorAttachmentList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<InquiryVendorAttachment> inquiryVendorAttachmentList);

    /**
     * 批量保存或更新询价供应商附件(InquiryVendorAttachmentDTOList)
     *
     * @param inquiryVendorAttachmentDTOList 询价供应商附件(InquiryVendorAttachmentDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<InquiryVendorAttachmentDTO> inquiryVendorAttachmentDTOList);

    /**
     * 删除询价供应商附件(InquiryVendorAttachment)
     *
     * @param id 询价供应商附件(InquiryVendorAttachment)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteInquiryVendorAttachmentLogical(Long id);

    /**
     * 查询询价供应商附件(InquiryVendorAttachment)
     *
     * @param inquiryVendorAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryVendorAttachmentVO>} 状态信息
     */
    List<InquiryVendorAttachmentVO> queryInquiryVendorAttachmentVOList(InquiryVendorAttachmentFilterDTO inquiryVendorAttachmentFilterDTO);

    /**
     * 查询询价供应商附件(InquiryVendorAttachment)
     *
     * @param inquiryVendorAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryVendorAttachmentVO>} 状态信息
     */
    PageVO<InquiryVendorAttachmentVO> queryInquiryVendorAttachment(InquiryVendorAttachmentFilterDTO inquiryVendorAttachmentFilterDTO);

    /**
     * 查询一个
     *
     * @param id 询价供应商附件(InquiryVendorAttachment)主键
     * @return {@link InquiryVendorAttachmentVO} 询价供应商附件(InquiryVendorAttachment)信息
     */
    InquiryVendorAttachmentVO queryInquiryVendorAttachmentById(Long id);


}

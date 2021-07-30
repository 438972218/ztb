package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryAttachmentVO;

import java.util.List;

/**
 * 询价单附件(InquiryAttachment)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:39:58
 */
public interface InquiryAttachmentBaseService<S, T, E> extends BaseService<InquiryAttachment, InquiryAttachmentVO, InquiryAttachment> {

    /**
     * 添加询价单附件(InquiryAttachment)
     *
     * @param inquiryAttachmentDTO 询价单附件(InquiryAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveInquiryAttachment(InquiryAttachmentDTO inquiryAttachmentDTO);

    /**
     * 修改询价单附件(InquiryAttachment)
     *
     * @param inquiryAttachmentDTO 询价单附件(InquiryAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInquiryAttachment(InquiryAttachmentDTO inquiryAttachmentDTO);

    /**
     * 批量保存或更新询价单附件(InquiryAttachment)
     *
     * @param inquiryAttachmentList 询价单附件(InquiryAttachmentList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<InquiryAttachment> inquiryAttachmentList);

    /**
     * 批量保存或更新询价单附件(InquiryAttachmentDTOList)
     *
     * @param inquiryAttachmentDTOList 询价单附件(InquiryAttachmentDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<InquiryAttachmentDTO> inquiryAttachmentDTOList);

    /**
     * 删除询价单附件(InquiryAttachment)
     *
     * @param id 询价单附件(InquiryAttachment)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteInquiryAttachmentLogical(Long id);

    /**
     * 查询询价单附件(InquiryAttachment)
     *
     * @param inquiryAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryAttachmentVO>} 状态信息
     */
    List<InquiryAttachmentVO> queryInquiryAttachmentVOList(InquiryAttachmentFilterDTO inquiryAttachmentFilterDTO);

    /**
     * 查询询价单附件(InquiryAttachment)
     *
     * @param inquiryAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryAttachmentVO>} 状态信息
     */
    PageVO<InquiryAttachmentVO> queryInquiryAttachment(InquiryAttachmentFilterDTO inquiryAttachmentFilterDTO);

    /**
     * 查询一个
     *
     * @param id 询价单附件(InquiryAttachment)主键
     * @return {@link InquiryAttachmentVO} 询价单附件(InquiryAttachment)信息
     */
    InquiryAttachmentVO queryInquiryAttachmentById(Long id);


}

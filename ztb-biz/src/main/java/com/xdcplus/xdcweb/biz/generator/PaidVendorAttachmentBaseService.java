package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorAttachmentVO;

import java.util.List;

/**
 * 竞价供应商附件(PaidVendorAttachment)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:45
 */
public interface PaidVendorAttachmentBaseService<S, T, E> extends BaseService<PaidVendorAttachment, PaidVendorAttachmentVO, PaidVendorAttachment> {

    /**
     * 添加竞价供应商附件(PaidVendorAttachment)
     *
     * @param paidVendorAttachmentDTO 竞价供应商附件(PaidVendorAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePaidVendorAttachment(PaidVendorAttachmentDTO paidVendorAttachmentDTO);

    /**
     * 修改竞价供应商附件(PaidVendorAttachment)
     *
     * @param paidVendorAttachmentDTO 竞价供应商附件(PaidVendorAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidVendorAttachment(PaidVendorAttachmentDTO paidVendorAttachmentDTO);

    /**
     * 批量保存或更新竞价供应商附件(PaidVendorAttachment)
     *
     * @param paidVendorAttachmentList 竞价供应商附件(PaidVendorAttachmentList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PaidVendorAttachment> paidVendorAttachmentList);

    /**
     * 批量保存或更新竞价供应商附件(PaidVendorAttachmentDTOList)
     *
     * @param paidVendorAttachmentDTOList 竞价供应商附件(PaidVendorAttachmentDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PaidVendorAttachmentDTO> paidVendorAttachmentDTOList);

    /**
     * 删除竞价供应商附件(PaidVendorAttachment)
     *
     * @param id 竞价供应商附件(PaidVendorAttachment)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePaidVendorAttachmentLogical(Long id);

    /**
     * 查询竞价供应商附件(PaidVendorAttachment)
     *
     * @param paidVendorAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidVendorAttachmentVO>} 状态信息
     */
    List<PaidVendorAttachmentVO> queryPaidVendorAttachmentVOList(PaidVendorAttachmentFilterDTO paidVendorAttachmentFilterDTO);

    /**
     * 查询竞价供应商附件(PaidVendorAttachment)
     *
     * @param paidVendorAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidVendorAttachmentVO>} 状态信息
     */
    PageVO<PaidVendorAttachmentVO> queryPaidVendorAttachment(PaidVendorAttachmentFilterDTO paidVendorAttachmentFilterDTO);

    /**
     * 查询一个
     *
     * @param id 竞价供应商附件(PaidVendorAttachment)主键
     * @return {@link PaidVendorAttachmentVO} 竞价供应商附件(PaidVendorAttachment)信息
     */
    PaidVendorAttachmentVO queryPaidVendorAttachmentById(Long id);


}

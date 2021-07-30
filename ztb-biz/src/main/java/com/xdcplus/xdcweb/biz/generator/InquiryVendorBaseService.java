package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendor;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorVO;

import java.util.List;

/**
 * 询价供应商(InquiryVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:10
 */
public interface InquiryVendorBaseService<S, T, E> extends BaseService<InquiryVendor, InquiryVendorVO, InquiryVendor> {

    /**
     * 添加询价供应商(InquiryVendor)
     *
     * @param inquiryVendorDTO 询价供应商(InquiryVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveInquiryVendor(InquiryVendorDTO inquiryVendorDTO);

    /**
     * 修改询价供应商(InquiryVendor)
     *
     * @param inquiryVendorDTO 询价供应商(InquiryVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInquiryVendor(InquiryVendorDTO inquiryVendorDTO);

    /**
     * 批量保存或更新询价供应商(InquiryVendor)
     *
     * @param inquiryVendorList 询价供应商(InquiryVendorList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<InquiryVendor> inquiryVendorList);

    /**
     * 批量保存或更新询价供应商(InquiryVendorDTOList)
     *
     * @param inquiryVendorDTOList 询价供应商(InquiryVendorDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<InquiryVendorDTO> inquiryVendorDTOList);

    /**
     * 删除询价供应商(InquiryVendor)
     *
     * @param id 询价供应商(InquiryVendor)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteInquiryVendorLogical(Long id);

    /**
     * 查询询价供应商(InquiryVendor)
     *
     * @param inquiryVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryVendorVO>} 状态信息
     */
    List<InquiryVendorVO> queryInquiryVendorVOList(InquiryVendorFilterDTO inquiryVendorFilterDTO);

    /**
     * 查询询价供应商(InquiryVendor)
     *
     * @param inquiryVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<InquiryVendorVO>} 状态信息
     */
    PageVO<InquiryVendorVO> queryInquiryVendor(InquiryVendorFilterDTO inquiryVendorFilterDTO);

    /**
     * 查询一个
     *
     * @param id 询价供应商(InquiryVendor)主键
     * @return {@link InquiryVendorVO} 询价供应商(InquiryVendor)信息
     */
    InquiryVendorVO queryInquiryVendorById(Long id);


}

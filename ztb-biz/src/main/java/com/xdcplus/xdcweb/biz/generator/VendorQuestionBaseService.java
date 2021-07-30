package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestion;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionVO;

import java.util.List;

/**
 * 供应商电子调查表(VendorQuestion)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:55
 */
public interface VendorQuestionBaseService<S, T, E> extends BaseService<VendorQuestion, VendorQuestionVO, VendorQuestion> {

    /**
     * 添加供应商电子调查表(VendorQuestion)
     *
     * @param vendorQuestionDTO 供应商电子调查表(VendorQuestionDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorQuestion(VendorQuestionDTO vendorQuestionDTO);

    /**
     * 修改供应商电子调查表(VendorQuestion)
     *
     * @param vendorQuestionDTO 供应商电子调查表(VendorQuestionDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorQuestion(VendorQuestionDTO vendorQuestionDTO);

    /**
     * 批量保存或更新供应商电子调查表(VendorQuestion)
     *
     * @param vendorQuestionList 供应商电子调查表(VendorQuestionList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorQuestion> vendorQuestionList);

    /**
     * 批量保存或更新供应商电子调查表(VendorQuestionDTOList)
     *
     * @param vendorQuestionDTOList 供应商电子调查表(VendorQuestionDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorQuestionDTO> vendorQuestionDTOList);

    /**
     * 删除供应商电子调查表(VendorQuestion)
     *
     * @param id 供应商电子调查表(VendorQuestion)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorQuestionLogical(Long id);

    /**
     * 查询供应商电子调查表(VendorQuestion)
     *
     * @param vendorQuestionFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQuestionVO>} 状态信息
     */
    List<VendorQuestionVO> queryVendorQuestionVOList(VendorQuestionFilterDTO vendorQuestionFilterDTO);

    /**
     * 查询供应商电子调查表(VendorQuestion)
     *
     * @param vendorQuestionFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQuestionVO>} 状态信息
     */
    PageVO<VendorQuestionVO> queryVendorQuestion(VendorQuestionFilterDTO vendorQuestionFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商电子调查表(VendorQuestion)主键
     * @return {@link VendorQuestionVO} 供应商电子调查表(VendorQuestion)信息
     */
    VendorQuestionVO queryVendorQuestionById(Long id);


}

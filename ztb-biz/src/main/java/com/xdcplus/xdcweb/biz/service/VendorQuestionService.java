package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionFilterDTO;
import com.xdcplus.xdcweb.biz.generator.VendorQuestionBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestion;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionVO;


/**
 * 供应商电子调查表(VendorQuestion)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:58
 */
public interface VendorQuestionService extends VendorQuestionBaseService<VendorQuestion, VendorQuestionVO, VendorQuestion> {

    /**
     * 查询一个并带出附表中数据
     *
     * @param id 电子调查单(VendorQuestion)主键
     * @return {@link VendorQuestionVO} 电子调查单(VendorQuestion)信息
     */
    VendorQuestionVO showVendorQuestionById(Long id);

    /**
     * 修改供应商电子调查表以及detail(VendorQualifyReview)
     *
     * @param vendorQuestionDTO 供应商电子调查表(VendorQualifyReviewDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorQuestionWithDetail(VendorQuestionDTO vendorQuestionDTO);

    /**
     * 添加电子调查单(VendorQuestion)返回VO
     *
     * @param vendorQuestionDTO 询价单(VendorQuestionDTO)
     * @return {@link VendorQuestionVO}
     */
    VendorQuestionVO saveVendorQuestionReturnVO(VendorQuestionDTO vendorQuestionDTO);

    /**
     * 查询供应商电子调查表(VendorQuestion)
     *
     * @param vendorQuestionFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <VendorQuestionVO>} 状态信息
     */
    PageVO<VendorQuestionVO> queryVendorQuestionWithRequest(VendorQuestionFilterDTO vendorQuestionFilterDTO);

}

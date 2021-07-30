package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionDetailVO;

import java.util.List;

/**
 * 供应商电子调查表明细(VendorQuestionDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:32:02
 */
public interface VendorQuestionDetailBaseService<S, T, E> extends BaseService<VendorQuestionDetail, VendorQuestionDetailVO, VendorQuestionDetail> {

    /**
     * 添加供应商电子调查表明细(VendorQuestionDetail)
     *
     * @param vendorQuestionDetailDTO 供应商电子调查表明细(VendorQuestionDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorQuestionDetail(VendorQuestionDetailDTO vendorQuestionDetailDTO);

    /**
     * 修改供应商电子调查表明细(VendorQuestionDetail)
     *
     * @param vendorQuestionDetailDTO 供应商电子调查表明细(VendorQuestionDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorQuestionDetail(VendorQuestionDetailDTO vendorQuestionDetailDTO);

    /**
     * 批量保存或更新供应商电子调查表明细(VendorQuestionDetail)
     *
     * @param vendorQuestionDetailList 供应商电子调查表明细(VendorQuestionDetailList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorQuestionDetail> vendorQuestionDetailList);

    /**
     * 批量保存或更新供应商电子调查表明细(VendorQuestionDetailDTOList)
     *
     * @param vendorQuestionDetailDTOList 供应商电子调查表明细(VendorQuestionDetailDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorQuestionDetailDTO> vendorQuestionDetailDTOList);

    /**
     * 删除供应商电子调查表明细(VendorQuestionDetail)
     *
     * @param id 供应商电子调查表明细(VendorQuestionDetail)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorQuestionDetailLogical(Long id);

    /**
     * 查询供应商电子调查表明细(VendorQuestionDetail)
     *
     * @param vendorQuestionDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQuestionDetailVO>} 状态信息
     */
    List<VendorQuestionDetailVO> queryVendorQuestionDetailVOList(VendorQuestionDetailFilterDTO vendorQuestionDetailFilterDTO);

    /**
     * 查询供应商电子调查表明细(VendorQuestionDetail)
     *
     * @param vendorQuestionDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorQuestionDetailVO>} 状态信息
     */
    PageVO<VendorQuestionDetailVO> queryVendorQuestionDetail(VendorQuestionDetailFilterDTO vendorQuestionDetailFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商电子调查表明细(VendorQuestionDetail)主键
     * @return {@link VendorQuestionDetailVO} 供应商电子调查表明细(VendorQuestionDetail)信息
     */
    VendorQuestionDetailVO queryVendorQuestionDetailById(Long id);


}

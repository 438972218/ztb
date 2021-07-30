package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestFilterDTO;
import com.xdcplus.xdcweb.biz.generator.VendorRequestBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequest;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestVO;


/**
 * 供应商注册单(VendorRequest)表服务接口层
 *
 * @author makejava
 * @since 2021-07-27 11:08:57
 */
public interface VendorRequestService extends VendorRequestBaseService<VendorRequest, VendorRequestVO, VendorRequest> {
    /**
     * 添加供应商注册单(VendorRequestDTO)
     *
     * @param vendorRequestDTO 供应商注册单(vendorRequestDTO)
     * @return {@link VendorRequestVO}
     */
    VendorRequestVO saveVendorQuestionReturnVO(VendorRequestDTO vendorRequestDTO);

    VendorRequestVO showVendorRequestById(Long id);

    /**
     * 修改供应商注册单(VendorRequestDTO)
     *
     * @param vendorRequestDTO 供应商注册单(vendorRequestDTO)
     *  @return {@link Boolean}
     */
    Boolean updateVendorRequestWithDetail(VendorRequestDTO vendorRequestDTO);
    /**
     * 查询供应商注册单(VendorRequest)
     *
     * @param vendorRequestFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <VendorRequestVO>} 状态信息
     */
    PageVO<VendorRequestVO> queryVendorRequestWithRequest(VendorRequestFilterDTO vendorRequestFilterDTO);

    
    /**
     * 保存供应商(VendorRequest)
     *
     * @param requestId 单据ID
     */
    void saveVendorByRequestId(Long requestId);
}

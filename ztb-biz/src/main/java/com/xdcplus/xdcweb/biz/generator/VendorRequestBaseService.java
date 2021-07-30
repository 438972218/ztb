package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequest;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestVO;

import java.util.List;

/**
 * 供应商注册单(VendorRequest)表服务接口层
 *
 * @author makejava
 * @since 2021-07-27 11:08:56
 */
public interface VendorRequestBaseService<S, T, E> extends BaseService<VendorRequest, VendorRequestVO, VendorRequest> {

    /**
     * 添加供应商注册单(VendorRequest)
     *
     * @param vendorRequestDTO 供应商注册单(VendorRequestDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorRequest(VendorRequestDTO vendorRequestDTO);

    /**
     * 修改供应商注册单(VendorRequest)
     *
     * @param vendorRequestDTO 供应商注册单(VendorRequestDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorRequest(VendorRequestDTO vendorRequestDTO);

    /**
     * 批量保存或更新供应商注册单(VendorRequest)
     *
     * @param vendorRequestList 供应商注册单(VendorRequestList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorRequest> vendorRequestList);

    /**
     * 批量保存或更新供应商注册单(VendorRequestDTOList)
     *
     * @param vendorRequestDTOList 供应商注册单(VendorRequestDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorRequestDTO> vendorRequestDTOList);

    /**
     * 删除供应商注册单(VendorRequest)
     *
     * @param id 供应商注册单(VendorRequest)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorRequestLogical(Long id);

    /**
     * 查询供应商注册单(VendorRequest)
     *
     * @param vendorRequestFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorRequestVO>} 状态信息
     */
    List<VendorRequestVO> queryVendorRequestVOList(VendorRequestFilterDTO vendorRequestFilterDTO);

    /**
     * 查询供应商注册单(VendorRequest)
     *
     * @param vendorRequestFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorRequestVO>} 状态信息
     */
    PageVO<VendorRequestVO> queryVendorRequest(VendorRequestFilterDTO vendorRequestFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商注册单(VendorRequest)主键
     * @return {@link VendorRequestVO} 供应商注册单(VendorRequest)信息
     */
    VendorRequestVO queryVendorRequestById(Long id);


}

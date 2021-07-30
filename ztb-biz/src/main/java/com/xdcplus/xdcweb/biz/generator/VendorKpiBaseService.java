package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpi;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiVO;

import java.util.List;

/**
 * 供应商绩效考核表(VendorKpi)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:40
 */
public interface VendorKpiBaseService<S, T, E> extends BaseService<VendorKpi, VendorKpiVO, VendorKpi> {

    /**
     * 添加供应商绩效考核表(VendorKpi)
     *
     * @param vendorKpiDTO 供应商绩效考核表(VendorKpiDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorKpi(VendorKpiDTO vendorKpiDTO);

    /**
     * 修改供应商绩效考核表(VendorKpi)
     *
     * @param vendorKpiDTO 供应商绩效考核表(VendorKpiDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorKpi(VendorKpiDTO vendorKpiDTO);

    /**
     * 批量保存或更新供应商绩效考核表(VendorKpi)
     *
     * @param vendorKpiList 供应商绩效考核表(VendorKpiList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorKpi> vendorKpiList);

    /**
     * 批量保存或更新供应商绩效考核表(VendorKpiDTOList)
     *
     * @param vendorKpiDTOList 供应商绩效考核表(VendorKpiDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorKpiDTO> vendorKpiDTOList);

    /**
     * 删除供应商绩效考核表(VendorKpi)
     *
     * @param id 供应商绩效考核表(VendorKpi)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorKpiLogical(Long id);

    /**
     * 查询供应商绩效考核表(VendorKpi)
     *
     * @param vendorKpiFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiVO>} 状态信息
     */
    List<VendorKpiVO> queryVendorKpiVOList(VendorKpiFilterDTO vendorKpiFilterDTO);

    /**
     * 查询供应商绩效考核表(VendorKpi)
     *
     * @param vendorKpiFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiVO>} 状态信息
     */
    PageVO<VendorKpiVO> queryVendorKpi(VendorKpiFilterDTO vendorKpiFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商绩效考核表(VendorKpi)主键
     * @return {@link VendorKpiVO} 供应商绩效考核表(VendorKpi)信息
     */
    VendorKpiVO queryVendorKpiById(Long id);


}

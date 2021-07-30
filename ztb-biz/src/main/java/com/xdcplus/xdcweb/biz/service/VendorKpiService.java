package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiUserVO;
import com.xdcplus.xdcweb.biz.generator.VendorKpiBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpi;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiVO;

import java.util.List;


/**
 * 供应商绩效考核表(VendorKpi)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:42
 */
public interface VendorKpiService extends VendorKpiBaseService<VendorKpi, VendorKpiVO, VendorKpi> {

    /**
     * 查询一个并带出附表中数据
     *
     * @param id 电子调查单(VendorKpi)主键
     * @return {@link VendorKpi} 电子调查单(VendorQuestion)信息
     */
    VendorKpiVO showVendorKpiById(Long id);

    /**
     * 修改供应商绩效考核表以及detail(vendorSiteInsDTO)
     *
     * @param vendorKpiDTO 供应商绩效考核表(vendorSiteInsDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorKpiWithDetail(VendorKpiDTO vendorKpiDTO);

    VendorKpiVO saveVendorKpiReturnVO(VendorKpiDTO vendorKpiDTO);

    /**
     * 查询供应商绩效考核表(request)(VendorKpi)
     *
     * @param vendorKpiFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiVO>} 状态信息
     */
    PageVO<VendorKpiVO> queryVendorKpiWithRequest(VendorKpiFilterDTO vendorKpiFilterDTO);


    /**
     * 审批完后计算分数
     * @param requestId 申请单ID
     *
     */
    void calculateScore(Long requestId);

    List<VendorKpiUserVO> getVendorKpiUserVosByRequestId(Long requestId);
}

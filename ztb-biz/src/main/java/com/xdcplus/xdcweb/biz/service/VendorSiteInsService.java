package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsUserVO;
import com.xdcplus.xdcweb.biz.generator.VendorSiteInsBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteIns;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsVO;

import java.util.List;


/**
 * 供应商现场考察表(VendorSiteIns)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:14
 */
public interface VendorSiteInsService extends VendorSiteInsBaseService<VendorSiteIns, VendorSiteInsVO, VendorSiteIns> {

    /**
     * 添加供应商现场考察表返回VO(VendorSiteIns)
     *
     * @param vendorSiteInsDTO 供应商现场考察表(VendorSiteInsDTO)
     * @return {@link Boolean} 是否成功
     */
    VendorSiteInsVO saveVendorSiteInsReturnVO(VendorSiteInsDTO vendorSiteInsDTO);

    /**
     * 查询一个并带出附表中数据
     *
     * @param id 供应商现场考察表(VendorSiteIns)主键
     * @return {@link VendorSiteInsVO} 供应商现场考察表(VendorSiteIns)信息
     */
    VendorSiteInsVO showVendorSiteIns(Long id);

    /**
     * 修改供应商现场考察表以及detail(vendorSiteInsDTO)
     *
     * @param vendorSiteInsDTO 供应商合格评审表(vendorSiteInsDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorSiteInsWithDetail(VendorSiteInsDTO vendorSiteInsDTO);

    /**
     * 查询供应商现场考察表(VendorSiteIns)
     *
     * @param vendorSiteInsFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <VendorSiteInsVO>} 状态信息
     */
    PageVO<VendorSiteInsVO> queryVendorSiteInsWithRequest(VendorSiteInsFilterDTO vendorSiteInsFilterDTO);

    /**
     * 审批完后计算分数
     * @param requestId 申请单ID
     *
     */
    void calculateScore(Long requestId);

    /**
     * 根据IDVendorSiteInsUsers表
     * @param requestId 申请单ID
     *
     */
    List<VendorSiteInsUserVO> getVendorSiteInsUserVosByRequestId(Long requestId);
}

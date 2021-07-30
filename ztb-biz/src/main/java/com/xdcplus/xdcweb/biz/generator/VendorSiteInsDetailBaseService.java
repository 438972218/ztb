package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailVO;

import java.util.List;

/**
 * 供应商现场考察表明细(VendorSiteInsDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:14
 */
public interface VendorSiteInsDetailBaseService<S, T, E> extends BaseService<VendorSiteInsDetail, VendorSiteInsDetailVO, VendorSiteInsDetail> {

    /**
     * 添加供应商现场考察表明细(VendorSiteInsDetail)
     *
     * @param vendorSiteInsDetailDTO 供应商现场考察表明细(VendorSiteInsDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorSiteInsDetail(VendorSiteInsDetailDTO vendorSiteInsDetailDTO);

    /**
     * 修改供应商现场考察表明细(VendorSiteInsDetail)
     *
     * @param vendorSiteInsDetailDTO 供应商现场考察表明细(VendorSiteInsDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorSiteInsDetail(VendorSiteInsDetailDTO vendorSiteInsDetailDTO);

    /**
     * 批量保存或更新供应商现场考察表明细(VendorSiteInsDetail)
     *
     * @param vendorSiteInsDetailList 供应商现场考察表明细(VendorSiteInsDetailList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorSiteInsDetail> vendorSiteInsDetailList);

    /**
     * 批量保存或更新供应商现场考察表明细(VendorSiteInsDetailDTOList)
     *
     * @param vendorSiteInsDetailDTOList 供应商现场考察表明细(VendorSiteInsDetailDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsDetailDTO> vendorSiteInsDetailDTOList);

    /**
     * 删除供应商现场考察表明细(VendorSiteInsDetail)
     *
     * @param id 供应商现场考察表明细(VendorSiteInsDetail)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorSiteInsDetailLogical(Long id);

    /**
     * 查询供应商现场考察表明细(VendorSiteInsDetail)
     *
     * @param vendorSiteInsDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsDetailVO>} 状态信息
     */
    List<VendorSiteInsDetailVO> queryVendorSiteInsDetailVOList(VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO);

    /**
     * 查询供应商现场考察表明细(VendorSiteInsDetail)
     *
     * @param vendorSiteInsDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsDetailVO>} 状态信息
     */
    PageVO<VendorSiteInsDetailVO> queryVendorSiteInsDetail(VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商现场考察表明细(VendorSiteInsDetail)主键
     * @return {@link VendorSiteInsDetailVO} 供应商现场考察表明细(VendorSiteInsDetail)信息
     */
    VendorSiteInsDetailVO queryVendorSiteInsDetailById(Long id);


}

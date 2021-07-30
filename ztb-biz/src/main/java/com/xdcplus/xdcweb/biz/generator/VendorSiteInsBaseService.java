package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteIns;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsVO;

import java.util.List;

/**
 * 供应商现场考察表(VendorSiteIns)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:06
 */
public interface VendorSiteInsBaseService<S, T, E> extends BaseService<VendorSiteIns, VendorSiteInsVO, VendorSiteIns> {

    /**
     * 添加供应商现场考察表(VendorSiteIns)
     *
     * @param vendorSiteInsDTO 供应商现场考察表(VendorSiteInsDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorSiteIns(VendorSiteInsDTO vendorSiteInsDTO);

    /**
     * 修改供应商现场考察表(VendorSiteIns)
     *
     * @param vendorSiteInsDTO 供应商现场考察表(VendorSiteInsDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorSiteIns(VendorSiteInsDTO vendorSiteInsDTO);

    /**
     * 批量保存或更新供应商现场考察表(VendorSiteIns)
     *
     * @param vendorSiteInsList 供应商现场考察表(VendorSiteInsList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorSiteIns> vendorSiteInsList);

    /**
     * 批量保存或更新供应商现场考察表(VendorSiteInsDTOList)
     *
     * @param vendorSiteInsDTOList 供应商现场考察表(VendorSiteInsDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsDTO> vendorSiteInsDTOList);

    /**
     * 删除供应商现场考察表(VendorSiteIns)
     *
     * @param id 供应商现场考察表(VendorSiteIns)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorSiteInsLogical(Long id);

    /**
     * 查询供应商现场考察表(VendorSiteIns)
     *
     * @param vendorSiteInsFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsVO>} 状态信息
     */
    List<VendorSiteInsVO> queryVendorSiteInsVOList(VendorSiteInsFilterDTO vendorSiteInsFilterDTO);

    /**
     * 查询供应商现场考察表(VendorSiteIns)
     *
     * @param vendorSiteInsFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsVO>} 状态信息
     */
    PageVO<VendorSiteInsVO> queryVendorSiteIns(VendorSiteInsFilterDTO vendorSiteInsFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商现场考察表(VendorSiteIns)主键
     * @return {@link VendorSiteInsVO} 供应商现场考察表(VendorSiteIns)信息
     */
    VendorSiteInsVO queryVendorSiteInsById(Long id);


}

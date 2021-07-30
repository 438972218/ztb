package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiUserVO;

import java.util.List;

/**
 * 供应商绩效人员表(VendorKpiUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:47
 */
public interface VendorKpiUserBaseService<S, T, E> extends BaseService<VendorKpiUser, VendorKpiUserVO, VendorKpiUser> {

    /**
     * 添加供应商绩效人员表(VendorKpiUser)
     *
     * @param vendorKpiUserDTO 供应商绩效人员表(VendorKpiUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorKpiUser(VendorKpiUserDTO vendorKpiUserDTO);

    /**
     * 修改供应商绩效人员表(VendorKpiUser)
     *
     * @param vendorKpiUserDTO 供应商绩效人员表(VendorKpiUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorKpiUser(VendorKpiUserDTO vendorKpiUserDTO);

    /**
     * 批量保存或更新供应商绩效人员表(VendorKpiUser)
     *
     * @param vendorKpiUserList 供应商绩效人员表(VendorKpiUserList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorKpiUser> vendorKpiUserList);

    /**
     * 批量保存或更新供应商绩效人员表(VendorKpiUserDTOList)
     *
     * @param vendorKpiUserDTOList 供应商绩效人员表(VendorKpiUserDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorKpiUserDTO> vendorKpiUserDTOList);

    /**
     * 删除供应商绩效人员表(VendorKpiUser)
     *
     * @param id 供应商绩效人员表(VendorKpiUser)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorKpiUserLogical(Long id);

    /**
     * 查询供应商绩效人员表(VendorKpiUser)
     *
     * @param vendorKpiUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiUserVO>} 状态信息
     */
    List<VendorKpiUserVO> queryVendorKpiUserVOList(VendorKpiUserFilterDTO vendorKpiUserFilterDTO);

    /**
     * 查询供应商绩效人员表(VendorKpiUser)
     *
     * @param vendorKpiUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiUserVO>} 状态信息
     */
    PageVO<VendorKpiUserVO> queryVendorKpiUser(VendorKpiUserFilterDTO vendorKpiUserFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商绩效人员表(VendorKpiUser)主键
     * @return {@link VendorKpiUserVO} 供应商绩效人员表(VendorKpiUser)信息
     */
    VendorKpiUserVO queryVendorKpiUserById(Long id);


}

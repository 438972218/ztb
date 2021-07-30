package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailUserVO;

import java.util.List;

/**
 * 供应商绩效人员评价表(VendorKpiDetailUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:45
 */
public interface VendorKpiDetailUserBaseService<S, T, E> extends BaseService<VendorKpiDetailUser, VendorKpiDetailUserVO, VendorKpiDetailUser> {

    /**
     * 添加供应商绩效人员评价表(VendorKpiDetailUser)
     *
     * @param vendorKpiDetailUserDTO 供应商绩效人员评价表(VendorKpiDetailUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorKpiDetailUser(VendorKpiDetailUserDTO vendorKpiDetailUserDTO);

    /**
     * 修改供应商绩效人员评价表(VendorKpiDetailUser)
     *
     * @param vendorKpiDetailUserDTO 供应商绩效人员评价表(VendorKpiDetailUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorKpiDetailUser(VendorKpiDetailUserDTO vendorKpiDetailUserDTO);

    /**
     * 批量保存或更新供应商绩效人员评价表(VendorKpiDetailUser)
     *
     * @param vendorKpiDetailUserList 供应商绩效人员评价表(VendorKpiDetailUserList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorKpiDetailUser> vendorKpiDetailUserList);

    /**
     * 批量保存或更新供应商绩效人员评价表(VendorKpiDetailUserDTOList)
     *
     * @param vendorKpiDetailUserDTOList 供应商绩效人员评价表(VendorKpiDetailUserDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorKpiDetailUserDTO> vendorKpiDetailUserDTOList);

    /**
     * 删除供应商绩效人员评价表(VendorKpiDetailUser)
     *
     * @param id 供应商绩效人员评价表(VendorKpiDetailUser)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorKpiDetailUserLogical(Long id);

    /**
     * 查询供应商绩效人员评价表(VendorKpiDetailUser)
     *
     * @param vendorKpiDetailUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiDetailUserVO>} 状态信息
     */
    List<VendorKpiDetailUserVO> queryVendorKpiDetailUserVOList(VendorKpiDetailUserFilterDTO vendorKpiDetailUserFilterDTO);

    /**
     * 查询供应商绩效人员评价表(VendorKpiDetailUser)
     *
     * @param vendorKpiDetailUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorKpiDetailUserVO>} 状态信息
     */
    PageVO<VendorKpiDetailUserVO> queryVendorKpiDetailUser(VendorKpiDetailUserFilterDTO vendorKpiDetailUserFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商绩效人员评价表(VendorKpiDetailUser)主键
     * @return {@link VendorKpiDetailUserVO} 供应商绩效人员评价表(VendorKpiDetailUser)信息
     */
    VendorKpiDetailUserVO queryVendorKpiDetailUserById(Long id);


}

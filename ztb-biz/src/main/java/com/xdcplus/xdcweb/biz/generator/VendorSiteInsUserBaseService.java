package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsUserVO;

import java.util.List;

/**
 * 供应商现场考察人员表(VendorSiteInsUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:21
 */
public interface VendorSiteInsUserBaseService<S, T, E> extends BaseService<VendorSiteInsUser, VendorSiteInsUserVO, VendorSiteInsUser> {

    /**
     * 添加供应商现场考察人员表(VendorSiteInsUser)
     *
     * @param vendorSiteInsUserDTO 供应商现场考察人员表(VendorSiteInsUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorSiteInsUser(VendorSiteInsUserDTO vendorSiteInsUserDTO);

    /**
     * 修改供应商现场考察人员表(VendorSiteInsUser)
     *
     * @param vendorSiteInsUserDTO 供应商现场考察人员表(VendorSiteInsUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorSiteInsUser(VendorSiteInsUserDTO vendorSiteInsUserDTO);

    /**
     * 批量保存或更新供应商现场考察人员表(VendorSiteInsUser)
     *
     * @param vendorSiteInsUserList 供应商现场考察人员表(VendorSiteInsUserList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorSiteInsUser> vendorSiteInsUserList);

    /**
     * 批量保存或更新供应商现场考察人员表(VendorSiteInsUserDTOList)
     *
     * @param vendorSiteInsUserDTOList 供应商现场考察人员表(VendorSiteInsUserDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsUserDTO> vendorSiteInsUserDTOList);

    /**
     * 删除供应商现场考察人员表(VendorSiteInsUser)
     *
     * @param id 供应商现场考察人员表(VendorSiteInsUser)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorSiteInsUserLogical(Long id);

    /**
     * 查询供应商现场考察人员表(VendorSiteInsUser)
     *
     * @param vendorSiteInsUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsUserVO>} 状态信息
     */
    List<VendorSiteInsUserVO> queryVendorSiteInsUserVOList(VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO);

    /**
     * 查询供应商现场考察人员表(VendorSiteInsUser)
     *
     * @param vendorSiteInsUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsUserVO>} 状态信息
     */
    PageVO<VendorSiteInsUserVO> queryVendorSiteInsUser(VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商现场考察人员表(VendorSiteInsUser)主键
     * @return {@link VendorSiteInsUserVO} 供应商现场考察人员表(VendorSiteInsUser)信息
     */
    VendorSiteInsUserVO queryVendorSiteInsUserById(Long id);


}

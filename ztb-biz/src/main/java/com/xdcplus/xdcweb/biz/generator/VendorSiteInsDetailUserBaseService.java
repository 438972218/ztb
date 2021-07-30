package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailUserVO;

import java.util.List;

/**
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:15
 */
public interface VendorSiteInsDetailUserBaseService<S, T, E> extends BaseService<VendorSiteInsDetailUser, VendorSiteInsDetailUserVO, VendorSiteInsDetailUser> {

    /**
     * 添加供应商现场考察人员评价表(VendorSiteInsDetailUser)
     *
     * @param vendorSiteInsDetailUserDTO 供应商现场考察人员评价表(VendorSiteInsDetailUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorSiteInsDetailUser(VendorSiteInsDetailUserDTO vendorSiteInsDetailUserDTO);

    /**
     * 修改供应商现场考察人员评价表(VendorSiteInsDetailUser)
     *
     * @param vendorSiteInsDetailUserDTO 供应商现场考察人员评价表(VendorSiteInsDetailUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorSiteInsDetailUser(VendorSiteInsDetailUserDTO vendorSiteInsDetailUserDTO);

    /**
     * 批量保存或更新供应商现场考察人员评价表(VendorSiteInsDetailUser)
     *
     * @param vendorSiteInsDetailUserList 供应商现场考察人员评价表(VendorSiteInsDetailUserList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorSiteInsDetailUser> vendorSiteInsDetailUserList);

    /**
     * 批量保存或更新供应商现场考察人员评价表(VendorSiteInsDetailUserDTOList)
     *
     * @param vendorSiteInsDetailUserDTOList 供应商现场考察人员评价表(VendorSiteInsDetailUserDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsDetailUserDTO> vendorSiteInsDetailUserDTOList);

    /**
     * 删除供应商现场考察人员评价表(VendorSiteInsDetailUser)
     *
     * @param id 供应商现场考察人员评价表(VendorSiteInsDetailUser)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorSiteInsDetailUserLogical(Long id);

    /**
     * 查询供应商现场考察人员评价表(VendorSiteInsDetailUser)
     *
     * @param vendorSiteInsDetailUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsDetailUserVO>} 状态信息
     */
    List<VendorSiteInsDetailUserVO> queryVendorSiteInsDetailUserVOList(VendorSiteInsDetailUserFilterDTO vendorSiteInsDetailUserFilterDTO);

    /**
     * 查询供应商现场考察人员评价表(VendorSiteInsDetailUser)
     *
     * @param vendorSiteInsDetailUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorSiteInsDetailUserVO>} 状态信息
     */
    PageVO<VendorSiteInsDetailUserVO> queryVendorSiteInsDetailUser(VendorSiteInsDetailUserFilterDTO vendorSiteInsDetailUserFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商现场考察人员评价表(VendorSiteInsDetailUser)主键
     * @return {@link VendorSiteInsDetailUserVO} 供应商现场考察人员评价表(VendorSiteInsDetailUser)信息
     */
    VendorSiteInsDetailUserVO queryVendorSiteInsDetailUserById(Long id);


}

package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCategory;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCategoryVO;

import java.util.List;

/**
 * 供应商注册单品类(VendorRequestCategory)表服务接口层
 *
 * @author makejava
 * @since 2021-07-27 11:08:57
 */
public interface VendorRequestCategoryBaseService<S, T, E> extends BaseService<VendorRequestCategory, VendorRequestCategoryVO, VendorRequestCategory> {

    /**
     * 添加供应商注册单品类(VendorRequestCategory)
     *
     * @param vendorRequestCategoryDTO 供应商注册单品类(VendorRequestCategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorRequestCategory(VendorRequestCategoryDTO vendorRequestCategoryDTO);

    /**
     * 修改供应商注册单品类(VendorRequestCategory)
     *
     * @param vendorRequestCategoryDTO 供应商注册单品类(VendorRequestCategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorRequestCategory(VendorRequestCategoryDTO vendorRequestCategoryDTO);

    /**
     * 批量保存或更新供应商注册单品类(VendorRequestCategory)
     *
     * @param vendorRequestCategoryList 供应商注册单品类(VendorRequestCategoryList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorRequestCategory> vendorRequestCategoryList);

    /**
     * 批量保存或更新供应商注册单品类(VendorRequestCategoryDTOList)
     *
     * @param vendorRequestCategoryDTOList 供应商注册单品类(VendorRequestCategoryDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorRequestCategoryDTO> vendorRequestCategoryDTOList);

    /**
     * 删除供应商注册单品类(VendorRequestCategory)
     *
     * @param id 供应商注册单品类(VendorRequestCategory)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorRequestCategoryLogical(Long id);

    /**
     * 查询供应商注册单品类(VendorRequestCategory)
     *
     * @param vendorRequestCategoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorRequestCategoryVO>} 状态信息
     */
    List<VendorRequestCategoryVO> queryVendorRequestCategoryVOList(VendorRequestCategoryFilterDTO vendorRequestCategoryFilterDTO);

    /**
     * 查询供应商注册单品类(VendorRequestCategory)
     *
     * @param vendorRequestCategoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorRequestCategoryVO>} 状态信息
     */
    PageVO<VendorRequestCategoryVO> queryVendorRequestCategory(VendorRequestCategoryFilterDTO vendorRequestCategoryFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商注册单品类(VendorRequestCategory)主键
     * @return {@link VendorRequestCategoryVO} 供应商注册单品类(VendorRequestCategory)信息
     */
    VendorRequestCategoryVO queryVendorRequestCategoryById(Long id);


}

package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCategory;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCategoryVO;

import java.util.List;

/**
 * 供应商品类(VendorCategory)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:36
 */
public interface VendorCategoryBaseService<S, T, E> extends BaseService<VendorCategory, VendorCategoryVO, VendorCategory> {

    /**
     * 添加供应商品类(VendorCategory)
     *
     * @param vendorCategoryDTO 供应商品类(VendorCategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorCategory(VendorCategoryDTO vendorCategoryDTO);

    /**
     * 修改供应商品类(VendorCategory)
     *
     * @param vendorCategoryDTO 供应商品类(VendorCategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorCategory(VendorCategoryDTO vendorCategoryDTO);

    /**
     * 批量保存或更新供应商品类(VendorCategory)
     *
     * @param vendorCategoryList 供应商品类(VendorCategoryList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorCategory> vendorCategoryList);

    /**
     * 批量保存或更新供应商品类(VendorCategoryDTOList)
     *
     * @param vendorCategoryDTOList 供应商品类(VendorCategoryDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorCategoryDTO> vendorCategoryDTOList);

    /**
     * 删除供应商品类(VendorCategory)
     *
     * @param id 供应商品类(VendorCategory)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorCategoryLogical(Long id);

    /**
     * 查询供应商品类(VendorCategory)
     *
     * @param vendorCategoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorCategoryVO>} 状态信息
     */
    List<VendorCategoryVO> queryVendorCategoryVOList(VendorCategoryFilterDTO vendorCategoryFilterDTO);

    /**
     * 查询供应商品类(VendorCategory)
     *
     * @param vendorCategoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorCategoryVO>} 状态信息
     */
    PageVO<VendorCategoryVO> queryVendorCategory(VendorCategoryFilterDTO vendorCategoryFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商品类(VendorCategory)主键
     * @return {@link VendorCategoryVO} 供应商品类(VendorCategory)信息
     */
    VendorCategoryVO queryVendorCategoryById(Long id);


}

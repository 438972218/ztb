package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplateQbank;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateQbankVO;

import java.util.List;

/**
 * 模板题库中间表(VendorTemplateQbank)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:30
 */
public interface VendorTemplateQbankBaseService<S, T, E> extends BaseService<VendorTemplateQbank, VendorTemplateQbankVO, VendorTemplateQbank> {

    /**
     * 添加模板题库中间表(VendorTemplateQbank)
     *
     * @param vendorTemplateQbankDTO 模板题库中间表(VendorTemplateQbankDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorTemplateQbank(VendorTemplateQbankDTO vendorTemplateQbankDTO);

    /**
     * 修改模板题库中间表(VendorTemplateQbank)
     *
     * @param vendorTemplateQbankDTO 模板题库中间表(VendorTemplateQbankDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorTemplateQbank(VendorTemplateQbankDTO vendorTemplateQbankDTO);

    /**
     * 批量保存或更新模板题库中间表(VendorTemplateQbank)
     *
     * @param vendorTemplateQbankList 模板题库中间表(VendorTemplateQbankList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorTemplateQbank> vendorTemplateQbankList);

    /**
     * 批量保存或更新模板题库中间表(VendorTemplateQbankDTOList)
     *
     * @param vendorTemplateQbankDTOList 模板题库中间表(VendorTemplateQbankDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorTemplateQbankDTO> vendorTemplateQbankDTOList);

    /**
     * 删除模板题库中间表(VendorTemplateQbank)
     *
     * @param id 模板题库中间表(VendorTemplateQbank)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorTemplateQbankLogical(Long id);

    /**
     * 查询模板题库中间表(VendorTemplateQbank)
     *
     * @param vendorTemplateQbankFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorTemplateQbankVO>} 状态信息
     */
    List<VendorTemplateQbankVO> queryVendorTemplateQbankVOList(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO);

    /**
     * 查询模板题库中间表(VendorTemplateQbank)
     *
     * @param vendorTemplateQbankFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorTemplateQbankVO>} 状态信息
     */
    PageVO<VendorTemplateQbankVO> queryVendorTemplateQbank(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO);

    /**
     * 查询一个
     *
     * @param id 模板题库中间表(VendorTemplateQbank)主键
     * @return {@link VendorTemplateQbankVO} 模板题库中间表(VendorTemplateQbank)信息
     */
    VendorTemplateQbankVO queryVendorTemplateQbankById(Long id);


}

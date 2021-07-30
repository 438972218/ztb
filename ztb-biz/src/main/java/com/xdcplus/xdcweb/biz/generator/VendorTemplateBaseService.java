package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplate;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateVO;

import java.util.List;

/**
 * 模板表(VendorTemplate)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:23
 */
public interface VendorTemplateBaseService<S, T, E> extends BaseService<VendorTemplate, VendorTemplateVO, VendorTemplate> {

    /**
     * 添加模板表(VendorTemplate)
     *
     * @param vendorTemplateDTO 模板表(VendorTemplateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorTemplate(VendorTemplateDTO vendorTemplateDTO);

    /**
     * 修改模板表(VendorTemplate)
     *
     * @param vendorTemplateDTO 模板表(VendorTemplateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorTemplate(VendorTemplateDTO vendorTemplateDTO);

    /**
     * 批量保存或更新模板表(VendorTemplate)
     *
     * @param vendorTemplateList 模板表(VendorTemplateList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorTemplate> vendorTemplateList);

    /**
     * 批量保存或更新模板表(VendorTemplateDTOList)
     *
     * @param vendorTemplateDTOList 模板表(VendorTemplateDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorTemplateDTO> vendorTemplateDTOList);

    /**
     * 删除模板表(VendorTemplate)
     *
     * @param id 模板表(VendorTemplate)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorTemplateLogical(Long id);

    /**
     * 查询模板表(VendorTemplate)
     *
     * @param vendorTemplateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorTemplateVO>} 状态信息
     */
    List<VendorTemplateVO> queryVendorTemplateVOList(VendorTemplateFilterDTO vendorTemplateFilterDTO);

    /**
     * 查询模板表(VendorTemplate)
     *
     * @param vendorTemplateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorTemplateVO>} 状态信息
     */
    PageVO<VendorTemplateVO> queryVendorTemplate(VendorTemplateFilterDTO vendorTemplateFilterDTO);

    /**
     * 查询一个
     *
     * @param id 模板表(VendorTemplate)主键
     * @return {@link VendorTemplateVO} 模板表(VendorTemplate)信息
     */
    VendorTemplateVO queryVendorTemplateById(Long id);


}

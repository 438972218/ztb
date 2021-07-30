package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorMaterialVO;

import java.util.List;

/**
 * 竞价供应商物品(PaidVendorMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:47
 */
public interface PaidVendorMaterialBaseService<S, T, E> extends BaseService<PaidVendorMaterial, PaidVendorMaterialVO, PaidVendorMaterial> {

    /**
     * 添加竞价供应商物品(PaidVendorMaterial)
     *
     * @param paidVendorMaterialDTO 竞价供应商物品(PaidVendorMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePaidVendorMaterial(PaidVendorMaterialDTO paidVendorMaterialDTO);

    /**
     * 修改竞价供应商物品(PaidVendorMaterial)
     *
     * @param paidVendorMaterialDTO 竞价供应商物品(PaidVendorMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidVendorMaterial(PaidVendorMaterialDTO paidVendorMaterialDTO);

    /**
     * 批量保存或更新竞价供应商物品(PaidVendorMaterial)
     *
     * @param paidVendorMaterialList 竞价供应商物品(PaidVendorMaterialList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PaidVendorMaterial> paidVendorMaterialList);

    /**
     * 批量保存或更新竞价供应商物品(PaidVendorMaterialDTOList)
     *
     * @param paidVendorMaterialDTOList 竞价供应商物品(PaidVendorMaterialDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PaidVendorMaterialDTO> paidVendorMaterialDTOList);

    /**
     * 删除竞价供应商物品(PaidVendorMaterial)
     *
     * @param id 竞价供应商物品(PaidVendorMaterial)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePaidVendorMaterialLogical(Long id);

    /**
     * 查询竞价供应商物品(PaidVendorMaterial)
     *
     * @param paidVendorMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidVendorMaterialVO>} 状态信息
     */
    List<PaidVendorMaterialVO> queryPaidVendorMaterialVOList(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO);

    /**
     * 查询竞价供应商物品(PaidVendorMaterial)
     *
     * @param paidVendorMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidVendorMaterialVO>} 状态信息
     */
    PageVO<PaidVendorMaterialVO> queryPaidVendorMaterial(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO);

    /**
     * 查询一个
     *
     * @param id 竞价供应商物品(PaidVendorMaterial)主键
     * @return {@link PaidVendorMaterialVO} 竞价供应商物品(PaidVendorMaterial)信息
     */
    PaidVendorMaterialVO queryPaidVendorMaterialById(Long id);


}

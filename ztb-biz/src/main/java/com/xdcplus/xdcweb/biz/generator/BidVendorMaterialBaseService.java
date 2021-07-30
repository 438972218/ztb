package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorMaterialVO;

import java.util.List;

/**
 * 招标投标方物品(BidVendorMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:10
 */
public interface BidVendorMaterialBaseService<S, T, E> extends BaseService<BidVendorMaterial, BidVendorMaterialVO, BidVendorMaterial> {

    /**
     * 添加招标投标方物品(BidVendorMaterial)
     *
     * @param bidVendorMaterialDTO 招标投标方物品(BidVendorMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidVendorMaterial(BidVendorMaterialDTO bidVendorMaterialDTO);

    /**
     * 修改招标投标方物品(BidVendorMaterial)
     *
     * @param bidVendorMaterialDTO 招标投标方物品(BidVendorMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidVendorMaterial(BidVendorMaterialDTO bidVendorMaterialDTO);

    /**
     * 批量保存或更新招标投标方物品(BidVendorMaterial)
     *
     * @param bidVendorMaterialList 招标投标方物品(BidVendorMaterialList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidVendorMaterial> bidVendorMaterialList);

    /**
     * 批量保存或更新招标投标方物品(BidVendorMaterialDTOList)
     *
     * @param bidVendorMaterialDTOList 招标投标方物品(BidVendorMaterialDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidVendorMaterialDTO> bidVendorMaterialDTOList);

    /**
     * 删除招标投标方物品(BidVendorMaterial)
     *
     * @param id 招标投标方物品(BidVendorMaterial)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidVendorMaterialLogical(Long id);

    /**
     * 查询招标投标方物品(BidVendorMaterial)
     *
     * @param bidVendorMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorMaterialVO>} 状态信息
     */
    List<BidVendorMaterialVO> queryBidVendorMaterialVOList(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO);

    /**
     * 查询招标投标方物品(BidVendorMaterial)
     *
     * @param bidVendorMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorMaterialVO>} 状态信息
     */
    PageVO<BidVendorMaterialVO> queryBidVendorMaterial(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO);

    /**
     * 查询一个
     *
     * @param id 招标投标方物品(BidVendorMaterial)主键
     * @return {@link BidVendorMaterialVO} 招标投标方物品(BidVendorMaterial)信息
     */
    BidVendorMaterialVO queryBidVendorMaterialById(Long id);


}

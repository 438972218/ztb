package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;

import java.util.List;

/**
 * 招标物品(BidMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:00
 */
public interface BidMaterialBaseService<S, T, E> extends BaseService<BidMaterial, BidMaterialVO, BidMaterial> {

    /**
     * 添加招标物品(BidMaterial)
     *
     * @param bidMaterialDTO 招标物品(BidMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidMaterial(BidMaterialDTO bidMaterialDTO);

    /**
     * 修改招标物品(BidMaterial)
     *
     * @param bidMaterialDTO 招标物品(BidMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidMaterial(BidMaterialDTO bidMaterialDTO);

    /**
     * 批量保存或更新招标物品(BidMaterial)
     *
     * @param bidMaterialList 招标物品(BidMaterialList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidMaterial> bidMaterialList);

    /**
     * 批量保存或更新招标物品(BidMaterialDTOList)
     *
     * @param bidMaterialDTOList 招标物品(BidMaterialDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidMaterialDTO> bidMaterialDTOList);

    /**
     * 删除招标物品(BidMaterial)
     *
     * @param id 招标物品(BidMaterial)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidMaterialLogical(Long id);

    /**
     * 查询招标物品(BidMaterial)
     *
     * @param bidMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidMaterialVO>} 状态信息
     */
    List<BidMaterialVO> queryBidMaterialVOList(BidMaterialFilterDTO bidMaterialFilterDTO);

    /**
     * 查询招标物品(BidMaterial)
     *
     * @param bidMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidMaterialVO>} 状态信息
     */
    PageVO<BidMaterialVO> queryBidMaterial(BidMaterialFilterDTO bidMaterialFilterDTO);

    /**
     * 查询一个
     *
     * @param id 招标物品(BidMaterial)主键
     * @return {@link BidMaterialVO} 招标物品(BidMaterial)信息
     */
    BidMaterialVO queryBidMaterialById(Long id);


}

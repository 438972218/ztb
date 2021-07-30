package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.BidMaterialBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.utils.BeanUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 招标物品(BidMaterial)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:46
 */
public class BidMaterialBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidMaterial, BidMaterialVO, BidMaterial, BidMaterialMapper> implements BidMaterialBaseService<S, T, E> {

    @Autowired
    protected BidMaterialMapper bidMaterialMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidMaterial(BidMaterialDTO bidMaterialDTO) {

        BidMaterial bidMaterial = bidMaterialMapper.selectById(bidMaterialDTO.getId());
        if (ObjectUtil.isNotNull(bidMaterial)) {
            log.error("saveBidMaterial() The BidMaterial already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidMaterial = new BidMaterial();
        BeanUtil.copyProperties(bidMaterialDTO, bidMaterial);
        bidMaterial.setCreatedTime(DateUtil.current());
        bidMaterial.setDeleted(0);

        return this.save(bidMaterial);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidMaterial(BidMaterialDTO bidMaterialDTO) {

        BidMaterial bidMaterial = this.getById(bidMaterialDTO.getId());
        if (ObjectUtil.isNull(bidMaterial)) {
            log.error("updateBidMaterial() The BidMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidMaterialDTO, bidMaterial);
        bidMaterial.setUpdatedUser(bidMaterialDTO.getUpdatedUser());
        bidMaterial.setUpdatedTime(DateUtil.current());

        return this.updateById(bidMaterial);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidMaterial> bidMaterialList) {

        bidMaterialList.forEach(bidMaterial -> {
            BidMaterial bidMaterialParam = new BidMaterial();
            bidMaterialParam.setId(bidMaterial.getId());
            if (ObjectUtil.isNotNull(bidMaterial.getId())) {
                bidMaterial.setId(bidMaterial.getId());
                bidMaterial.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidMaterial> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidMaterial::getId, bidMaterial.getId());
                update(bidMaterial, lambdaUpdate);
            } else {
                bidMaterial.setCreatedTime(DateUtil.current());
                bidMaterial.setDeleted(0);
                save(bidMaterial);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidMaterialDTO> bidMaterialDTOList) {

        List<BidMaterial> bidMaterialList = BeanUtils.copyProperties(bidMaterialDTOList, BidMaterial.class);
        return saveOrUpdateBatch(bidMaterialList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidMaterialLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidMaterial bidMaterial = this.getById(id);

        if (ObjectUtil.isNull(bidMaterial)) {
            log.error("deleteBidMaterial() The BidMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidMaterial.setDeleted(1);

        return this.updateById(bidMaterial);
    }

    private List<BidMaterial> queryBidMaterialList(BidMaterialFilterDTO bidMaterialFilterDTO) {
        if (ObjectUtil.isNull(bidMaterialFilterDTO)) {
            bidMaterialFilterDTO = new BidMaterialFilterDTO();
        }
        bidMaterialFilterDTO.setDeleted(0);
        BidMaterialQuery bidMaterialQuery = BeanUtil.copyProperties(bidMaterialFilterDTO, BidMaterialQuery.class);

        return bidMaterialMapper.queryBidMaterial(bidMaterialQuery);
    }

    @Override
    public List<BidMaterialVO> queryBidMaterialVOList(BidMaterialFilterDTO bidMaterialFilterDTO) {
        if (ObjectUtil.isNull(bidMaterialFilterDTO)) {
            bidMaterialFilterDTO = new BidMaterialFilterDTO();
        }
        bidMaterialFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidMaterialList(bidMaterialFilterDTO));
    }

    @Override
    public PageVO<BidMaterialVO> queryBidMaterial(BidMaterialFilterDTO bidMaterialFilterDTO) {
        if (ObjectUtil.isNull(bidMaterialFilterDTO)) {
            bidMaterialFilterDTO = new BidMaterialFilterDTO();
        }
        bidMaterialFilterDTO.setDeleted(0);
        PageVO<BidMaterialVO> pageVO = new PageVO<>();

        if (bidMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidMaterialFilterDTO.getCurrentPage(), bidMaterialFilterDTO.getPageSize(),
                    bidMaterialFilterDTO.getOrderType(), bidMaterialFilterDTO.getOrderField());
        }

        List<BidMaterial> bidMaterialList = queryBidMaterialList(bidMaterialFilterDTO);

        PageInfo<BidMaterial> pageInfo = new PageInfo<>(bidMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidMaterialList));

        return pageVO;
    }

    @Override
    public BidMaterialVO queryBidMaterialById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidVendorMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.BidVendorMaterialBaseService;
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
 * 招标投标方物品(BidVendorMaterial)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:54
 */
public class BidVendorMaterialBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidVendorMaterial, BidVendorMaterialVO, BidVendorMaterial, BidVendorMaterialMapper> implements BidVendorMaterialBaseService<S, T, E> {

    @Autowired
    protected BidVendorMaterialMapper bidVendorMaterialMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidVendorMaterial(BidVendorMaterialDTO bidVendorMaterialDTO) {

        BidVendorMaterial bidVendorMaterial = bidVendorMaterialMapper.selectById(bidVendorMaterialDTO.getId());
        if (ObjectUtil.isNotNull(bidVendorMaterial)) {
            log.error("saveBidVendorMaterial() The BidVendorMaterial already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidVendorMaterial = new BidVendorMaterial();
        BeanUtil.copyProperties(bidVendorMaterialDTO, bidVendorMaterial);
        bidVendorMaterial.setCreatedTime(DateUtil.current());
        bidVendorMaterial.setDeleted(0);

        return this.save(bidVendorMaterial);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidVendorMaterial(BidVendorMaterialDTO bidVendorMaterialDTO) {

        BidVendorMaterial bidVendorMaterial = this.getById(bidVendorMaterialDTO.getId());
        if (ObjectUtil.isNull(bidVendorMaterial)) {
            log.error("updateBidVendorMaterial() The BidVendorMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidVendorMaterialDTO, bidVendorMaterial);
        bidVendorMaterial.setUpdatedUser(bidVendorMaterialDTO.getUpdatedUser());
        bidVendorMaterial.setUpdatedTime(DateUtil.current());

        return this.updateById(bidVendorMaterial);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidVendorMaterial> bidVendorMaterialList) {

        bidVendorMaterialList.forEach(bidVendorMaterial -> {
            BidVendorMaterial bidVendorMaterialParam = new BidVendorMaterial();
            bidVendorMaterialParam.setId(bidVendorMaterial.getId());
            if (ObjectUtil.isNotNull(bidVendorMaterial.getId())) {
                bidVendorMaterial.setId(bidVendorMaterial.getId());
                bidVendorMaterial.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidVendorMaterial> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidVendorMaterial::getId, bidVendorMaterial.getId());
                update(bidVendorMaterial, lambdaUpdate);
            } else {
                bidVendorMaterial.setCreatedTime(DateUtil.current());
                bidVendorMaterial.setDeleted(0);
                save(bidVendorMaterial);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidVendorMaterialDTO> bidVendorMaterialDTOList) {

        List<BidVendorMaterial> bidVendorMaterialList = BeanUtils.copyProperties(bidVendorMaterialDTOList, BidVendorMaterial.class);
        return saveOrUpdateBatch(bidVendorMaterialList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidVendorMaterialLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidVendorMaterial bidVendorMaterial = this.getById(id);

        if (ObjectUtil.isNull(bidVendorMaterial)) {
            log.error("deleteBidVendorMaterial() The BidVendorMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidVendorMaterial.setDeleted(1);

        return this.updateById(bidVendorMaterial);
    }

    private List<BidVendorMaterial> queryBidVendorMaterialList(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(bidVendorMaterialFilterDTO)) {
            bidVendorMaterialFilterDTO = new BidVendorMaterialFilterDTO();
        }
        bidVendorMaterialFilterDTO.setDeleted(0);
        BidVendorMaterialQuery bidVendorMaterialQuery = BeanUtil.copyProperties(bidVendorMaterialFilterDTO, BidVendorMaterialQuery.class);

        return bidVendorMaterialMapper.queryBidVendorMaterial(bidVendorMaterialQuery);
    }

    @Override
    public List<BidVendorMaterialVO> queryBidVendorMaterialVOList(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(bidVendorMaterialFilterDTO)) {
            bidVendorMaterialFilterDTO = new BidVendorMaterialFilterDTO();
        }
        bidVendorMaterialFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidVendorMaterialList(bidVendorMaterialFilterDTO));
    }

    @Override
    public PageVO<BidVendorMaterialVO> queryBidVendorMaterial(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(bidVendorMaterialFilterDTO)) {
            bidVendorMaterialFilterDTO = new BidVendorMaterialFilterDTO();
        }
        bidVendorMaterialFilterDTO.setDeleted(0);
        PageVO<BidVendorMaterialVO> pageVO = new PageVO<>();

        if (bidVendorMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidVendorMaterialFilterDTO.getCurrentPage(), bidVendorMaterialFilterDTO.getPageSize(),
                    bidVendorMaterialFilterDTO.getOrderType(), bidVendorMaterialFilterDTO.getOrderField());
        }

        List<BidVendorMaterial> bidVendorMaterialList = queryBidVendorMaterialList(bidVendorMaterialFilterDTO);

        PageInfo<BidVendorMaterial> pageInfo = new PageInfo<>(bidVendorMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidVendorMaterialList));

        return pageVO;
    }

    @Override
    public BidVendorMaterialVO queryBidVendorMaterialById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

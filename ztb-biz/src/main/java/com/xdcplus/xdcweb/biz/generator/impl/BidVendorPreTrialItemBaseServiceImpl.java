package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidVendorPreTrialItemMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorPreTrialItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorPreTrialItemVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorPreTrialItemQuery;
import com.xdcplus.xdcweb.biz.generator.BidVendorPreTrialItemBaseService;
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
 * 招标投标方预审项(BidVendorPreTrialItem)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:55
 */
public class BidVendorPreTrialItemBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidVendorPreTrialItem, BidVendorPreTrialItemVO, BidVendorPreTrialItem, BidVendorPreTrialItemMapper> implements BidVendorPreTrialItemBaseService<S, T, E> {

    @Autowired
    protected BidVendorPreTrialItemMapper bidVendorPreTrialItemMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidVendorPreTrialItem(BidVendorPreTrialItemDTO bidVendorPreTrialItemDTO) {

        BidVendorPreTrialItem bidVendorPreTrialItem = bidVendorPreTrialItemMapper.selectById(bidVendorPreTrialItemDTO.getId());
        if (ObjectUtil.isNotNull(bidVendorPreTrialItem)) {
            log.error("saveBidVendorPreTrialItem() The BidVendorPreTrialItem already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidVendorPreTrialItem = new BidVendorPreTrialItem();
        BeanUtil.copyProperties(bidVendorPreTrialItemDTO, bidVendorPreTrialItem);
        bidVendorPreTrialItem.setCreatedTime(DateUtil.current());
        bidVendorPreTrialItem.setDeleted(0);

        return this.save(bidVendorPreTrialItem);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidVendorPreTrialItem(BidVendorPreTrialItemDTO bidVendorPreTrialItemDTO) {

        BidVendorPreTrialItem bidVendorPreTrialItem = this.getById(bidVendorPreTrialItemDTO.getId());
        if (ObjectUtil.isNull(bidVendorPreTrialItem)) {
            log.error("updateBidVendorPreTrialItem() The BidVendorPreTrialItem does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidVendorPreTrialItemDTO, bidVendorPreTrialItem);
        bidVendorPreTrialItem.setUpdatedUser(bidVendorPreTrialItemDTO.getUpdatedUser());
        bidVendorPreTrialItem.setUpdatedTime(DateUtil.current());

        return this.updateById(bidVendorPreTrialItem);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidVendorPreTrialItem> bidVendorPreTrialItemList) {

        bidVendorPreTrialItemList.forEach(bidVendorPreTrialItem -> {
            BidVendorPreTrialItem bidVendorPreTrialItemParam = new BidVendorPreTrialItem();
            bidVendorPreTrialItemParam.setId(bidVendorPreTrialItem.getId());
            if (ObjectUtil.isNotNull(bidVendorPreTrialItem.getId())) {
                bidVendorPreTrialItem.setId(bidVendorPreTrialItem.getId());
                bidVendorPreTrialItem.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidVendorPreTrialItem> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidVendorPreTrialItem::getId, bidVendorPreTrialItem.getId());
                update(bidVendorPreTrialItem, lambdaUpdate);
            } else {
                bidVendorPreTrialItem.setCreatedTime(DateUtil.current());
                bidVendorPreTrialItem.setDeleted(0);
                save(bidVendorPreTrialItem);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidVendorPreTrialItemDTO> bidVendorPreTrialItemDTOList) {

        List<BidVendorPreTrialItem> bidVendorPreTrialItemList = BeanUtils.copyProperties(bidVendorPreTrialItemDTOList, BidVendorPreTrialItem.class);
        return saveOrUpdateBatch(bidVendorPreTrialItemList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidVendorPreTrialItemLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidVendorPreTrialItem bidVendorPreTrialItem = this.getById(id);

        if (ObjectUtil.isNull(bidVendorPreTrialItem)) {
            log.error("deleteBidVendorPreTrialItem() The BidVendorPreTrialItem does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidVendorPreTrialItem.setDeleted(1);

        return this.updateById(bidVendorPreTrialItem);
    }

    private List<BidVendorPreTrialItem> queryBidVendorPreTrialItemList(BidVendorPreTrialItemFilterDTO bidVendorPreTrialItemFilterDTO) {
        if (ObjectUtil.isNull(bidVendorPreTrialItemFilterDTO)) {
            bidVendorPreTrialItemFilterDTO = new BidVendorPreTrialItemFilterDTO();
        }
        bidVendorPreTrialItemFilterDTO.setDeleted(0);
        BidVendorPreTrialItemQuery bidVendorPreTrialItemQuery = BeanUtil.copyProperties(bidVendorPreTrialItemFilterDTO, BidVendorPreTrialItemQuery.class);

        return bidVendorPreTrialItemMapper.queryBidVendorPreTrialItem(bidVendorPreTrialItemQuery);
    }

    @Override
    public List<BidVendorPreTrialItemVO> queryBidVendorPreTrialItemVOList(BidVendorPreTrialItemFilterDTO bidVendorPreTrialItemFilterDTO) {
        if (ObjectUtil.isNull(bidVendorPreTrialItemFilterDTO)) {
            bidVendorPreTrialItemFilterDTO = new BidVendorPreTrialItemFilterDTO();
        }
        bidVendorPreTrialItemFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidVendorPreTrialItemList(bidVendorPreTrialItemFilterDTO));
    }

    @Override
    public PageVO<BidVendorPreTrialItemVO> queryBidVendorPreTrialItem(BidVendorPreTrialItemFilterDTO bidVendorPreTrialItemFilterDTO) {
        if (ObjectUtil.isNull(bidVendorPreTrialItemFilterDTO)) {
            bidVendorPreTrialItemFilterDTO = new BidVendorPreTrialItemFilterDTO();
        }
        bidVendorPreTrialItemFilterDTO.setDeleted(0);
        PageVO<BidVendorPreTrialItemVO> pageVO = new PageVO<>();

        if (bidVendorPreTrialItemFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidVendorPreTrialItemFilterDTO.getCurrentPage(), bidVendorPreTrialItemFilterDTO.getPageSize(),
                    bidVendorPreTrialItemFilterDTO.getOrderType(), bidVendorPreTrialItemFilterDTO.getOrderField());
        }

        List<BidVendorPreTrialItem> bidVendorPreTrialItemList = queryBidVendorPreTrialItemList(bidVendorPreTrialItemFilterDTO);

        PageInfo<BidVendorPreTrialItem> pageInfo = new PageInfo<>(bidVendorPreTrialItemList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidVendorPreTrialItemList));

        return pageVO;
    }

    @Override
    public BidVendorPreTrialItemVO queryBidVendorPreTrialItemById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidPreTrialItemMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidPreTrialItemVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidPreTrialItemQuery;
import com.xdcplus.xdcweb.biz.generator.BidPreTrialItemBaseService;
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
 * 预审项(BidPreTrialItem)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:47
 */
public class BidPreTrialItemBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidPreTrialItem, BidPreTrialItemVO, BidPreTrialItem, BidPreTrialItemMapper> implements BidPreTrialItemBaseService<S, T, E> {

    @Autowired
    protected BidPreTrialItemMapper bidPreTrialItemMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidPreTrialItem(BidPreTrialItemDTO bidPreTrialItemDTO) {

        BidPreTrialItem bidPreTrialItem = bidPreTrialItemMapper.selectById(bidPreTrialItemDTO.getId());
        if (ObjectUtil.isNotNull(bidPreTrialItem)) {
            log.error("saveBidPreTrialItem() The BidPreTrialItem already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidPreTrialItem = new BidPreTrialItem();
        BeanUtil.copyProperties(bidPreTrialItemDTO, bidPreTrialItem);
        bidPreTrialItem.setCreatedTime(DateUtil.current());
        bidPreTrialItem.setDeleted(0);

        return this.save(bidPreTrialItem);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidPreTrialItem(BidPreTrialItemDTO bidPreTrialItemDTO) {

        BidPreTrialItem bidPreTrialItem = this.getById(bidPreTrialItemDTO.getId());
        if (ObjectUtil.isNull(bidPreTrialItem)) {
            log.error("updateBidPreTrialItem() The BidPreTrialItem does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidPreTrialItemDTO, bidPreTrialItem);
        bidPreTrialItem.setUpdatedUser(bidPreTrialItemDTO.getUpdatedUser());
        bidPreTrialItem.setUpdatedTime(DateUtil.current());

        return this.updateById(bidPreTrialItem);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidPreTrialItem> bidPreTrialItemList) {

        bidPreTrialItemList.forEach(bidPreTrialItem -> {
            BidPreTrialItem bidPreTrialItemParam = new BidPreTrialItem();
            bidPreTrialItemParam.setId(bidPreTrialItem.getId());
            if (ObjectUtil.isNotNull(bidPreTrialItem.getId())) {
                bidPreTrialItem.setId(bidPreTrialItem.getId());
                bidPreTrialItem.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidPreTrialItem> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidPreTrialItem::getId, bidPreTrialItem.getId());
                update(bidPreTrialItem, lambdaUpdate);
            } else {
                bidPreTrialItem.setCreatedTime(DateUtil.current());
                bidPreTrialItem.setDeleted(0);
                save(bidPreTrialItem);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidPreTrialItemDTO> bidPreTrialItemDTOList) {

        List<BidPreTrialItem> bidPreTrialItemList = BeanUtils.copyProperties(bidPreTrialItemDTOList, BidPreTrialItem.class);
        return saveOrUpdateBatch(bidPreTrialItemList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidPreTrialItemLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidPreTrialItem bidPreTrialItem = this.getById(id);

        if (ObjectUtil.isNull(bidPreTrialItem)) {
            log.error("deleteBidPreTrialItem() The BidPreTrialItem does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidPreTrialItem.setDeleted(1);

        return this.updateById(bidPreTrialItem);
    }

    private List<BidPreTrialItem> queryBidPreTrialItemList(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO) {
        if (ObjectUtil.isNull(bidPreTrialItemFilterDTO)) {
            bidPreTrialItemFilterDTO = new BidPreTrialItemFilterDTO();
        }
        bidPreTrialItemFilterDTO.setDeleted(0);
        BidPreTrialItemQuery bidPreTrialItemQuery = BeanUtil.copyProperties(bidPreTrialItemFilterDTO, BidPreTrialItemQuery.class);

        return bidPreTrialItemMapper.queryBidPreTrialItem(bidPreTrialItemQuery);
    }

    @Override
    public List<BidPreTrialItemVO> queryBidPreTrialItemVOList(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO) {
        if (ObjectUtil.isNull(bidPreTrialItemFilterDTO)) {
            bidPreTrialItemFilterDTO = new BidPreTrialItemFilterDTO();
        }
        bidPreTrialItemFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidPreTrialItemList(bidPreTrialItemFilterDTO));
    }

    @Override
    public PageVO<BidPreTrialItemVO> queryBidPreTrialItem(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO) {
        if (ObjectUtil.isNull(bidPreTrialItemFilterDTO)) {
            bidPreTrialItemFilterDTO = new BidPreTrialItemFilterDTO();
        }
        bidPreTrialItemFilterDTO.setDeleted(0);
        PageVO<BidPreTrialItemVO> pageVO = new PageVO<>();

        if (bidPreTrialItemFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidPreTrialItemFilterDTO.getCurrentPage(), bidPreTrialItemFilterDTO.getPageSize(),
                    bidPreTrialItemFilterDTO.getOrderType(), bidPreTrialItemFilterDTO.getOrderField());
        }

        List<BidPreTrialItem> bidPreTrialItemList = queryBidPreTrialItemList(bidPreTrialItemFilterDTO);

        PageInfo<BidPreTrialItem> pageInfo = new PageInfo<>(bidPreTrialItemList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidPreTrialItemList));

        return pageVO;
    }

    @Override
    public BidPreTrialItemVO queryBidPreTrialItemById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

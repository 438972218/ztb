package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.ItemMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Item;
import com.xdcplus.xdcweb.biz.common.pojo.dto.ItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.ItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ItemVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.ItemQuery;
import com.xdcplus.xdcweb.biz.generator.ItemBaseService;
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
 * 物料管理(Item)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:12
 */
public class ItemBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<Item, ItemVO, Item, ItemMapper> implements ItemBaseService<S, T, E> {

    @Autowired
    protected ItemMapper itemMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveItem(ItemDTO itemDTO) {

        Item item = itemMapper.selectById(itemDTO.getId());
        if (ObjectUtil.isNotNull(item)) {
            log.error("saveItem() The Item already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        item = new Item();
        BeanUtil.copyProperties(itemDTO, item);
        item.setCreatedTime(DateUtil.current());
        item.setDeleted(0);

        return this.save(item);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateItem(ItemDTO itemDTO) {

        Item item = this.getById(itemDTO.getId());
        if (ObjectUtil.isNull(item)) {
            log.error("updateItem() The Item does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(itemDTO, item);
        item.setUpdatedUser(itemDTO.getUpdatedUser());
        item.setUpdatedTime(DateUtil.current());

        return this.updateById(item);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<Item> itemList) {

        itemList.forEach(item -> {
            Item itemParam = new Item();
            itemParam.setId(item.getId());
            if (ObjectUtil.isNotNull(item.getId())) {
                item.setId(item.getId());
                item.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<Item> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(Item::getId, item.getId());
                update(item, lambdaUpdate);
            } else {
                item.setCreatedTime(DateUtil.current());
                item.setDeleted(0);
                save(item);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<ItemDTO> itemDTOList) {

        List<Item> itemList = BeanUtils.copyProperties(itemDTOList, Item.class);
        return saveOrUpdateBatch(itemList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteItemLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Item item = this.getById(id);

        if (ObjectUtil.isNull(item)) {
            log.error("deleteItem() The Item does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        item.setDeleted(1);

        return this.updateById(item);
    }

    private List<Item> queryItemList(ItemFilterDTO itemFilterDTO) {
        if (ObjectUtil.isNull(itemFilterDTO)) {
            itemFilterDTO = new ItemFilterDTO();
        }
        itemFilterDTO.setDeleted(0);
        ItemQuery itemQuery = BeanUtil.copyProperties(itemFilterDTO, ItemQuery.class);

        return itemMapper.queryItem(itemQuery);
    }

    @Override
    public List<ItemVO> queryItemVOList(ItemFilterDTO itemFilterDTO) {
        if (ObjectUtil.isNull(itemFilterDTO)) {
            itemFilterDTO = new ItemFilterDTO();
        }
        itemFilterDTO.setDeleted(0);
        return this.objectConversion(queryItemList(itemFilterDTO));
    }

    @Override
    public PageVO<ItemVO> queryItem(ItemFilterDTO itemFilterDTO) {
        if (ObjectUtil.isNull(itemFilterDTO)) {
            itemFilterDTO = new ItemFilterDTO();
        }
        itemFilterDTO.setDeleted(0);
        PageVO<ItemVO> pageVO = new PageVO<>();

        if (itemFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(itemFilterDTO.getCurrentPage(), itemFilterDTO.getPageSize(),
                    itemFilterDTO.getOrderType(), itemFilterDTO.getOrderField());
        }

        List<Item> itemList = queryItemList(itemFilterDTO);

        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(itemList));

        return pageVO;
    }

    @Override
    public ItemVO queryItemById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

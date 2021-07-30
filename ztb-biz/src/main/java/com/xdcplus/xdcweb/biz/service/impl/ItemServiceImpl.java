package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.ItemBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.ItemMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Item;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ItemVO;
import com.xdcplus.xdcweb.biz.service.ItemService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 物料管理(Item)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:52:49
 */
@Slf4j
@Service("itemService")
public class ItemServiceImpl extends ItemBaseServiceImpl<Item, ItemVO, Item, ItemMapper> implements ItemService {


}

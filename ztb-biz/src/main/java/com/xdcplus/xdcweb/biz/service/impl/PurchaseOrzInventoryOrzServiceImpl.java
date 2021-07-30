package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.mp.utils.AuthUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzFilterDTO;
import com.xdcplus.xdcweb.biz.generator.impl.PurchaseOrzInventoryOrzBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PurchaseOrzInventoryOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrzInventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzInventoryOrzVO;
import com.xdcplus.xdcweb.biz.service.PurchaseOrzInventoryOrzService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:41
 */
@Slf4j
@Service("purchaseOrzInventoryOrzService")
public class PurchaseOrzInventoryOrzServiceImpl extends PurchaseOrzInventoryOrzBaseServiceImpl<PurchaseOrzInventoryOrz, PurchaseOrzInventoryOrzVO, PurchaseOrzInventoryOrz, PurchaseOrzInventoryOrzMapper> implements PurchaseOrzInventoryOrzService {


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean maintenance(PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO) {
        Assert.notNull(purchaseOrzInventoryOrzDTO.getPurchaseOrzId(), ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        QueryWrapper<PurchaseOrzInventoryOrz> qw = new QueryWrapper<>();
        qw.eq("purchase_orz_id", purchaseOrzInventoryOrzDTO.getPurchaseOrzId());
        purchaseOrzInventoryOrzMapper.delete(qw);

        if (CollUtil.isNotEmpty(purchaseOrzInventoryOrzDTO.getInventoryOrzIds())) {
            PurchaseOrzInventoryOrz purchaseOrzInventoryOrz;
            for (Long id : purchaseOrzInventoryOrzDTO.getInventoryOrzIds()) {
                purchaseOrzInventoryOrz = new PurchaseOrzInventoryOrz();
                purchaseOrzInventoryOrz.setPurchaseOrzId(purchaseOrzInventoryOrzDTO.getPurchaseOrzId());
                purchaseOrzInventoryOrz.setInventoryOrzId(id);
                purchaseOrzInventoryOrz.setCreatedUser(AuthUtils.getCurrentUser());
                purchaseOrzInventoryOrz.setCreatedTime(DateUtil.current());
                purchaseOrzInventoryOrz.setDeleted(0);
                save(purchaseOrzInventoryOrz);
            }
        }
        return true;
    }

    @Override
    public PurchaseOrzInventoryOrzVO getMaintenance(Long purchaseOrzId) {
        PurchaseOrzInventoryOrzFilterDTO purchaseOrzInventoryOrzFilterDTO=new PurchaseOrzInventoryOrzFilterDTO();
        purchaseOrzInventoryOrzFilterDTO.setPurchaseOrzId(purchaseOrzId);
        purchaseOrzInventoryOrzFilterDTO.setDeleted(0);
        List<PurchaseOrzInventoryOrzVO> purchaseOrzInventoryOrzVOS =queryPurchaseOrzInventoryOrzVOList(purchaseOrzInventoryOrzFilterDTO);
        PurchaseOrzInventoryOrzVO purchaseOrzInventoryOrzVO=new PurchaseOrzInventoryOrzVO();
        purchaseOrzInventoryOrzVO.setPurchaseOrzId(purchaseOrzId);
        if(CollectionUtil.isNotEmpty(purchaseOrzInventoryOrzVOS)){
            purchaseOrzInventoryOrzVO.setInventoryOrzIds(purchaseOrzInventoryOrzVOS.stream().map(s -> s.getInventoryOrzId()).collect(Collectors.toList()));
        }
        return purchaseOrzInventoryOrzVO;
    }
}

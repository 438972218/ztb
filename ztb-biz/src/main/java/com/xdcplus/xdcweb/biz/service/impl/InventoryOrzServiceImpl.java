package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.permission.PceCompanyResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzInventoryOrzVO;
import com.xdcplus.xdcweb.biz.generator.impl.InventoryOrzBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InventoryOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InventoryOrzVO;
import com.xdcplus.xdcweb.biz.service.InventoryOrzService;
import com.xdcplus.xdcweb.biz.service.PurchaseOrzInventoryOrzService;
import com.xdcplus.xdcweb.biz.service.PurchaseOrzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 库存组织(InventoryOrz)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:52:45
 */
@Slf4j
@Service("inventoryOrzService")
public class InventoryOrzServiceImpl extends InventoryOrzBaseServiceImpl<InventoryOrz, InventoryOrzVO, InventoryOrz, InventoryOrzMapper> implements InventoryOrzService {

    @Autowired
    private PurchaseOrzInventoryOrzService purchaseOrzInventoryOrzService;

    @Autowired
    private PurchaseOrzService purchaseOrzService;

    @Override
    public List<CompanyVO> queryInventoryOrzTree() {
        List<CompanyVO> companyVOs = PceCompanyResponseUtils.getSysCompanyTree();
        companyVOs.forEach(c -> {
            c.setType("0");
            combineTree(c);
        });
        return companyVOs;
    }

    //组合两个树
    private void combineTree(CompanyVO c) {
        if (c.getChildren() != null) {
            for (CompanyVO v : c.getChildren()) {
                v.setType("0");
                combineTree(v);
            }
        } else {
            c.setChildren(getOrzByCompanyId(c.getId()));
        }
    }

    //根据工厂找到组织列表
    private List<CompanyVO> getOrzByCompanyId(Long companyId) {
        List<InventoryOrz> inventoryOrzs = inventoryOrzMapper.selectList(new QueryWrapper<InventoryOrz>().eq("company_id", companyId).eq("deleted", 0).and(qw -> qw.isNull("p_id").or().eq("p_id", 0)));
        if (CollUtil.isEmpty(inventoryOrzs)) {
            return null;
        }
        List<CompanyVO> companyVOs = new ArrayList<>();
        inventoryOrzs.forEach(inventoryOrz -> companyVOs.add(recursiveTree(inventoryOrz.getId())));
        return companyVOs;
    }

    //递归子组织并转化成工厂
    private CompanyVO recursiveTree(Long id) {
        InventoryOrz inventoryOrz = inventoryOrzMapper.selectById(id);
        //转成company
        CompanyVO companyVO = new CompanyVO();
        companyVO.setId(inventoryOrz.getId());
        companyVO.setShortName(inventoryOrz.getName());
        companyVO.setParentId(inventoryOrz.getPId());
        companyVO.setType(inventoryOrz.getType());

        List<InventoryOrz> childTreeInventoryOrzs = inventoryOrzMapper.selectList((new QueryWrapper<InventoryOrz>()).eq("p_id", id).eq("deleted", 0));

        //遍历子节点
        for (InventoryOrz childInventoryOrz : childTreeInventoryOrzs) {
            CompanyVO companyVO1 = recursiveTree(childInventoryOrz.getId()); //递归
            if (companyVO.getChildren() != null) {
                companyVO.getChildren().add(companyVO1);
            } else {
                List<CompanyVO> cDTOS = new ArrayList<>();
                cDTOS.add(companyVO1);
                companyVO.setChildren(cDTOS);
            }
        }

        return companyVO;
    }

    @Override
    public List<CompanyVO> findFactoryTree() {
        List<InventoryOrz> inventoryOrzs = inventoryOrzMapper.selectList(new QueryWrapper<InventoryOrz>().eq("p_id", 0));
        if (CollUtil.isEmpty(inventoryOrzs)) {
            return null;
        }
        Map<Long, List<CompanyVO>> result = new HashMap<>();
        inventoryOrzs.forEach(inventoryOrz -> {
            List<CompanyVO> companyVOS;
            if (inventoryOrz.getType() != null && ZtbConstant.PLANT.equals(inventoryOrz.getType())) {
                if (CollUtil.isEmpty(result.get(inventoryOrz.getCompanyId()))) {
                    companyVOS = new ArrayList<>();
                    CompanyVO companyVO = new CompanyVO();
                    companyVO.setId(inventoryOrz.getId());
                    companyVO.setShortName(inventoryOrz.getName());
                    companyVO.setType(inventoryOrz.getType());
                    companyVOS.add(companyVO);
                    result.put(inventoryOrz.getCompanyId(), companyVOS);
                } else {
                    CompanyVO companyVO = new CompanyVO();
                    companyVO.setId(inventoryOrz.getId());
                    companyVO.setShortName(inventoryOrz.getName());
                    companyVO.setType(inventoryOrz.getType());
                    result.get(inventoryOrz.getCompanyId()).add(companyVO);
                }
            }
        });


        return map2List(result);
    }


    private List<CompanyVO> map2List(Map<Long, List<CompanyVO>> map) {
        if (CollUtil.isEmpty(map)) {
            return null;
        }
        Iterator<Long> it = map.keySet().iterator();
        List<CompanyVO> companyVOS = new ArrayList<>();
        while (it.hasNext()) {
            Long k = it.next().longValue();
            CompanyVO companyVO = PceCompanyResponseUtils.queryById(k);
            companyVO.setType("0");
            companyVO.setDisabled(true);
            companyVO.setChildren(map.get(k));
            companyVOS.add(companyVO);
        }
        return companyVOS;
    }

    @Override
    public List<CompanyVO> findFactoryTreeBySelect(Long purchaseOrzId) {
        List<CompanyVO> companyVOS = this.findFactoryTree();
        List<Long> inOrzs = this.getAllInventoryOrzIdsByPurchaseOrzId(purchaseOrzId);
        if (CollectionUtil.isEmpty(inOrzs)) {
            return companyVOS;
        }
        companyVOS.forEach(companyVO -> {
            List<CompanyVO> companyVOS1 = companyVO.getChildren();
            if (CollectionUtil.isNotEmpty(companyVOS1)) {
                companyVOS1.forEach(companyVO1 -> {
                    companyVO1.setDisabled(true);
                    if (CollectionUtil.contains(inOrzs, companyVO1.getId())) {
                        companyVO1.setDisabled(false);
                    }
                });
            }
        });
        return companyVOS;
    }

    //获取父采购组织是否关联的工厂(库存)IDS
    private List<Long> getAllInventoryOrzIdsByPurchaseOrzId(Long purchaseOrzId) {
        PurchaseOrz purchaseOrz = purchaseOrzService.getById(purchaseOrzId);
        List<Long> inventoryOrzIds = new ArrayList<>();
        if (ObjectUtil.isNull(purchaseOrz)) {
            return inventoryOrzIds;
        }
        Long id = purchaseOrz.getPId();
        while (id != 0) {
            PurchaseOrzInventoryOrzVO purchaseOrzInventoryOrzVO = purchaseOrzInventoryOrzService.getMaintenance(id);
            if (CollectionUtil.isNotEmpty(purchaseOrzInventoryOrzVO.getInventoryOrzIds())) {
                inventoryOrzIds.addAll(purchaseOrzInventoryOrzVO.getInventoryOrzIds());
            }
            id = purchaseOrzService.ifHasParent(id);
        }
        return inventoryOrzIds;
    }

}

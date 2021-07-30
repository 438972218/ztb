package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.common.permission.PceCompanyResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.generator.impl.PurchaseOrzBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PurchaseOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzVO;
import com.xdcplus.xdcweb.biz.service.PurchaseOrzService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 采购组织(PurchaseOrz)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:40
 */
@Slf4j
@Service("purchaseOrzService")
public class PurchaseOrzServiceImpl extends PurchaseOrzBaseServiceImpl<PurchaseOrz, PurchaseOrzVO, PurchaseOrz, PurchaseOrzMapper> implements PurchaseOrzService {


    @Override
    public List<CompanyVO> queryPurchaseOrzsTree() {
        List<CompanyVO> companyVOs = PceCompanyResponseUtils.getSysCompanyTree();
        companyVOs.forEach(c-> {
            c.setType("0");
            c.setLevel("0");
            combineTree(c);
        });
        return companyVOs;
    }

    @Override
    public Long ifHasParent(Long id) {
        PurchaseOrz purchaseOrz=this.getById(id);
        if(purchaseOrz.getPId()==null||purchaseOrz.getPId()==0){
            return 0L;
        }
        return purchaseOrz.getPId();
    }

    //组合两个树只取最上一级
    private void combineTree(CompanyVO c){
//        if(c.getChildren()!=null){
//            for(CompanyVO v:c.getChildren()){
//                combineTree(v);
//            }
//        }else{
        c.setChildren(getOrzByCompanyId(c.getId()));
//        }
    }

    //根据工厂找到组织列表
    private List<CompanyVO> getOrzByCompanyId(Long companyId){
        List<PurchaseOrz> purchaseOrzs= purchaseOrzMapper.selectList((new QueryWrapper<PurchaseOrz>()).eq("company_id",companyId).eq("deleted",0).and(qw -> qw.isNull("p_id").or().eq("p_id", 0)));
        if(CollUtil.isEmpty(purchaseOrzs)){
            return null;
        }
        List<CompanyVO> companyVOs=new ArrayList<>();
        purchaseOrzs.forEach(inventoryOrz->{
            int a=1;
            companyVOs.add(recursiveTree(inventoryOrz.getId(),a));
        });
        return companyVOs;
    }

    //递归子组织并转化成工厂
    private CompanyVO recursiveTree(Long id,Integer level) {
        PurchaseOrz purchaseOrz = purchaseOrzMapper.selectById(id);
        //转成company
        CompanyVO companyVO=new CompanyVO();
        companyVO.setPId(purchaseOrz.getPId());
        companyVO.setLevel(String.valueOf(level++));
        companyVO.setId(purchaseOrz.getId());
        companyVO.setShortName(purchaseOrz.getName());
        companyVO.setParentId(purchaseOrz.getPId());

        List<PurchaseOrz> childTreePurchaseOrzs = purchaseOrzMapper.selectList((new QueryWrapper<PurchaseOrz>()).eq("p_id",id).eq("deleted",0));

        //遍历子节点
        for (PurchaseOrz childPruchaseOrz : childTreePurchaseOrzs) {
            CompanyVO companyVO1 = recursiveTree(childPruchaseOrz.getId(),level); //递归
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
}

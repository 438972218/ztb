package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.generator.impl.PaidMaterialBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.xdcweb.biz.service.PaidMaterialService;
import com.xdcplus.xdcweb.biz.service.PaidVendorMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 竞价物品(PaidMaterial)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:41
 */
@Slf4j
@Service("paidMaterialService")
public class PaidMaterialServiceImpl extends PaidMaterialBaseServiceImpl<PaidMaterial, PaidMaterialVO, PaidMaterial, PaidMaterialMapper> implements PaidMaterialService {

    @Autowired
    private PaidVendorMaterialService paidVendorMaterialService;

    @Override
    public List<PaidMaterialVO> queryPaidMaterialsWithPricing(PaidMaterialFilterDTO paidMaterialFilterDTO) {
        List<PaidMaterialVO> paidMaterialVOS = queryPaidMaterialVOList(paidMaterialFilterDTO);
        if (CollUtil.isEmpty(paidMaterialVOS)) {
            return null;
        }
        for (PaidMaterialVO paidMaterialVO : paidMaterialVOS) {
            PaidVendorMaterialQuery paidVendorMaterialQuery = new PaidVendorMaterialQuery();
            paidVendorMaterialQuery.setMaterialId(paidMaterialVO.getId());
            List<PaidVendorMaterialVO> paidVendorMaterialVOS = paidVendorMaterialService.queryNewPaidVendorMaterialByMaterialId(paidVendorMaterialQuery);
            if (CollUtil.isNotEmpty(paidVendorMaterialVOS)) {
                Double costTotalPrice = 0.0;
                Double totalQuantity = 0.0;
                for (PaidVendorMaterialVO paidVendorMaterialVO : paidVendorMaterialVOS) {
                    if (paidVendorMaterialVO.getAllottedQuantity() != null) {
                        costTotalPrice += (paidVendorMaterialVO.getPrice() * Double.parseDouble(paidVendorMaterialVO.getAllottedQuantity()));
                        totalQuantity += Double.parseDouble(paidVendorMaterialVO.getAllottedQuantity());
                    }
                }
                if (costTotalPrice != 0.0) {
                    Double costPrice = costTotalPrice / totalQuantity;
                    paidMaterialVO.setCostPrice(costPrice);
                    paidMaterialVO.setCostTotalPrice(costTotalPrice);
                }
            }
        }

        return paidMaterialVOS;
    }

}

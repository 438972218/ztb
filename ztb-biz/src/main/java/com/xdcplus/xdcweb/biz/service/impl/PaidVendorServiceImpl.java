package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.generator.impl.PaidVendorBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidVendorMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorVO;
import com.xdcplus.xdcweb.biz.service.PaidMaterialService;
import com.xdcplus.xdcweb.biz.service.PaidVendorMaterialService;
import com.xdcplus.xdcweb.biz.service.PaidVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 竞价供应商(PaidVendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:45
 */
@Slf4j
@Service("paidVendorService")
public class PaidVendorServiceImpl extends PaidVendorBaseServiceImpl<PaidVendor, PaidVendorVO, PaidVendor, PaidVendorMapper> implements PaidVendorService {

    @Autowired
    private PaidVendorMaterialService paidVendorMaterialService;

    @Autowired
    private PaidMaterialService paidMaterialService;

    @Override
    public Boolean updatePaidVendorStatus(PaidVendorDTO paidVendorDTO) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("paid_sheet_id", paidVendorDTO.getPaidSheetId());
        updateWrapper.eq("vendor_code", paidVendorDTO.getVendorCode());
        PaidVendor paidVendor = new PaidVendor();
        paidVendor.setVendorStatus(paidVendorDTO.getVendorStatus());
        int result = paidVendorMapper.update(paidVendor, updateWrapper);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updatePaidVendorStatusByPricing(Long sheetId) {
        PaidMaterialFilterDTO paidMaterialFilterDTO = new PaidMaterialFilterDTO();
        paidMaterialFilterDTO.setPaidSheetId(sheetId);
        List<PaidMaterialVO> paidMaterialVOS = paidMaterialService.queryPaidMaterialVOList(paidMaterialFilterDTO);
        if (CollectionUtil.isEmpty(paidMaterialVOS)) {
            return true;
        }
        PaidVendorMaterialQuery paidVendorMaterialQuery = new PaidVendorMaterialQuery();
        paidVendorMaterialQuery.setMaterialId(paidMaterialVOS.get(0).getId());
        List<PaidVendorMaterialVO> paidVendorMaterialVOS = paidVendorMaterialService.queryNewPaidVendorMaterialByMaterialId(paidVendorMaterialQuery);
        if(CollectionUtil.isEmpty(paidVendorMaterialVOS)){
            return true;
        }
        for (PaidVendorMaterialVO paidVendorMaterialVO : paidVendorMaterialVOS) {
            if (paidVendorMaterialVO.getAllottedQuantity() != null) {
                PaidVendor paidVendor = new PaidVendor();
                paidVendor.setId(paidVendorMaterialVO.getPaidVendorId());
                paidVendor.setVendorStatus("已中签");
                updateById(paidVendor);
            }
        }

        return true;
    }

}

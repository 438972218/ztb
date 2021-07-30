package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorKpiDetailBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailVO;
import com.xdcplus.xdcweb.biz.service.VendorKpiDetailService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商绩效考核表明细(VendorKpiDetail)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:45
 */
@Slf4j
@Service("vendorKpiDetailService")
public class VendorKpiDetailServiceImpl extends VendorKpiDetailBaseServiceImpl<VendorKpiDetail, VendorKpiDetailVO, VendorKpiDetail, VendorKpiDetailMapper> implements VendorKpiDetailService {


    @Override
    public int deleteVendorKpiDetailByKpiId(Long id) {
        return vendorKpiDetailMapper.delete(new QueryWrapper<VendorKpiDetail>().eq("vendor_kpi_id",id));
    }
}

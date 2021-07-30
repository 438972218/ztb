package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorKpiUserBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiUser;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiUserVO;
import com.xdcplus.xdcweb.biz.service.VendorKpiUserService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商绩效人员表(VendorKpiUser)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:50
 */
@Slf4j
@Service("vendorKpiUserService")
public class VendorKpiUserServiceImpl extends VendorKpiUserBaseServiceImpl<VendorKpiUser, VendorKpiUserVO, VendorKpiUser, VendorKpiUserMapper> implements VendorKpiUserService {


    @Override
    public int deleteVendorKpiUserByKpiId(Long id) {
        return vendorKpiUserMapper.delete(new QueryWrapper<VendorKpiUser>().eq("vendor_kpi_id",id));
    }
}

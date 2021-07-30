package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorSiteInsUserBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsUser;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsUserVO;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsUserService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商现场考察人员表(VendorSiteInsUser)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:23
 */
@Slf4j
@Service("vendorSiteInsUserService")
public class VendorSiteInsUserServiceImpl extends VendorSiteInsUserBaseServiceImpl<VendorSiteInsUser, VendorSiteInsUserVO, VendorSiteInsUser, VendorSiteInsUserMapper> implements VendorSiteInsUserService {


    @Override
    public int deleteVendorSiteInsBySiteInsId(Long id) {
        return vendorSiteInsUserMapper.delete(new QueryWrapper<VendorSiteInsUser>().eq("vendor_site_ins_id", id));
    }
}

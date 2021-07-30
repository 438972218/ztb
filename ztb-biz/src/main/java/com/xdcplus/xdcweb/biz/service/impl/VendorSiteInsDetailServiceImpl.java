package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorSiteInsDetailBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailVO;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsDetailService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商现场考察表明细(VendorSiteInsDetail)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:15
 */
@Slf4j
@Service("vendorSiteInsDetailService")
public class VendorSiteInsDetailServiceImpl extends VendorSiteInsDetailBaseServiceImpl<VendorSiteInsDetail, VendorSiteInsDetailVO, VendorSiteInsDetail, VendorSiteInsDetailMapper> implements VendorSiteInsDetailService {


    @Override
    public int deleteVendorSiteInsBySiteInsId(Long id) {
        return vendorSiteInsDetailMapper.delete(new QueryWrapper<VendorSiteInsDetail>().eq("vendor_site_ins_id", id));
    }
}

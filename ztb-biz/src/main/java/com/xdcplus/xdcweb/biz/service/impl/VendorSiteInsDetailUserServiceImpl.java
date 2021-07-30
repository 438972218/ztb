package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.VendorSiteInsDetailUserBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsDetailUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailUserVO;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsDetailUserService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:20
 */
@Slf4j
@Service("vendorSiteInsDetailUserService")
public class VendorSiteInsDetailUserServiceImpl extends VendorSiteInsDetailUserBaseServiceImpl<VendorSiteInsDetailUser, VendorSiteInsDetailUserVO, VendorSiteInsDetailUser, VendorSiteInsDetailUserMapper> implements VendorSiteInsDetailUserService {


}

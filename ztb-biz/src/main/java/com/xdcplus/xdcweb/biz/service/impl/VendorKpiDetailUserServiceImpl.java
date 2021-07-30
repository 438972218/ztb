package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.VendorKpiDetailUserBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiDetailUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailUserVO;
import com.xdcplus.xdcweb.biz.service.VendorKpiDetailUserService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商绩效人员评价表(VendorKpiDetailUser)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:47
 */
@Slf4j
@Service("vendorKpiDetailUserService")
public class VendorKpiDetailUserServiceImpl extends VendorKpiDetailUserBaseServiceImpl<VendorKpiDetailUser, VendorKpiDetailUserVO, VendorKpiDetailUser, VendorKpiDetailUserMapper> implements VendorKpiDetailUserService {


}

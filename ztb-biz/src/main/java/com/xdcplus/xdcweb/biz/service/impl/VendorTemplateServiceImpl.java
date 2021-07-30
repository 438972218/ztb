package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.VendorTemplateBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorTemplateMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplate;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateVO;
import com.xdcplus.xdcweb.biz.service.VendorTemplateService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 模板表(VendorTemplate)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:29
 */
@Slf4j
@Service("vendorTemplateService")
public class VendorTemplateServiceImpl extends VendorTemplateBaseServiceImpl<VendorTemplate, VendorTemplateVO, VendorTemplate, VendorTemplateMapper> implements VendorTemplateService {


}

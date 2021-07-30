package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.VendorRequestCategoryBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorRequestCategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCategory;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCategoryVO;
import com.xdcplus.xdcweb.biz.service.VendorRequestCategoryService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商注册单品类(VendorRequestCategory)表服务实现类
 *
 * @author makejava
 * @since 2021-07-27 11:08:57
 */
@Slf4j
@Service("vendorRequestCategoryService")
public class VendorRequestCategoryServiceImpl extends VendorRequestCategoryBaseServiceImpl<VendorRequestCategory, VendorRequestCategoryVO, VendorRequestCategory, VendorRequestCategoryMapper> implements VendorRequestCategoryService {


}

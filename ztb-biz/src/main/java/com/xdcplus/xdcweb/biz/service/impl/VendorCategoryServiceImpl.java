package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.VendorCategoryBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorCategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCategory;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCategoryVO;
import com.xdcplus.xdcweb.biz.service.VendorCategoryService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商品类(VendorCategory)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:38
 */
@Slf4j
@Service("vendorCategoryService")
public class VendorCategoryServiceImpl extends VendorCategoryBaseServiceImpl<VendorCategory, VendorCategoryVO, VendorCategory, VendorCategoryMapper> implements VendorCategoryService {


}

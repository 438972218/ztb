package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankFilterDTO;
import com.xdcplus.xdcweb.biz.generator.VendorTemplateQbankBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplateQbank;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateQbankVO;


/**
 * 模板题库中间表(VendorTemplateQbank)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:30
 */
public interface VendorTemplateQbankService extends VendorTemplateQbankBaseService<VendorTemplateQbank, VendorTemplateQbankVO, VendorTemplateQbank> {

    PageVO<VendorTemplateQbankVO> queryVendorTemplateQbankReturnVO(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO);
}

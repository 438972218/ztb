package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorDTO;
import com.xdcplus.xdcweb.biz.generator.InquiryVendorBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorVO;


/**
 * 询价供应商(InquiryVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:14
 */
public interface InquiryVendorService extends InquiryVendorBaseService<InquiryVendor, InquiryVendorVO, InquiryVendor> {

    /**
     * 供应商修改询价供应商状态(InquiryVendor)
     *
     * @param inquiryVendorDTO 询价供应商(InquiryVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateInquiryVendorStatus(InquiryVendorDTO inquiryVendorDTO);

    /**
     * 供应商修改询价供应商状态(InquiryVendor)
     *
     * @param inquiryVendorDTO 询价供应商(InquiryVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean joinInquiryVendor(InquiryVendorDTO inquiryVendorDTO);
}

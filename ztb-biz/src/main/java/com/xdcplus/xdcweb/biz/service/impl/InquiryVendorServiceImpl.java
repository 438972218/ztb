package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquirySheetVO;
import com.xdcplus.xdcweb.biz.generator.impl.InquiryVendorBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryVendorMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorVO;
import com.xdcplus.xdcweb.biz.service.InquirySheetService;
import com.xdcplus.xdcweb.biz.service.InquiryVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 询价供应商(InquiryVendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:40:14
 */
@Slf4j
@Service("inquiryVendorService")
public class InquiryVendorServiceImpl extends InquiryVendorBaseServiceImpl<InquiryVendor, InquiryVendorVO, InquiryVendor, InquiryVendorMapper> implements InquiryVendorService {

    @Autowired
    private InquirySheetService inquirySheetService;

    @Override
    public Boolean updateInquiryVendorStatus(InquiryVendorDTO inquiryVendorDTO) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("inquiry_sheet_id", inquiryVendorDTO.getInquirySheetId());
        updateWrapper.eq("vendor_code", inquiryVendorDTO.getVendorCode());
        InquiryVendor inquiryVendor = new InquiryVendor();
        inquiryVendor.setVendorStatus(inquiryVendorDTO.getVendorStatus());
        int result = inquiryVendorMapper.update(inquiryVendor, updateWrapper);
        if (result != 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean joinInquiryVendor(InquiryVendorDTO inquiryVendorDTO) {
        Boolean ifJoin = inquiryVendorDTO.getIfJoin();
        Long inquirySheetId = inquiryVendorDTO.getInquirySheetId();
        InquirySheetVO inquirySheetVO = inquirySheetService.queryInquirySheetById(inquirySheetId);
        if (inquirySheetVO.getInquiryMode().equals("公开")) {
            if (ifJoin) {
                inquiryVendorDTO.setVendorStatus("已参与");
                saveInquiryVendor(inquiryVendorDTO);
            } else {
                inquiryVendorDTO.setVendorStatus("已拒绝");
                saveInquiryVendor(inquiryVendorDTO);
            }
        } else {

        }

        return null;
    }

}

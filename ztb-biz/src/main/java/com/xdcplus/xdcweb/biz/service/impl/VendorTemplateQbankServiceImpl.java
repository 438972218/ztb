package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankFilterDTO;
import com.xdcplus.xdcweb.biz.generator.impl.VendorTemplateQbankBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorTemplateQbankMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplateQbank;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateQbankVO;
import com.xdcplus.xdcweb.biz.service.VendorQuestionBankService;
import com.xdcplus.xdcweb.biz.service.VendorTemplateQbankService;
import com.xdcplus.xdcweb.biz.service.VendorTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板题库中间表(VendorTemplateQbank)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:31
 */
@Slf4j
@Service("vendorTemplateQbankService")
public class VendorTemplateQbankServiceImpl extends VendorTemplateQbankBaseServiceImpl<VendorTemplateQbank, VendorTemplateQbankVO, VendorTemplateQbank, VendorTemplateQbankMapper> implements VendorTemplateQbankService {
    @Autowired
    private VendorTemplateService vendorTemplateService;
    @Autowired
    private VendorQuestionBankService vendorQuestionBankService;

    @Override
    public PageVO<VendorTemplateQbankVO> queryVendorTemplateQbankReturnVO(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO) {
        PageVO<VendorTemplateQbankVO> pageVO = new PageVO<>();

        if (vendorTemplateQbankFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorTemplateQbankFilterDTO.getCurrentPage(), vendorTemplateQbankFilterDTO.getPageSize(),
                    vendorTemplateQbankFilterDTO.getOrderType(), vendorTemplateQbankFilterDTO.getOrderField());
        }

        List<VendorTemplateQbankVO> vendorTemplateQbankList = queryVendorTemplateQbankVOList(vendorTemplateQbankFilterDTO);

        if(CollUtil.isEmpty(vendorTemplateQbankList)){
            return null;
        }

        List<VendorTemplateQbankVO> vendorTemplateQbankVOS= new ArrayList<>();

        vendorTemplateQbankList.forEach(vendorTemplateQbank -> {
            VendorTemplateQbankVO vendorTemplateQbankVO=new VendorTemplateQbankVO();
            vendorTemplateQbankVO.setId(vendorTemplateQbank.getId());
            vendorTemplateQbankVO.setTemplateId(vendorTemplateQbank.getTemplateId());
            vendorTemplateQbankVO.setQuestionBankId(vendorTemplateQbank.getQuestionBankId());
            vendorTemplateQbankVO.setVendorTemplate(vendorTemplateService.getById(vendorTemplateQbank.getTemplateId()));
            vendorTemplateQbankVO.setVendorQuestionBank(vendorQuestionBankService.getById(vendorTemplateQbank.getQuestionBankId()));
            vendorTemplateQbankVOS.add(vendorTemplateQbankVO);
        });

        PageInfo<VendorTemplateQbankVO> pageInfo = new PageInfo<>(vendorTemplateQbankVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, vendorTemplateQbankVOS);

        return pageVO;
    }
}

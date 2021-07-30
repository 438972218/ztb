package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionBankFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuestionBankQuery;
import com.xdcplus.xdcweb.biz.generator.impl.VendorQuestionBankBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQuestionBankMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionBank;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionBankVO;
import com.xdcplus.xdcweb.biz.service.VendorQuestionBankService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板题库表(VendorQuestionBank)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:32:02
 */
@Slf4j
@Service("vendorQuestionBankService")
public class VendorQuestionBankServiceImpl extends VendorQuestionBankBaseServiceImpl<VendorQuestionBank, VendorQuestionBankVO, VendorQuestionBank, VendorQuestionBankMapper> implements VendorQuestionBankService {


    @Override
    public Map<String, List<VendorQuestionBankVO>> queryVendorQuestionBankVOByTemplate(VendorQuestionBankFilterDTO vendorQuestionBankFilterDTO) {
        VendorQuestionBankQuery vendorQuestionBankQuery = BeanUtil.copyProperties(vendorQuestionBankFilterDTO, VendorQuestionBankQuery.class);

        List<VendorQuestionBank> vendorQuestionBanks = vendorQuestionBankMapper.queryVendorQuestionBankByTemplate(vendorQuestionBankQuery);
        if (CollectionUtil.isEmpty(vendorQuestionBanks)) {
            return null;
        }
        Map<String, List<VendorQuestionBankVO>> map = new HashMap<>();
        vendorQuestionBanks.forEach(vendorQuestionBank -> {
            if (map.get(vendorQuestionBank.getType()) == null) {
                List<VendorQuestionBankVO> vendorQuestionBankVOS = new ArrayList<>();
                vendorQuestionBankVOS.add(BeanUtil.copyProperties(vendorQuestionBank, VendorQuestionBankVO.class));
                map.put(vendorQuestionBank.getType(), vendorQuestionBankVOS);
            } else {
                map.get(vendorQuestionBank.getType()).add(BeanUtil.copyProperties(vendorQuestionBank, VendorQuestionBankVO.class));
            }
        });
        return map;
    }
}

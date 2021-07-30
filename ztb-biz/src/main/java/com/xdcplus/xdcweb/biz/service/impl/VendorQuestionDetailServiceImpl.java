package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.VendorQuestionDetailBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQuestionDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionDetailVO;
import com.xdcplus.xdcweb.biz.service.VendorQuestionDetailService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商电子调查表明细(VendorQuestionDetail)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:32:05
 */
@Slf4j
@Service("vendorQuestionDetailService")
public class VendorQuestionDetailServiceImpl extends VendorQuestionDetailBaseServiceImpl<VendorQuestionDetail, VendorQuestionDetailVO, VendorQuestionDetail, VendorQuestionDetailMapper> implements VendorQuestionDetailService {


    @Override
    public int deleteVendorQuestionDetailByQuestionId(Long id) {
        return vendorQuestionDetailMapper.delete(new QueryWrapper<VendorQuestionDetail>().eq("vendor_question_id",id));
    }
}

package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionBankFilterDTO;
import com.xdcplus.xdcweb.biz.generator.VendorQuestionBankBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionBank;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionBankVO;

import java.util.List;
import java.util.Map;


/**
 * 模板题库表(VendorQuestionBank)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:32:02
 */
public interface VendorQuestionBankService extends VendorQuestionBankBaseService<VendorQuestionBank, VendorQuestionBankVO, VendorQuestionBank> {

    /**
     * 根据模板id查询模板题库表(Template)(VendorQuestionBank)
     *
     * @param vendorQuestionBankFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <VendorQuestionBankVO>} 状态信息
     */
    Map<String, List<VendorQuestionBankVO>> queryVendorQuestionBankVOByTemplate(VendorQuestionBankFilterDTO vendorQuestionBankFilterDTO);


}

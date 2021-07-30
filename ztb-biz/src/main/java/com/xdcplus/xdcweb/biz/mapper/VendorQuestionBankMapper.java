package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionBank;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuestionBankQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板题库表(VendorQuestionBank)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-15 10:53:31
 */
public interface VendorQuestionBankMapper extends IBaseMapper<VendorQuestionBank> {

    /**
     * 查询模板题库表(VendorQuestionBank)
     *
     * @param vendorQuestionBankQuery 模板题库表(VendorQuestionBank)查询
     * @return {@link List<VendorQuestionBank>}
     */
    List<VendorQuestionBank> queryVendorQuestionBank(VendorQuestionBankQuery vendorQuestionBankQuery);

    List<VendorQuestionBank> queryVendorQuestionBankByTemplate(VendorQuestionBankQuery vendorQuestionBankQuery);

}

package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestion;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuestionQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商电子调查表(VendorQuestion)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-15 10:53:34
 */
public interface VendorQuestionMapper extends IBaseMapper<VendorQuestion> {

    /**
     * 查询供应商电子调查表(VendorQuestion)
     *
     * @param vendorQuestionQuery 供应商电子调查表(VendorQuestion)查询
     * @return {@link List<VendorQuestion>}
     */
    List<VendorQuestion> queryVendorQuestion(VendorQuestionQuery vendorQuestionQuery);

}

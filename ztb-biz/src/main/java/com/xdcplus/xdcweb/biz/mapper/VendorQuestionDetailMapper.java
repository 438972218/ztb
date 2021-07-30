package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionDetail;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuestionDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商电子调查表明细(VendorQuestionDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-14 14:51:21
 */
public interface VendorQuestionDetailMapper extends IBaseMapper<VendorQuestionDetail> {

    /**
     * 查询供应商电子调查表明细(VendorQuestionDetail)
     *
     * @param vendorQuestionDetailQuery 供应商电子调查表明细(VendorQuestionDetail)查询
     * @return {@link List<VendorQuestionDetail>}
     */
    List<VendorQuestionDetail> queryVendorQuestionDetail(VendorQuestionDetailQuery vendorQuestionDetailQuery);

}

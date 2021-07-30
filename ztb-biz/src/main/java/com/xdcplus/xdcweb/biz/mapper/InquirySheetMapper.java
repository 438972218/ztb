package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquirySheet;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquirySheetQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 询价单(InquirySheet)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:07:44
 */
public interface InquirySheetMapper extends IBaseMapper<InquirySheet> {

    /**
     * 查询询价单(InquirySheet)
     *
     * @param inquirySheetQuery 询价单(InquirySheet)查询
     * @return {@link List<InquirySheet>}
     */
    List<InquirySheet> queryInquirySheet(InquirySheetQuery inquirySheetQuery);

}

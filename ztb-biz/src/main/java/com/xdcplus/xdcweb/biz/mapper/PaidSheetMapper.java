package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidSheet;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidSheetQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价单(PaidSheet)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-02 11:13:28
 */
public interface PaidSheetMapper extends IBaseMapper<PaidSheet> {

    /**
     * 查询竞价单(PaidSheet)
     *
     * @param paidSheetQuery 竞价单(PaidSheet)查询
     * @return {@link List<PaidSheet>}
     */
    List<PaidSheet> queryPaidSheet(PaidSheetQuery paidSheetQuery);

}

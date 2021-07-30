package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSheet;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidSheetQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * bid招标单(BidSheet)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:03
 */
public interface BidSheetMapper extends IBaseMapper<BidSheet> {

    /**
     * 查询bid招标单(BidSheet)
     *
     * @param bidSheetQuery bid招标单(BidSheet)查询
     * @return {@link List<BidSheet>}
     */
    List<BidSheet> queryBidSheet(BidSheetQuery bidSheetQuery);

}

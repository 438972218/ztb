package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidPreTrialItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预审项(BidPreTrialItem)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:02
 */
public interface BidPreTrialItemMapper extends IBaseMapper<BidPreTrialItem> {

    /**
     * 查询预审项(BidPreTrialItem)
     *
     * @param bidPreTrialItemQuery 预审项(BidPreTrialItem)查询
     * @return {@link List<BidPreTrialItem>}
     */
    List<BidPreTrialItem> queryBidPreTrialItem(BidPreTrialItemQuery bidPreTrialItemQuery);

}

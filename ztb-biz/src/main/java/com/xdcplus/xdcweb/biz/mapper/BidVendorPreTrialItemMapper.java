package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorPreTrialItem;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorPreTrialItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标投标方预审项(BidVendorPreTrialItem)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:10
 */
public interface BidVendorPreTrialItemMapper extends IBaseMapper<BidVendorPreTrialItem> {

    /**
     * 查询招标投标方预审项(BidVendorPreTrialItem)
     *
     * @param bidVendorPreTrialItemQuery 招标投标方预审项(BidVendorPreTrialItem)查询
     * @return {@link List<BidVendorPreTrialItem>}
     */
    List<BidVendorPreTrialItem> queryBidVendorPreTrialItem(BidVendorPreTrialItemQuery bidVendorPreTrialItemQuery);

}

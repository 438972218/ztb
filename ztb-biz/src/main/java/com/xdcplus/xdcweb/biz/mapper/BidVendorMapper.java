package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标投标方(BidVendor)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-09 10:01:09
 */
public interface BidVendorMapper extends IBaseMapper<BidVendor> {

    /**
     * 查询招标投标方(BidVendor)
     *
     * @param bidVendorQuery 招标投标方(BidVendor)查询
     * @return {@link List<BidVendor>}
     */
    List<BidVendor> queryBidVendor(BidVendorQuery bidVendorQuery);

}

package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.xdcweb.biz.generator.BidVendorBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorVO;

import java.util.List;


/**
 * 招标投标方(BidVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:08
 */
public interface BidVendorService extends BidVendorBaseService<BidVendor, BidVendorVO, BidVendor> {

    /**
     * 查询招标投标方(组长)(BidVendor)
     *
     * @param bidVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <BidVendorVO>} 状态信息
     */
    List<BidVendorVO> queryBidVendorWithTotalScore(BidVendorFilterDTO bidVendorFilterDTO);

    /**
     * 供应商修改招标供应商状态(BidVendor)
     *
     * @param bidVendorDTO 招标供应商(BidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidVendorStatus(BidVendorDTO bidVendorDTO);

    /**
     * 供应商修改供应商状态(核价)(BidVendor)
     *
     * @param sheetId
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidVendorStatusByPricing(Long sheetId);

    /**
     * 修改招标投标方(BidVendor)
     *
     * @param bidVendorDTO 招标投标方(BidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidVendorTotalPrice(BidVendorDTO bidVendorDTO);

}

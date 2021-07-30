package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorDTO;
import com.xdcplus.xdcweb.biz.generator.PaidVendorBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorVO;


/**
 * 竞价供应商(PaidVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:45
 */
public interface PaidVendorService extends PaidVendorBaseService<PaidVendor, PaidVendorVO, PaidVendor> {
    /**
     * 供应商修改供应商状态(PaidVendor)
     *
     * @param paidVendorDTO 招标供应商(PaidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidVendorStatus(PaidVendorDTO paidVendorDTO);

    /**
     * 供应商修改供应商状态(核价)(PaidVendor)
     *
     * @param sheetId
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidVendorStatusByPricing(Long sheetId);
}

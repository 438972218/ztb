package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.generator.PurchaseOrzBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzVO;

import java.util.List;


/**
 * 采购组织(PurchaseOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:40
 */
public interface PurchaseOrzService extends PurchaseOrzBaseService<PurchaseOrz, PurchaseOrzVO, PurchaseOrz> {

    /**
     * 查询公司采购组织树状(PurchaseOrz)
     *
     * @return {@link List < CompanyVO >} 状态信息
     */
    List<CompanyVO> queryPurchaseOrzsTree();

    /**
     * 是否有父级采购组织
     *
     * @return {@link Long}0,nll表示没有父级,其它表示有
     */
    Long ifHasParent(Long id);

}

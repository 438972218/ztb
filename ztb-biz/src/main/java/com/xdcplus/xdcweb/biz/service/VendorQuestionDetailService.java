package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.VendorQuestionDetailBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionDetailVO;


/**
 * 供应商电子调查表明细(VendorQuestionDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:32:05
 */
public interface VendorQuestionDetailService extends VendorQuestionDetailBaseService<VendorQuestionDetail, VendorQuestionDetailVO, VendorQuestionDetail> {

    /**
     * 删除电子调查表包装细节
     *
     * @param id
     * @return {@link int}
     */
    int deleteVendorQuestionDetailByQuestionId(Long id);

}

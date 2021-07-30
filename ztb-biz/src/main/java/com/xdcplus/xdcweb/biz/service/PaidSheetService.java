package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidSheetDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidSheetFilterDTO;
import com.xdcplus.xdcweb.biz.generator.PaidSheetBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidSheet;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidSheetVO;


/**
 * 竞价单(PaidSheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:43
 */
public interface PaidSheetService extends PaidSheetBaseService<PaidSheet, PaidSheetVO, PaidSheet> {
    /**
     * 添加竞价单(PaidSheet)返回VO
     *
     * @param paidSheetDTO 竞价单(PaidSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    PaidSheetVO savePaidSheetReturnVO(PaidSheetDTO paidSheetDTO);

    /**
     * 查询一个并带出附表中数据
     *
     * @param id 竞价单(PaidSheet)主键
     * @return {@link PaidSheetVO} 竞价单(PaidSheet)信息
     */
    PaidSheetVO showPaidSheetById(Long id);

    /**
     * 查询竞价单(供应商)(PaidSheet)
     *
     * @param paidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO < PaidSheetVO >} 状态信息
     */
    PageVO<PaidSheetVO> queryPaidSheetByVendor(PaidSheetFilterDTO paidSheetFilterDTO);

    /**
     * 查询竞价单(request)(PaidSheet)
     *
     * @param paidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO< PaidSheetVO >} 状态信息
     */
    PageVO<PaidSheetVO> queryPaidSheetWithRequest(PaidSheetFilterDTO paidSheetFilterDTO);

}

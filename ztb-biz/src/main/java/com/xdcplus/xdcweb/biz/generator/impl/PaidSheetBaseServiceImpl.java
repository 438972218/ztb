package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidSheetMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidSheet;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidSheetDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidSheetFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidSheetQuery;
import com.xdcplus.xdcweb.biz.generator.PaidSheetBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.utils.BeanUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 竞价单(PaidSheet)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:23
 */
public class PaidSheetBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PaidSheet, PaidSheetVO, PaidSheet, PaidSheetMapper> implements PaidSheetBaseService<S, T, E> {

    @Autowired
    protected PaidSheetMapper paidSheetMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePaidSheet(PaidSheetDTO paidSheetDTO) {

        PaidSheet paidSheet = paidSheetMapper.selectById(paidSheetDTO.getId());
        if (ObjectUtil.isNotNull(paidSheet)) {
            log.error("savePaidSheet() The PaidSheet already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        paidSheet = new PaidSheet();
        BeanUtil.copyProperties(paidSheetDTO, paidSheet);
        paidSheet.setCreatedTime(DateUtil.current());
        paidSheet.setDeleted(0);

        return this.save(paidSheet);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePaidSheet(PaidSheetDTO paidSheetDTO) {

        PaidSheet paidSheet = this.getById(paidSheetDTO.getId());
        if (ObjectUtil.isNull(paidSheet)) {
            log.error("updatePaidSheet() The PaidSheet does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(paidSheetDTO, paidSheet);
        paidSheet.setUpdatedUser(paidSheetDTO.getUpdatedUser());
        paidSheet.setUpdatedTime(DateUtil.current());

        return this.updateById(paidSheet);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PaidSheet> paidSheetList) {

        paidSheetList.forEach(paidSheet -> {
            PaidSheet paidSheetParam = new PaidSheet();
            paidSheetParam.setId(paidSheet.getId());
            if (ObjectUtil.isNotNull(paidSheet.getId())) {
                paidSheet.setId(paidSheet.getId());
                paidSheet.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PaidSheet> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PaidSheet::getId, paidSheet.getId());
                update(paidSheet, lambdaUpdate);
            } else {
                paidSheet.setCreatedTime(DateUtil.current());
                paidSheet.setDeleted(0);
                save(paidSheet);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PaidSheetDTO> paidSheetDTOList) {

        List<PaidSheet> paidSheetList = BeanUtils.copyProperties(paidSheetDTOList, PaidSheet.class);
        return saveOrUpdateBatch(paidSheetList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePaidSheetLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PaidSheet paidSheet = this.getById(id);

        if (ObjectUtil.isNull(paidSheet)) {
            log.error("deletePaidSheet() The PaidSheet does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        paidSheet.setDeleted(1);

        return this.updateById(paidSheet);
    }

    private List<PaidSheet> queryPaidSheetList(PaidSheetFilterDTO paidSheetFilterDTO) {
        if (ObjectUtil.isNull(paidSheetFilterDTO)) {
            paidSheetFilterDTO = new PaidSheetFilterDTO();
        }
        paidSheetFilterDTO.setDeleted(0);
        PaidSheetQuery paidSheetQuery = BeanUtil.copyProperties(paidSheetFilterDTO, PaidSheetQuery.class);

        return paidSheetMapper.queryPaidSheet(paidSheetQuery);
    }

    @Override
    public List<PaidSheetVO> queryPaidSheetVOList(PaidSheetFilterDTO paidSheetFilterDTO) {
        if (ObjectUtil.isNull(paidSheetFilterDTO)) {
            paidSheetFilterDTO = new PaidSheetFilterDTO();
        }
        paidSheetFilterDTO.setDeleted(0);
        return this.objectConversion(queryPaidSheetList(paidSheetFilterDTO));
    }

    @Override
    public PageVO<PaidSheetVO> queryPaidSheet(PaidSheetFilterDTO paidSheetFilterDTO) {
        if (ObjectUtil.isNull(paidSheetFilterDTO)) {
            paidSheetFilterDTO = new PaidSheetFilterDTO();
        }
        paidSheetFilterDTO.setDeleted(0);
        PageVO<PaidSheetVO> pageVO = new PageVO<>();

        if (paidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidSheetFilterDTO.getCurrentPage(), paidSheetFilterDTO.getPageSize(),
                    paidSheetFilterDTO.getOrderType(), paidSheetFilterDTO.getOrderField());
        }

        List<PaidSheet> paidSheetList = queryPaidSheetList(paidSheetFilterDTO);

        PageInfo<PaidSheet> pageInfo = new PageInfo<>(paidSheetList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(paidSheetList));

        return pageVO;
    }

    @Override
    public PaidSheetVO queryPaidSheetById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

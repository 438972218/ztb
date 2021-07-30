package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquirySheetMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquirySheet;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquirySheetDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquirySheetFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquirySheetVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquirySheetQuery;
import com.xdcplus.xdcweb.biz.generator.InquirySheetBaseService;
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
 * 询价单(InquirySheet)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:09
 */
public class InquirySheetBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<InquirySheet, InquirySheetVO, InquirySheet, InquirySheetMapper> implements InquirySheetBaseService<S, T, E> {

    @Autowired
    protected InquirySheetMapper inquirySheetMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveInquirySheet(InquirySheetDTO inquirySheetDTO) {

        InquirySheet inquirySheet = inquirySheetMapper.selectById(inquirySheetDTO.getId());
        if (ObjectUtil.isNotNull(inquirySheet)) {
            log.error("saveInquirySheet() The InquirySheet already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inquirySheet = new InquirySheet();
        BeanUtil.copyProperties(inquirySheetDTO, inquirySheet);
        inquirySheet.setCreatedTime(DateUtil.current());
        inquirySheet.setDeleted(0);

        return this.save(inquirySheet);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateInquirySheet(InquirySheetDTO inquirySheetDTO) {

        InquirySheet inquirySheet = this.getById(inquirySheetDTO.getId());
        if (ObjectUtil.isNull(inquirySheet)) {
            log.error("updateInquirySheet() The InquirySheet does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(inquirySheetDTO, inquirySheet);
        inquirySheet.setUpdatedUser(inquirySheetDTO.getUpdatedUser());
        inquirySheet.setUpdatedTime(DateUtil.current());

        return this.updateById(inquirySheet);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<InquirySheet> inquirySheetList) {

        inquirySheetList.forEach(inquirySheet -> {
            InquirySheet inquirySheetParam = new InquirySheet();
            inquirySheetParam.setId(inquirySheet.getId());
            if (ObjectUtil.isNotNull(inquirySheet.getId())) {
                inquirySheet.setId(inquirySheet.getId());
                inquirySheet.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<InquirySheet> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(InquirySheet::getId, inquirySheet.getId());
                update(inquirySheet, lambdaUpdate);
            } else {
                inquirySheet.setCreatedTime(DateUtil.current());
                inquirySheet.setDeleted(0);
                save(inquirySheet);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<InquirySheetDTO> inquirySheetDTOList) {

        List<InquirySheet> inquirySheetList = BeanUtils.copyProperties(inquirySheetDTOList, InquirySheet.class);
        return saveOrUpdateBatch(inquirySheetList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteInquirySheetLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        InquirySheet inquirySheet = this.getById(id);

        if (ObjectUtil.isNull(inquirySheet)) {
            log.error("deleteInquirySheet() The InquirySheet does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        inquirySheet.setDeleted(1);

        return this.updateById(inquirySheet);
    }

    private List<InquirySheet> queryInquirySheetList(InquirySheetFilterDTO inquirySheetFilterDTO) {
        if (ObjectUtil.isNull(inquirySheetFilterDTO)) {
            inquirySheetFilterDTO = new InquirySheetFilterDTO();
        }
        inquirySheetFilterDTO.setDeleted(0);
        InquirySheetQuery inquirySheetQuery = BeanUtil.copyProperties(inquirySheetFilterDTO, InquirySheetQuery.class);

        return inquirySheetMapper.queryInquirySheet(inquirySheetQuery);
    }

    @Override
    public List<InquirySheetVO> queryInquirySheetVOList(InquirySheetFilterDTO inquirySheetFilterDTO) {
        if (ObjectUtil.isNull(inquirySheetFilterDTO)) {
            inquirySheetFilterDTO = new InquirySheetFilterDTO();
        }
        inquirySheetFilterDTO.setDeleted(0);
        return this.objectConversion(queryInquirySheetList(inquirySheetFilterDTO));
    }

    @Override
    public PageVO<InquirySheetVO> queryInquirySheet(InquirySheetFilterDTO inquirySheetFilterDTO) {
        if (ObjectUtil.isNull(inquirySheetFilterDTO)) {
            inquirySheetFilterDTO = new InquirySheetFilterDTO();
        }
        inquirySheetFilterDTO.setDeleted(0);
        PageVO<InquirySheetVO> pageVO = new PageVO<>();

        if (inquirySheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquirySheetFilterDTO.getCurrentPage(), inquirySheetFilterDTO.getPageSize(),
                    inquirySheetFilterDTO.getOrderType(), inquirySheetFilterDTO.getOrderField());
        }

        List<InquirySheet> inquirySheetList = queryInquirySheetList(inquirySheetFilterDTO);

        PageInfo<InquirySheet> pageInfo = new PageInfo<>(inquirySheetList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inquirySheetList));

        return pageVO;
    }

    @Override
    public InquirySheetVO queryInquirySheetById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

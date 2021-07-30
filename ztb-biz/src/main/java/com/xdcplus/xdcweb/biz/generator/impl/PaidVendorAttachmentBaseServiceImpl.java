package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidVendorAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorAttachmentQuery;
import com.xdcplus.xdcweb.biz.generator.PaidVendorAttachmentBaseService;
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
 * 竞价供应商附件(PaidVendorAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:26
 */
public class PaidVendorAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PaidVendorAttachment, PaidVendorAttachmentVO, PaidVendorAttachment, PaidVendorAttachmentMapper> implements PaidVendorAttachmentBaseService<S, T, E> {

    @Autowired
    protected PaidVendorAttachmentMapper paidVendorAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePaidVendorAttachment(PaidVendorAttachmentDTO paidVendorAttachmentDTO) {

        PaidVendorAttachment paidVendorAttachment = paidVendorAttachmentMapper.selectById(paidVendorAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(paidVendorAttachment)) {
            log.error("savePaidVendorAttachment() The PaidVendorAttachment already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        paidVendorAttachment = new PaidVendorAttachment();
        BeanUtil.copyProperties(paidVendorAttachmentDTO, paidVendorAttachment);
        paidVendorAttachment.setCreatedTime(DateUtil.current());
        paidVendorAttachment.setDeleted(0);

        return this.save(paidVendorAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePaidVendorAttachment(PaidVendorAttachmentDTO paidVendorAttachmentDTO) {

        PaidVendorAttachment paidVendorAttachment = this.getById(paidVendorAttachmentDTO.getId());
        if (ObjectUtil.isNull(paidVendorAttachment)) {
            log.error("updatePaidVendorAttachment() The PaidVendorAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(paidVendorAttachmentDTO, paidVendorAttachment);
        paidVendorAttachment.setUpdatedUser(paidVendorAttachmentDTO.getUpdatedUser());
        paidVendorAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(paidVendorAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PaidVendorAttachment> paidVendorAttachmentList) {

        paidVendorAttachmentList.forEach(paidVendorAttachment -> {
            PaidVendorAttachment paidVendorAttachmentParam = new PaidVendorAttachment();
            paidVendorAttachmentParam.setId(paidVendorAttachment.getId());
            if (ObjectUtil.isNotNull(paidVendorAttachment.getId())) {
                paidVendorAttachment.setId(paidVendorAttachment.getId());
                paidVendorAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PaidVendorAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PaidVendorAttachment::getId, paidVendorAttachment.getId());
                update(paidVendorAttachment, lambdaUpdate);
            } else {
                paidVendorAttachment.setCreatedTime(DateUtil.current());
                paidVendorAttachment.setDeleted(0);
                save(paidVendorAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PaidVendorAttachmentDTO> paidVendorAttachmentDTOList) {

        List<PaidVendorAttachment> paidVendorAttachmentList = BeanUtils.copyProperties(paidVendorAttachmentDTOList, PaidVendorAttachment.class);
        return saveOrUpdateBatch(paidVendorAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePaidVendorAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PaidVendorAttachment paidVendorAttachment = this.getById(id);

        if (ObjectUtil.isNull(paidVendorAttachment)) {
            log.error("deletePaidVendorAttachment() The PaidVendorAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        paidVendorAttachment.setDeleted(1);

        return this.updateById(paidVendorAttachment);
    }

    private List<PaidVendorAttachment> queryPaidVendorAttachmentList(PaidVendorAttachmentFilterDTO paidVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(paidVendorAttachmentFilterDTO)) {
            paidVendorAttachmentFilterDTO = new PaidVendorAttachmentFilterDTO();
        }
        paidVendorAttachmentFilterDTO.setDeleted(0);
        PaidVendorAttachmentQuery paidVendorAttachmentQuery = BeanUtil.copyProperties(paidVendorAttachmentFilterDTO, PaidVendorAttachmentQuery.class);

        return paidVendorAttachmentMapper.queryPaidVendorAttachment(paidVendorAttachmentQuery);
    }

    @Override
    public List<PaidVendorAttachmentVO> queryPaidVendorAttachmentVOList(PaidVendorAttachmentFilterDTO paidVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(paidVendorAttachmentFilterDTO)) {
            paidVendorAttachmentFilterDTO = new PaidVendorAttachmentFilterDTO();
        }
        paidVendorAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryPaidVendorAttachmentList(paidVendorAttachmentFilterDTO));
    }

    @Override
    public PageVO<PaidVendorAttachmentVO> queryPaidVendorAttachment(PaidVendorAttachmentFilterDTO paidVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(paidVendorAttachmentFilterDTO)) {
            paidVendorAttachmentFilterDTO = new PaidVendorAttachmentFilterDTO();
        }
        paidVendorAttachmentFilterDTO.setDeleted(0);
        PageVO<PaidVendorAttachmentVO> pageVO = new PageVO<>();

        if (paidVendorAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidVendorAttachmentFilterDTO.getCurrentPage(), paidVendorAttachmentFilterDTO.getPageSize(),
                    paidVendorAttachmentFilterDTO.getOrderType(), paidVendorAttachmentFilterDTO.getOrderField());
        }

        List<PaidVendorAttachment> paidVendorAttachmentList = queryPaidVendorAttachmentList(paidVendorAttachmentFilterDTO);

        PageInfo<PaidVendorAttachment> pageInfo = new PageInfo<>(paidVendorAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(paidVendorAttachmentList));

        return pageVO;
    }

    @Override
    public PaidVendorAttachmentVO queryPaidVendorAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

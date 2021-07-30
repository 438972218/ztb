package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryAttachmentVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryAttachmentQuery;
import com.xdcplus.xdcweb.biz.generator.InquiryAttachmentBaseService;
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
 * 询价单附件(InquiryAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:07
 */
public class InquiryAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<InquiryAttachment, InquiryAttachmentVO, InquiryAttachment, InquiryAttachmentMapper> implements InquiryAttachmentBaseService<S, T, E> {

    @Autowired
    protected InquiryAttachmentMapper inquiryAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveInquiryAttachment(InquiryAttachmentDTO inquiryAttachmentDTO) {

        InquiryAttachment inquiryAttachment = inquiryAttachmentMapper.selectById(inquiryAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(inquiryAttachment)) {
            log.error("saveInquiryAttachment() The InquiryAttachment already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inquiryAttachment = new InquiryAttachment();
        BeanUtil.copyProperties(inquiryAttachmentDTO, inquiryAttachment);
        inquiryAttachment.setCreatedTime(DateUtil.current());
        inquiryAttachment.setDeleted(0);

        return this.save(inquiryAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateInquiryAttachment(InquiryAttachmentDTO inquiryAttachmentDTO) {

        InquiryAttachment inquiryAttachment = this.getById(inquiryAttachmentDTO.getId());
        if (ObjectUtil.isNull(inquiryAttachment)) {
            log.error("updateInquiryAttachment() The InquiryAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(inquiryAttachmentDTO, inquiryAttachment);
        inquiryAttachment.setUpdatedUser(inquiryAttachmentDTO.getUpdatedUser());
        inquiryAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(inquiryAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<InquiryAttachment> inquiryAttachmentList) {

        inquiryAttachmentList.forEach(inquiryAttachment -> {
            InquiryAttachment inquiryAttachmentParam = new InquiryAttachment();
            inquiryAttachmentParam.setId(inquiryAttachment.getId());
            if (ObjectUtil.isNotNull(inquiryAttachment.getId())) {
                inquiryAttachment.setId(inquiryAttachment.getId());
                inquiryAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<InquiryAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(InquiryAttachment::getId, inquiryAttachment.getId());
                update(inquiryAttachment, lambdaUpdate);
            } else {
                inquiryAttachment.setCreatedTime(DateUtil.current());
                inquiryAttachment.setDeleted(0);
                save(inquiryAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<InquiryAttachmentDTO> inquiryAttachmentDTOList) {

        List<InquiryAttachment> inquiryAttachmentList = BeanUtils.copyProperties(inquiryAttachmentDTOList, InquiryAttachment.class);
        return saveOrUpdateBatch(inquiryAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteInquiryAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        InquiryAttachment inquiryAttachment = this.getById(id);

        if (ObjectUtil.isNull(inquiryAttachment)) {
            log.error("deleteInquiryAttachment() The InquiryAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        inquiryAttachment.setDeleted(1);

        return this.updateById(inquiryAttachment);
    }

    private List<InquiryAttachment> queryInquiryAttachmentList(InquiryAttachmentFilterDTO inquiryAttachmentFilterDTO) {
        if (ObjectUtil.isNull(inquiryAttachmentFilterDTO)) {
            inquiryAttachmentFilterDTO = new InquiryAttachmentFilterDTO();
        }
        inquiryAttachmentFilterDTO.setDeleted(0);
        InquiryAttachmentQuery inquiryAttachmentQuery = BeanUtil.copyProperties(inquiryAttachmentFilterDTO, InquiryAttachmentQuery.class);

        return inquiryAttachmentMapper.queryInquiryAttachment(inquiryAttachmentQuery);
    }

    @Override
    public List<InquiryAttachmentVO> queryInquiryAttachmentVOList(InquiryAttachmentFilterDTO inquiryAttachmentFilterDTO) {
        if (ObjectUtil.isNull(inquiryAttachmentFilterDTO)) {
            inquiryAttachmentFilterDTO = new InquiryAttachmentFilterDTO();
        }
        inquiryAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryInquiryAttachmentList(inquiryAttachmentFilterDTO));
    }

    @Override
    public PageVO<InquiryAttachmentVO> queryInquiryAttachment(InquiryAttachmentFilterDTO inquiryAttachmentFilterDTO) {
        if (ObjectUtil.isNull(inquiryAttachmentFilterDTO)) {
            inquiryAttachmentFilterDTO = new InquiryAttachmentFilterDTO();
        }
        inquiryAttachmentFilterDTO.setDeleted(0);
        PageVO<InquiryAttachmentVO> pageVO = new PageVO<>();

        if (inquiryAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquiryAttachmentFilterDTO.getCurrentPage(), inquiryAttachmentFilterDTO.getPageSize(),
                    inquiryAttachmentFilterDTO.getOrderType(), inquiryAttachmentFilterDTO.getOrderField());
        }

        List<InquiryAttachment> inquiryAttachmentList = queryInquiryAttachmentList(inquiryAttachmentFilterDTO);

        PageInfo<InquiryAttachment> pageInfo = new PageInfo<>(inquiryAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inquiryAttachmentList));

        return pageVO;
    }

    @Override
    public InquiryAttachmentVO queryInquiryAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

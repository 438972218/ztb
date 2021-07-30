package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryVendorAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorAttachmentQuery;
import com.xdcplus.xdcweb.biz.generator.InquiryVendorAttachmentBaseService;
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
 * 询价供应商附件(InquiryVendorAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:10
 */
public class InquiryVendorAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<InquiryVendorAttachment, InquiryVendorAttachmentVO, InquiryVendorAttachment, InquiryVendorAttachmentMapper> implements InquiryVendorAttachmentBaseService<S, T, E> {

    @Autowired
    protected InquiryVendorAttachmentMapper inquiryVendorAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveInquiryVendorAttachment(InquiryVendorAttachmentDTO inquiryVendorAttachmentDTO) {

        InquiryVendorAttachment inquiryVendorAttachment = inquiryVendorAttachmentMapper.selectById(inquiryVendorAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(inquiryVendorAttachment)) {
            log.error("saveInquiryVendorAttachment() The InquiryVendorAttachment already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inquiryVendorAttachment = new InquiryVendorAttachment();
        BeanUtil.copyProperties(inquiryVendorAttachmentDTO, inquiryVendorAttachment);
        inquiryVendorAttachment.setCreatedTime(DateUtil.current());
        inquiryVendorAttachment.setDeleted(0);

        return this.save(inquiryVendorAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateInquiryVendorAttachment(InquiryVendorAttachmentDTO inquiryVendorAttachmentDTO) {

        InquiryVendorAttachment inquiryVendorAttachment = this.getById(inquiryVendorAttachmentDTO.getId());
        if (ObjectUtil.isNull(inquiryVendorAttachment)) {
            log.error("updateInquiryVendorAttachment() The InquiryVendorAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(inquiryVendorAttachmentDTO, inquiryVendorAttachment);
        inquiryVendorAttachment.setUpdatedUser(inquiryVendorAttachmentDTO.getUpdatedUser());
        inquiryVendorAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(inquiryVendorAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<InquiryVendorAttachment> inquiryVendorAttachmentList) {

        inquiryVendorAttachmentList.forEach(inquiryVendorAttachment -> {
            InquiryVendorAttachment inquiryVendorAttachmentParam = new InquiryVendorAttachment();
            inquiryVendorAttachmentParam.setId(inquiryVendorAttachment.getId());
            if (ObjectUtil.isNotNull(inquiryVendorAttachment.getId())) {
                inquiryVendorAttachment.setId(inquiryVendorAttachment.getId());
                inquiryVendorAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<InquiryVendorAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(InquiryVendorAttachment::getId, inquiryVendorAttachment.getId());
                update(inquiryVendorAttachment, lambdaUpdate);
            } else {
                inquiryVendorAttachment.setCreatedTime(DateUtil.current());
                inquiryVendorAttachment.setDeleted(0);
                save(inquiryVendorAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<InquiryVendorAttachmentDTO> inquiryVendorAttachmentDTOList) {

        List<InquiryVendorAttachment> inquiryVendorAttachmentList = BeanUtils.copyProperties(inquiryVendorAttachmentDTOList, InquiryVendorAttachment.class);
        return saveOrUpdateBatch(inquiryVendorAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteInquiryVendorAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        InquiryVendorAttachment inquiryVendorAttachment = this.getById(id);

        if (ObjectUtil.isNull(inquiryVendorAttachment)) {
            log.error("deleteInquiryVendorAttachment() The InquiryVendorAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        inquiryVendorAttachment.setDeleted(1);

        return this.updateById(inquiryVendorAttachment);
    }

    private List<InquiryVendorAttachment> queryInquiryVendorAttachmentList(InquiryVendorAttachmentFilterDTO inquiryVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorAttachmentFilterDTO)) {
            inquiryVendorAttachmentFilterDTO = new InquiryVendorAttachmentFilterDTO();
        }
        inquiryVendorAttachmentFilterDTO.setDeleted(0);
        InquiryVendorAttachmentQuery inquiryVendorAttachmentQuery = BeanUtil.copyProperties(inquiryVendorAttachmentFilterDTO, InquiryVendorAttachmentQuery.class);

        return inquiryVendorAttachmentMapper.queryInquiryVendorAttachment(inquiryVendorAttachmentQuery);
    }

    @Override
    public List<InquiryVendorAttachmentVO> queryInquiryVendorAttachmentVOList(InquiryVendorAttachmentFilterDTO inquiryVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorAttachmentFilterDTO)) {
            inquiryVendorAttachmentFilterDTO = new InquiryVendorAttachmentFilterDTO();
        }
        inquiryVendorAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryInquiryVendorAttachmentList(inquiryVendorAttachmentFilterDTO));
    }

    @Override
    public PageVO<InquiryVendorAttachmentVO> queryInquiryVendorAttachment(InquiryVendorAttachmentFilterDTO inquiryVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorAttachmentFilterDTO)) {
            inquiryVendorAttachmentFilterDTO = new InquiryVendorAttachmentFilterDTO();
        }
        inquiryVendorAttachmentFilterDTO.setDeleted(0);
        PageVO<InquiryVendorAttachmentVO> pageVO = new PageVO<>();

        if (inquiryVendorAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquiryVendorAttachmentFilterDTO.getCurrentPage(), inquiryVendorAttachmentFilterDTO.getPageSize(),
                    inquiryVendorAttachmentFilterDTO.getOrderType(), inquiryVendorAttachmentFilterDTO.getOrderField());
        }

        List<InquiryVendorAttachment> inquiryVendorAttachmentList = queryInquiryVendorAttachmentList(inquiryVendorAttachmentFilterDTO);

        PageInfo<InquiryVendorAttachment> pageInfo = new PageInfo<>(inquiryVendorAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inquiryVendorAttachmentList));

        return pageVO;
    }

    @Override
    public InquiryVendorAttachmentVO queryInquiryVendorAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

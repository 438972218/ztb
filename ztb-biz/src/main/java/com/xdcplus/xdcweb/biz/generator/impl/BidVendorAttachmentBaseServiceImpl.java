package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidVendorAttachmentMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidVendorAttachment;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorAttachmentQuery;
import com.xdcplus.xdcweb.biz.generator.BidVendorAttachmentBaseService;
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
 * 招标投标方附件(BidVendorAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:54
 */
public class BidVendorAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidVendorAttachment, BidVendorAttachmentVO, BidVendorAttachment, BidVendorAttachmentMapper> implements BidVendorAttachmentBaseService<S, T, E> {

    @Autowired
    protected BidVendorAttachmentMapper bidVendorAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidVendorAttachment(BidVendorAttachmentDTO bidVendorAttachmentDTO) {

        BidVendorAttachment bidVendorAttachment = bidVendorAttachmentMapper.selectById(bidVendorAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(bidVendorAttachment)) {
            log.error("saveBidVendorAttachment() The BidVendorAttachment already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidVendorAttachment = new BidVendorAttachment();
        BeanUtil.copyProperties(bidVendorAttachmentDTO, bidVendorAttachment);
        bidVendorAttachment.setCreatedTime(DateUtil.current());
        bidVendorAttachment.setDeleted(0);

        return this.save(bidVendorAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidVendorAttachment(BidVendorAttachmentDTO bidVendorAttachmentDTO) {

        BidVendorAttachment bidVendorAttachment = this.getById(bidVendorAttachmentDTO.getId());
        if (ObjectUtil.isNull(bidVendorAttachment)) {
            log.error("updateBidVendorAttachment() The BidVendorAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidVendorAttachmentDTO, bidVendorAttachment);
        bidVendorAttachment.setUpdatedUser(bidVendorAttachmentDTO.getUpdatedUser());
        bidVendorAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(bidVendorAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidVendorAttachment> bidVendorAttachmentList) {

        bidVendorAttachmentList.forEach(bidVendorAttachment -> {
            BidVendorAttachment bidVendorAttachmentParam = new BidVendorAttachment();
            bidVendorAttachmentParam.setId(bidVendorAttachment.getId());
            if (ObjectUtil.isNotNull(bidVendorAttachment.getId())) {
                bidVendorAttachment.setId(bidVendorAttachment.getId());
                bidVendorAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidVendorAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidVendorAttachment::getId, bidVendorAttachment.getId());
                update(bidVendorAttachment, lambdaUpdate);
            } else {
                bidVendorAttachment.setCreatedTime(DateUtil.current());
                bidVendorAttachment.setDeleted(0);
                save(bidVendorAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidVendorAttachmentDTO> bidVendorAttachmentDTOList) {

        List<BidVendorAttachment> bidVendorAttachmentList = BeanUtils.copyProperties(bidVendorAttachmentDTOList, BidVendorAttachment.class);
        return saveOrUpdateBatch(bidVendorAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidVendorAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidVendorAttachment bidVendorAttachment = this.getById(id);

        if (ObjectUtil.isNull(bidVendorAttachment)) {
            log.error("deleteBidVendorAttachment() The BidVendorAttachment does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidVendorAttachment.setDeleted(1);

        return this.updateById(bidVendorAttachment);
    }

    private List<BidVendorAttachment> queryBidVendorAttachmentList(BidVendorAttachmentFilterDTO bidVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(bidVendorAttachmentFilterDTO)) {
            bidVendorAttachmentFilterDTO = new BidVendorAttachmentFilterDTO();
        }
        bidVendorAttachmentFilterDTO.setDeleted(0);
        BidVendorAttachmentQuery bidVendorAttachmentQuery = BeanUtil.copyProperties(bidVendorAttachmentFilterDTO, BidVendorAttachmentQuery.class);

        return bidVendorAttachmentMapper.queryBidVendorAttachment(bidVendorAttachmentQuery);
    }

    @Override
    public List<BidVendorAttachmentVO> queryBidVendorAttachmentVOList(BidVendorAttachmentFilterDTO bidVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(bidVendorAttachmentFilterDTO)) {
            bidVendorAttachmentFilterDTO = new BidVendorAttachmentFilterDTO();
        }
        bidVendorAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidVendorAttachmentList(bidVendorAttachmentFilterDTO));
    }

    @Override
    public PageVO<BidVendorAttachmentVO> queryBidVendorAttachment(BidVendorAttachmentFilterDTO bidVendorAttachmentFilterDTO) {
        if (ObjectUtil.isNull(bidVendorAttachmentFilterDTO)) {
            bidVendorAttachmentFilterDTO = new BidVendorAttachmentFilterDTO();
        }
        bidVendorAttachmentFilterDTO.setDeleted(0);
        PageVO<BidVendorAttachmentVO> pageVO = new PageVO<>();

        if (bidVendorAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidVendorAttachmentFilterDTO.getCurrentPage(), bidVendorAttachmentFilterDTO.getPageSize(),
                    bidVendorAttachmentFilterDTO.getOrderType(), bidVendorAttachmentFilterDTO.getOrderField());
        }

        List<BidVendorAttachment> bidVendorAttachmentList = queryBidVendorAttachmentList(bidVendorAttachmentFilterDTO);

        PageInfo<BidVendorAttachment> pageInfo = new PageInfo<>(bidVendorAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidVendorAttachmentList));

        return pageVO;
    }

    @Override
    public BidVendorAttachmentVO queryBidVendorAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

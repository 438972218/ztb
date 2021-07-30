package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidSpecialistMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialist;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidSpecialistQuery;
import com.xdcplus.xdcweb.biz.generator.BidSpecialistBaseService;
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
 * 专家(BidSpecialist)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:51
 */
public class BidSpecialistBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidSpecialist, BidSpecialistVO, BidSpecialist, BidSpecialistMapper> implements BidSpecialistBaseService<S, T, E> {

    @Autowired
    protected BidSpecialistMapper bidSpecialistMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidSpecialist(BidSpecialistDTO bidSpecialistDTO) {

        BidSpecialist bidSpecialist = bidSpecialistMapper.selectById(bidSpecialistDTO.getId());
        if (ObjectUtil.isNotNull(bidSpecialist)) {
            log.error("saveBidSpecialist() The BidSpecialist already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidSpecialist = new BidSpecialist();
        BeanUtil.copyProperties(bidSpecialistDTO, bidSpecialist);
        bidSpecialist.setCreatedTime(DateUtil.current());
        bidSpecialist.setDeleted(0);

        return this.save(bidSpecialist);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidSpecialist(BidSpecialistDTO bidSpecialistDTO) {

        BidSpecialist bidSpecialist = this.getById(bidSpecialistDTO.getId());
        if (ObjectUtil.isNull(bidSpecialist)) {
            log.error("updateBidSpecialist() The BidSpecialist does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidSpecialistDTO, bidSpecialist);
        bidSpecialist.setUpdatedUser(bidSpecialistDTO.getUpdatedUser());
        bidSpecialist.setUpdatedTime(DateUtil.current());

        return this.updateById(bidSpecialist);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidSpecialist> bidSpecialistList) {

        bidSpecialistList.forEach(bidSpecialist -> {
            BidSpecialist bidSpecialistParam = new BidSpecialist();
            bidSpecialistParam.setId(bidSpecialist.getId());
            if (ObjectUtil.isNotNull(bidSpecialist.getId())) {
                bidSpecialist.setId(bidSpecialist.getId());
                bidSpecialist.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidSpecialist> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidSpecialist::getId, bidSpecialist.getId());
                update(bidSpecialist, lambdaUpdate);
            } else {
                bidSpecialist.setCreatedTime(DateUtil.current());
                bidSpecialist.setDeleted(0);
                save(bidSpecialist);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidSpecialistDTO> bidSpecialistDTOList) {

        List<BidSpecialist> bidSpecialistList = BeanUtils.copyProperties(bidSpecialistDTOList, BidSpecialist.class);
        return saveOrUpdateBatch(bidSpecialistList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidSpecialistLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidSpecialist bidSpecialist = this.getById(id);

        if (ObjectUtil.isNull(bidSpecialist)) {
            log.error("deleteBidSpecialist() The BidSpecialist does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidSpecialist.setDeleted(1);

        return this.updateById(bidSpecialist);
    }

    private List<BidSpecialist> queryBidSpecialistList(BidSpecialistFilterDTO bidSpecialistFilterDTO) {
        if (ObjectUtil.isNull(bidSpecialistFilterDTO)) {
            bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
        }
        bidSpecialistFilterDTO.setDeleted(0);
        BidSpecialistQuery bidSpecialistQuery = BeanUtil.copyProperties(bidSpecialistFilterDTO, BidSpecialistQuery.class);

        return bidSpecialistMapper.queryBidSpecialist(bidSpecialistQuery);
    }

    @Override
    public List<BidSpecialistVO> queryBidSpecialistVOList(BidSpecialistFilterDTO bidSpecialistFilterDTO) {
        if (ObjectUtil.isNull(bidSpecialistFilterDTO)) {
            bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
        }
        bidSpecialistFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidSpecialistList(bidSpecialistFilterDTO));
    }

    @Override
    public PageVO<BidSpecialistVO> queryBidSpecialist(BidSpecialistFilterDTO bidSpecialistFilterDTO) {
        if (ObjectUtil.isNull(bidSpecialistFilterDTO)) {
            bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
        }
        bidSpecialistFilterDTO.setDeleted(0);
        PageVO<BidSpecialistVO> pageVO = new PageVO<>();

        if (bidSpecialistFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSpecialistFilterDTO.getCurrentPage(), bidSpecialistFilterDTO.getPageSize(),
                    bidSpecialistFilterDTO.getOrderType(), bidSpecialistFilterDTO.getOrderField());
        }

        List<BidSpecialist> bidSpecialistList = queryBidSpecialistList(bidSpecialistFilterDTO);

        PageInfo<BidSpecialist> pageInfo = new PageInfo<>(bidSpecialistList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidSpecialistList));

        return pageVO;
    }

    @Override
    public BidSpecialistVO queryBidSpecialistById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

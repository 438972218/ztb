package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidScoringElementsMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidScoringElements;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidScoringElementsDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidScoringElementsFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidScoringElementsVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidScoringElementsQuery;
import com.xdcplus.xdcweb.biz.generator.BidScoringElementsBaseService;
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
 * 评分要素(BidScoringElements)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:48
 */
public class BidScoringElementsBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidScoringElements, BidScoringElementsVO, BidScoringElements, BidScoringElementsMapper> implements BidScoringElementsBaseService<S, T, E> {

    @Autowired
    protected BidScoringElementsMapper bidScoringElementsMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidScoringElements(BidScoringElementsDTO bidScoringElementsDTO) {

        BidScoringElements bidScoringElements = bidScoringElementsMapper.selectById(bidScoringElementsDTO.getId());
        if (ObjectUtil.isNotNull(bidScoringElements)) {
            log.error("saveBidScoringElements() The BidScoringElements already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidScoringElements = new BidScoringElements();
        BeanUtil.copyProperties(bidScoringElementsDTO, bidScoringElements);
        bidScoringElements.setCreatedTime(DateUtil.current());
        bidScoringElements.setDeleted(0);

        return this.save(bidScoringElements);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidScoringElements(BidScoringElementsDTO bidScoringElementsDTO) {

        BidScoringElements bidScoringElements = this.getById(bidScoringElementsDTO.getId());
        if (ObjectUtil.isNull(bidScoringElements)) {
            log.error("updateBidScoringElements() The BidScoringElements does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidScoringElementsDTO, bidScoringElements);
        bidScoringElements.setUpdatedUser(bidScoringElementsDTO.getUpdatedUser());
        bidScoringElements.setUpdatedTime(DateUtil.current());

        return this.updateById(bidScoringElements);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidScoringElements> bidScoringElementsList) {

        bidScoringElementsList.forEach(bidScoringElements -> {
            BidScoringElements bidScoringElementsParam = new BidScoringElements();
            bidScoringElementsParam.setId(bidScoringElements.getId());
            if (ObjectUtil.isNotNull(bidScoringElements.getId())) {
                bidScoringElements.setId(bidScoringElements.getId());
                bidScoringElements.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidScoringElements> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidScoringElements::getId, bidScoringElements.getId());
                update(bidScoringElements, lambdaUpdate);
            } else {
                bidScoringElements.setCreatedTime(DateUtil.current());
                bidScoringElements.setDeleted(0);
                save(bidScoringElements);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidScoringElementsDTO> bidScoringElementsDTOList) {

        List<BidScoringElements> bidScoringElementsList = BeanUtils.copyProperties(bidScoringElementsDTOList, BidScoringElements.class);
        return saveOrUpdateBatch(bidScoringElementsList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidScoringElementsLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidScoringElements bidScoringElements = this.getById(id);

        if (ObjectUtil.isNull(bidScoringElements)) {
            log.error("deleteBidScoringElements() The BidScoringElements does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidScoringElements.setDeleted(1);

        return this.updateById(bidScoringElements);
    }

    private List<BidScoringElements> queryBidScoringElementsList(BidScoringElementsFilterDTO bidScoringElementsFilterDTO) {
        if (ObjectUtil.isNull(bidScoringElementsFilterDTO)) {
            bidScoringElementsFilterDTO = new BidScoringElementsFilterDTO();
        }
        bidScoringElementsFilterDTO.setDeleted(0);
        BidScoringElementsQuery bidScoringElementsQuery = BeanUtil.copyProperties(bidScoringElementsFilterDTO, BidScoringElementsQuery.class);

        return bidScoringElementsMapper.queryBidScoringElements(bidScoringElementsQuery);
    }

    @Override
    public List<BidScoringElementsVO> queryBidScoringElementsVOList(BidScoringElementsFilterDTO bidScoringElementsFilterDTO) {
        if (ObjectUtil.isNull(bidScoringElementsFilterDTO)) {
            bidScoringElementsFilterDTO = new BidScoringElementsFilterDTO();
        }
        bidScoringElementsFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidScoringElementsList(bidScoringElementsFilterDTO));
    }

    @Override
    public PageVO<BidScoringElementsVO> queryBidScoringElements(BidScoringElementsFilterDTO bidScoringElementsFilterDTO) {
        if (ObjectUtil.isNull(bidScoringElementsFilterDTO)) {
            bidScoringElementsFilterDTO = new BidScoringElementsFilterDTO();
        }
        bidScoringElementsFilterDTO.setDeleted(0);
        PageVO<BidScoringElementsVO> pageVO = new PageVO<>();

        if (bidScoringElementsFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidScoringElementsFilterDTO.getCurrentPage(), bidScoringElementsFilterDTO.getPageSize(),
                    bidScoringElementsFilterDTO.getOrderType(), bidScoringElementsFilterDTO.getOrderField());
        }

        List<BidScoringElements> bidScoringElementsList = queryBidScoringElementsList(bidScoringElementsFilterDTO);

        PageInfo<BidScoringElements> pageInfo = new PageInfo<>(bidScoringElementsList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidScoringElementsList));

        return pageVO;
    }

    @Override
    public BidScoringElementsVO queryBidScoringElementsById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

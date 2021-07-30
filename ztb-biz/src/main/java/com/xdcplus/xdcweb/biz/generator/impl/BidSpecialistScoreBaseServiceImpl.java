package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.xdcweb.biz.mapper.BidSpecialistScoreMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistScoreVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidSpecialistScoreQuery;
import com.xdcplus.xdcweb.biz.generator.BidSpecialistScoreBaseService;
import com.xdcplus.xdcweb.biz.service.BidVendorService;
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
 * 招标专家评分(BidSpecialistScore)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:37:52
 */
public class BidSpecialistScoreBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidSpecialistScore, BidSpecialistScoreVO, BidSpecialistScore, BidSpecialistScoreMapper> implements BidSpecialistScoreBaseService<S, T, E> {

    @Autowired
    protected BidSpecialistScoreMapper bidSpecialistScoreMapper;

    @Autowired
    protected BidVendorService bidVendorService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidSpecialistScore(BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        BidSpecialistScore bidSpecialistScore = bidSpecialistScoreMapper.selectById(bidSpecialistScoreDTO.getId());
        if (ObjectUtil.isNotNull(bidSpecialistScore)) {
            log.error("saveBidSpecialistScore() The BidSpecialistScore already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        bidSpecialistScore = new BidSpecialistScore();
        BeanUtil.copyProperties(bidSpecialistScoreDTO, bidSpecialistScore);
        bidSpecialistScore.setCreatedTime(DateUtil.current());
        bidSpecialistScore.setDeleted(0);

        return this.save(bidSpecialistScore);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidSpecialistScore(BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        BidSpecialistScore bidSpecialistScore = this.getById(bidSpecialistScoreDTO.getId());
        if (ObjectUtil.isNull(bidSpecialistScore)) {
            log.error("updateBidSpecialistScore() The BidSpecialistScore does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidSpecialistScoreDTO, bidSpecialistScore);
        bidSpecialistScore.setUpdatedUser(bidSpecialistScoreDTO.getUpdatedUser());
        bidSpecialistScore.setUpdatedTime(DateUtil.current());

        return this.updateById(bidSpecialistScore);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidSpecialistScore> bidSpecialistScoreList) {

        bidSpecialistScoreList.forEach(bidSpecialistScore -> {
            BidSpecialistScore bidSpecialistScoreParam = new BidSpecialistScore();
            bidSpecialistScoreParam.setId(bidSpecialistScore.getId());
            if (ObjectUtil.isNotNull(bidSpecialistScore.getId())) {
                bidSpecialistScore.setId(bidSpecialistScore.getId());
                bidSpecialistScore.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidSpecialistScore> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidSpecialistScore::getId, bidSpecialistScore.getId());
                update(bidSpecialistScore, lambdaUpdate);
            } else {
                bidSpecialistScore.setCreatedTime(DateUtil.current());
                bidSpecialistScore.setDeleted(0);
                save(bidSpecialistScore);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidSpecialistScoreDTO> bidSpecialistScoreDTOList) {

        List<BidSpecialistScore> bidSpecialistScoreList = BeanUtils.copyProperties(bidSpecialistScoreDTOList, BidSpecialistScore.class);
        return saveOrUpdateBatch(bidSpecialistScoreList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidSpecialistScoreLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidSpecialistScore bidSpecialistScore = this.getById(id);

        if (ObjectUtil.isNull(bidSpecialistScore)) {
            log.error("deleteBidSpecialistScore() The BidSpecialistScore does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        bidSpecialistScore.setDeleted(1);

        return this.updateById(bidSpecialistScore);
    }

    private List<BidSpecialistScore> queryBidSpecialistScoreList(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {
        if (ObjectUtil.isNull(bidSpecialistScoreFilterDTO)) {
            bidSpecialistScoreFilterDTO = new BidSpecialistScoreFilterDTO();
        }
        bidSpecialistScoreFilterDTO.setDeleted(0);
        BidSpecialistScoreQuery bidSpecialistScoreQuery = BeanUtil.copyProperties(bidSpecialistScoreFilterDTO, BidSpecialistScoreQuery.class);

        return bidSpecialistScoreMapper.queryBidSpecialistScore(bidSpecialistScoreQuery);
    }

    @Override
    public List<BidSpecialistScoreVO> queryBidSpecialistScoreVOList(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {
        if (ObjectUtil.isNull(bidSpecialistScoreFilterDTO)) {
            bidSpecialistScoreFilterDTO = new BidSpecialistScoreFilterDTO();
        }
        bidSpecialistScoreFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidSpecialistScoreList(bidSpecialistScoreFilterDTO));
    }

    @Override
    public PageVO<BidSpecialistScoreVO> queryBidSpecialistScore(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {
        if (ObjectUtil.isNull(bidSpecialistScoreFilterDTO)) {
            bidSpecialistScoreFilterDTO = new BidSpecialistScoreFilterDTO();
        }
        bidSpecialistScoreFilterDTO.setDeleted(0);
        PageVO<BidSpecialistScoreVO> pageVO = new PageVO<>();

        if (bidSpecialistScoreFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSpecialistScoreFilterDTO.getCurrentPage(), bidSpecialistScoreFilterDTO.getPageSize(),
                    bidSpecialistScoreFilterDTO.getOrderType(), bidSpecialistScoreFilterDTO.getOrderField());
        }

        List<BidSpecialistScore> bidSpecialistScoreList = queryBidSpecialistScoreList(bidSpecialistScoreFilterDTO);

        PageInfo<BidSpecialistScore> pageInfo = new PageInfo<>(bidSpecialistScoreList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidSpecialistScoreList));

        return pageVO;
    }

    @Override
    public BidSpecialistScoreVO queryBidSpecialistScoreById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }

}

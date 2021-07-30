package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQualifyReviewMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReview;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQualifyReviewQuery;
import com.xdcplus.xdcweb.biz.generator.VendorQualifyReviewBaseService;
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
 * 供应商合格评审表(VendorQualifyReview)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:47
 */
public class VendorQualifyReviewBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorQualifyReview, VendorQualifyReviewVO, VendorQualifyReview, VendorQualifyReviewMapper> implements VendorQualifyReviewBaseService<S, T, E> {

    @Autowired
    protected VendorQualifyReviewMapper vendorQualifyReviewMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorQualifyReview(VendorQualifyReviewDTO vendorQualifyReviewDTO) {

        VendorQualifyReview vendorQualifyReview = vendorQualifyReviewMapper.selectById(vendorQualifyReviewDTO.getId());
        if (ObjectUtil.isNotNull(vendorQualifyReview)) {
            log.error("saveVendorQualifyReview() The VendorQualifyReview already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorQualifyReview = new VendorQualifyReview();
        BeanUtil.copyProperties(vendorQualifyReviewDTO, vendorQualifyReview);
        vendorQualifyReview.setCreatedTime(DateUtil.current());
        vendorQualifyReview.setDeleted(0);

        return this.save(vendorQualifyReview);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorQualifyReview(VendorQualifyReviewDTO vendorQualifyReviewDTO) {

        VendorQualifyReview vendorQualifyReview = this.getById(vendorQualifyReviewDTO.getId());
        if (ObjectUtil.isNull(vendorQualifyReview)) {
            log.error("updateVendorQualifyReview() The VendorQualifyReview does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorQualifyReviewDTO, vendorQualifyReview);
        vendorQualifyReview.setUpdatedUser(vendorQualifyReviewDTO.getUpdatedUser());
        vendorQualifyReview.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorQualifyReview);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorQualifyReview> vendorQualifyReviewList) {

        vendorQualifyReviewList.forEach(vendorQualifyReview -> {
            VendorQualifyReview vendorQualifyReviewParam = new VendorQualifyReview();
            vendorQualifyReviewParam.setId(vendorQualifyReview.getId());
            if (ObjectUtil.isNotNull(vendorQualifyReview.getId())) {
                vendorQualifyReview.setId(vendorQualifyReview.getId());
                vendorQualifyReview.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorQualifyReview> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorQualifyReview::getId, vendorQualifyReview.getId());
                update(vendorQualifyReview, lambdaUpdate);
            } else {
                vendorQualifyReview.setCreatedTime(DateUtil.current());
                vendorQualifyReview.setDeleted(0);
                save(vendorQualifyReview);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorQualifyReviewDTO> vendorQualifyReviewDTOList) {

        List<VendorQualifyReview> vendorQualifyReviewList = BeanUtils.copyProperties(vendorQualifyReviewDTOList, VendorQualifyReview.class);
        return saveOrUpdateBatch(vendorQualifyReviewList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorQualifyReviewLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorQualifyReview vendorQualifyReview = this.getById(id);

        if (ObjectUtil.isNull(vendorQualifyReview)) {
            log.error("deleteVendorQualifyReview() The VendorQualifyReview does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorQualifyReview.setDeleted(1);

        return this.updateById(vendorQualifyReview);
    }

    private List<VendorQualifyReview> queryVendorQualifyReviewList(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO) {
        if (ObjectUtil.isNull(vendorQualifyReviewFilterDTO)) {
            vendorQualifyReviewFilterDTO = new VendorQualifyReviewFilterDTO();
        }
        vendorQualifyReviewFilterDTO.setDeleted(0);
        VendorQualifyReviewQuery vendorQualifyReviewQuery = BeanUtil.copyProperties(vendorQualifyReviewFilterDTO, VendorQualifyReviewQuery.class);

        return vendorQualifyReviewMapper.queryVendorQualifyReview(vendorQualifyReviewQuery);
    }

    @Override
    public List<VendorQualifyReviewVO> queryVendorQualifyReviewVOList(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO) {
        if (ObjectUtil.isNull(vendorQualifyReviewFilterDTO)) {
            vendorQualifyReviewFilterDTO = new VendorQualifyReviewFilterDTO();
        }
        vendorQualifyReviewFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorQualifyReviewList(vendorQualifyReviewFilterDTO));
    }

    @Override
    public PageVO<VendorQualifyReviewVO> queryVendorQualifyReview(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO) {
        if (ObjectUtil.isNull(vendorQualifyReviewFilterDTO)) {
            vendorQualifyReviewFilterDTO = new VendorQualifyReviewFilterDTO();
        }
        vendorQualifyReviewFilterDTO.setDeleted(0);
        PageVO<VendorQualifyReviewVO> pageVO = new PageVO<>();

        if (vendorQualifyReviewFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorQualifyReviewFilterDTO.getCurrentPage(), vendorQualifyReviewFilterDTO.getPageSize(),
                    vendorQualifyReviewFilterDTO.getOrderType(), vendorQualifyReviewFilterDTO.getOrderField());
        }

        List<VendorQualifyReview> vendorQualifyReviewList = queryVendorQualifyReviewList(vendorQualifyReviewFilterDTO);

        PageInfo<VendorQualifyReview> pageInfo = new PageInfo<>(vendorQualifyReviewList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorQualifyReviewList));

        return pageVO;
    }

    @Override
    public VendorQualifyReviewVO queryVendorQualifyReviewById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQualifyReviewDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReviewDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewDetailVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQualifyReviewDetailQuery;
import com.xdcplus.xdcweb.biz.generator.VendorQualifyReviewDetailBaseService;
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
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:48
 */
public class VendorQualifyReviewDetailBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorQualifyReviewDetail, VendorQualifyReviewDetailVO, VendorQualifyReviewDetail, VendorQualifyReviewDetailMapper> implements VendorQualifyReviewDetailBaseService<S, T, E> {

    @Autowired
    protected VendorQualifyReviewDetailMapper vendorQualifyReviewDetailMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorQualifyReviewDetail(VendorQualifyReviewDetailDTO vendorQualifyReviewDetailDTO) {

        VendorQualifyReviewDetail vendorQualifyReviewDetail = vendorQualifyReviewDetailMapper.selectById(vendorQualifyReviewDetailDTO.getId());
        if (ObjectUtil.isNotNull(vendorQualifyReviewDetail)) {
            log.error("saveVendorQualifyReviewDetail() The VendorQualifyReviewDetail already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorQualifyReviewDetail = new VendorQualifyReviewDetail();
        BeanUtil.copyProperties(vendorQualifyReviewDetailDTO, vendorQualifyReviewDetail);
        vendorQualifyReviewDetail.setCreatedTime(DateUtil.current());
        vendorQualifyReviewDetail.setDeleted(0);

        return this.save(vendorQualifyReviewDetail);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorQualifyReviewDetail(VendorQualifyReviewDetailDTO vendorQualifyReviewDetailDTO) {

        VendorQualifyReviewDetail vendorQualifyReviewDetail = this.getById(vendorQualifyReviewDetailDTO.getId());
        if (ObjectUtil.isNull(vendorQualifyReviewDetail)) {
            log.error("updateVendorQualifyReviewDetail() The VendorQualifyReviewDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorQualifyReviewDetailDTO, vendorQualifyReviewDetail);
        vendorQualifyReviewDetail.setUpdatedUser(vendorQualifyReviewDetailDTO.getUpdatedUser());
        vendorQualifyReviewDetail.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorQualifyReviewDetail);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorQualifyReviewDetail> vendorQualifyReviewDetailList) {

        vendorQualifyReviewDetailList.forEach(vendorQualifyReviewDetail -> {
            VendorQualifyReviewDetail vendorQualifyReviewDetailParam = new VendorQualifyReviewDetail();
            vendorQualifyReviewDetailParam.setId(vendorQualifyReviewDetail.getId());
            if (ObjectUtil.isNotNull(vendorQualifyReviewDetail.getId())) {
                vendorQualifyReviewDetail.setId(vendorQualifyReviewDetail.getId());
                vendorQualifyReviewDetail.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorQualifyReviewDetail> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorQualifyReviewDetail::getId, vendorQualifyReviewDetail.getId());
                update(vendorQualifyReviewDetail, lambdaUpdate);
            } else {
                vendorQualifyReviewDetail.setCreatedTime(DateUtil.current());
                vendorQualifyReviewDetail.setDeleted(0);
                save(vendorQualifyReviewDetail);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorQualifyReviewDetailDTO> vendorQualifyReviewDetailDTOList) {

        List<VendorQualifyReviewDetail> vendorQualifyReviewDetailList = BeanUtils.copyProperties(vendorQualifyReviewDetailDTOList, VendorQualifyReviewDetail.class);
        return saveOrUpdateBatch(vendorQualifyReviewDetailList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorQualifyReviewDetailLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorQualifyReviewDetail vendorQualifyReviewDetail = this.getById(id);

        if (ObjectUtil.isNull(vendorQualifyReviewDetail)) {
            log.error("deleteVendorQualifyReviewDetail() The VendorQualifyReviewDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorQualifyReviewDetail.setDeleted(1);

        return this.updateById(vendorQualifyReviewDetail);
    }

    private List<VendorQualifyReviewDetail> queryVendorQualifyReviewDetailList(VendorQualifyReviewDetailFilterDTO vendorQualifyReviewDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorQualifyReviewDetailFilterDTO)) {
            vendorQualifyReviewDetailFilterDTO = new VendorQualifyReviewDetailFilterDTO();
        }
        vendorQualifyReviewDetailFilterDTO.setDeleted(0);
        VendorQualifyReviewDetailQuery vendorQualifyReviewDetailQuery = BeanUtil.copyProperties(vendorQualifyReviewDetailFilterDTO, VendorQualifyReviewDetailQuery.class);

        return vendorQualifyReviewDetailMapper.queryVendorQualifyReviewDetail(vendorQualifyReviewDetailQuery);
    }

    @Override
    public List<VendorQualifyReviewDetailVO> queryVendorQualifyReviewDetailVOList(VendorQualifyReviewDetailFilterDTO vendorQualifyReviewDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorQualifyReviewDetailFilterDTO)) {
            vendorQualifyReviewDetailFilterDTO = new VendorQualifyReviewDetailFilterDTO();
        }
        vendorQualifyReviewDetailFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorQualifyReviewDetailList(vendorQualifyReviewDetailFilterDTO));
    }

    @Override
    public PageVO<VendorQualifyReviewDetailVO> queryVendorQualifyReviewDetail(VendorQualifyReviewDetailFilterDTO vendorQualifyReviewDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorQualifyReviewDetailFilterDTO)) {
            vendorQualifyReviewDetailFilterDTO = new VendorQualifyReviewDetailFilterDTO();
        }
        vendorQualifyReviewDetailFilterDTO.setDeleted(0);
        PageVO<VendorQualifyReviewDetailVO> pageVO = new PageVO<>();

        if (vendorQualifyReviewDetailFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorQualifyReviewDetailFilterDTO.getCurrentPage(), vendorQualifyReviewDetailFilterDTO.getPageSize(),
                    vendorQualifyReviewDetailFilterDTO.getOrderType(), vendorQualifyReviewDetailFilterDTO.getOrderField());
        }

        List<VendorQualifyReviewDetail> vendorQualifyReviewDetailList = queryVendorQualifyReviewDetailList(vendorQualifyReviewDetailFilterDTO);

        PageInfo<VendorQualifyReviewDetail> pageInfo = new PageInfo<>(vendorQualifyReviewDetailList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorQualifyReviewDetailList));

        return pageVO;
    }

    @Override
    public VendorQualifyReviewDetailVO queryVendorQualifyReviewDetailById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

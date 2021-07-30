package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiDetailQuery;
import com.xdcplus.xdcweb.biz.generator.VendorKpiDetailBaseService;
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
 * 供应商绩效考核表明细(VendorKpiDetail)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:46
 */
public class VendorKpiDetailBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorKpiDetail, VendorKpiDetailVO, VendorKpiDetail, VendorKpiDetailMapper> implements VendorKpiDetailBaseService<S, T, E> {

    @Autowired
    protected VendorKpiDetailMapper vendorKpiDetailMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorKpiDetail(VendorKpiDetailDTO vendorKpiDetailDTO) {

        VendorKpiDetail vendorKpiDetail = vendorKpiDetailMapper.selectById(vendorKpiDetailDTO.getId());
        if (ObjectUtil.isNotNull(vendorKpiDetail)) {
            log.error("saveVendorKpiDetail() The VendorKpiDetail already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorKpiDetail = new VendorKpiDetail();
        BeanUtil.copyProperties(vendorKpiDetailDTO, vendorKpiDetail);
        vendorKpiDetail.setCreatedTime(DateUtil.current());
        vendorKpiDetail.setDeleted(0);

        return this.save(vendorKpiDetail);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorKpiDetail(VendorKpiDetailDTO vendorKpiDetailDTO) {

        VendorKpiDetail vendorKpiDetail = this.getById(vendorKpiDetailDTO.getId());
        if (ObjectUtil.isNull(vendorKpiDetail)) {
            log.error("updateVendorKpiDetail() The VendorKpiDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorKpiDetailDTO, vendorKpiDetail);
        vendorKpiDetail.setUpdatedUser(vendorKpiDetailDTO.getUpdatedUser());
        vendorKpiDetail.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorKpiDetail);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorKpiDetail> vendorKpiDetailList) {

        vendorKpiDetailList.forEach(vendorKpiDetail -> {
            VendorKpiDetail vendorKpiDetailParam = new VendorKpiDetail();
            vendorKpiDetailParam.setId(vendorKpiDetail.getId());
            if (ObjectUtil.isNotNull(vendorKpiDetail.getId())) {
                vendorKpiDetail.setId(vendorKpiDetail.getId());
                vendorKpiDetail.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorKpiDetail> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorKpiDetail::getId, vendorKpiDetail.getId());
                update(vendorKpiDetail, lambdaUpdate);
            } else {
                vendorKpiDetail.setCreatedTime(DateUtil.current());
                vendorKpiDetail.setDeleted(0);
                save(vendorKpiDetail);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorKpiDetailDTO> vendorKpiDetailDTOList) {

        List<VendorKpiDetail> vendorKpiDetailList = BeanUtils.copyProperties(vendorKpiDetailDTOList, VendorKpiDetail.class);
        return saveOrUpdateBatch(vendorKpiDetailList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorKpiDetailLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorKpiDetail vendorKpiDetail = this.getById(id);

        if (ObjectUtil.isNull(vendorKpiDetail)) {
            log.error("deleteVendorKpiDetail() The VendorKpiDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorKpiDetail.setDeleted(1);

        return this.updateById(vendorKpiDetail);
    }

    private List<VendorKpiDetail> queryVendorKpiDetailList(VendorKpiDetailFilterDTO vendorKpiDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiDetailFilterDTO)) {
            vendorKpiDetailFilterDTO = new VendorKpiDetailFilterDTO();
        }
        vendorKpiDetailFilterDTO.setDeleted(0);
        VendorKpiDetailQuery vendorKpiDetailQuery = BeanUtil.copyProperties(vendorKpiDetailFilterDTO, VendorKpiDetailQuery.class);

        return vendorKpiDetailMapper.queryVendorKpiDetail(vendorKpiDetailQuery);
    }

    @Override
    public List<VendorKpiDetailVO> queryVendorKpiDetailVOList(VendorKpiDetailFilterDTO vendorKpiDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiDetailFilterDTO)) {
            vendorKpiDetailFilterDTO = new VendorKpiDetailFilterDTO();
        }
        vendorKpiDetailFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorKpiDetailList(vendorKpiDetailFilterDTO));
    }

    @Override
    public PageVO<VendorKpiDetailVO> queryVendorKpiDetail(VendorKpiDetailFilterDTO vendorKpiDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiDetailFilterDTO)) {
            vendorKpiDetailFilterDTO = new VendorKpiDetailFilterDTO();
        }
        vendorKpiDetailFilterDTO.setDeleted(0);
        PageVO<VendorKpiDetailVO> pageVO = new PageVO<>();

        if (vendorKpiDetailFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorKpiDetailFilterDTO.getCurrentPage(), vendorKpiDetailFilterDTO.getPageSize(),
                    vendorKpiDetailFilterDTO.getOrderType(), vendorKpiDetailFilterDTO.getOrderField());
        }

        List<VendorKpiDetail> vendorKpiDetailList = queryVendorKpiDetailList(vendorKpiDetailFilterDTO);

        PageInfo<VendorKpiDetail> pageInfo = new PageInfo<>(vendorKpiDetailList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorKpiDetailList));

        return pageVO;
    }

    @Override
    public VendorKpiDetailVO queryVendorKpiDetailById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

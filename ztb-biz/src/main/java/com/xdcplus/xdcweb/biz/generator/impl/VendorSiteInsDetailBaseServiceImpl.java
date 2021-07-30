package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsDetailQuery;
import com.xdcplus.xdcweb.biz.generator.VendorSiteInsDetailBaseService;
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
 * 供应商现场考察表明细(VendorSiteInsDetail)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:57
 */
public class VendorSiteInsDetailBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorSiteInsDetail, VendorSiteInsDetailVO, VendorSiteInsDetail, VendorSiteInsDetailMapper> implements VendorSiteInsDetailBaseService<S, T, E> {

    @Autowired
    protected VendorSiteInsDetailMapper vendorSiteInsDetailMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorSiteInsDetail(VendorSiteInsDetailDTO vendorSiteInsDetailDTO) {

        VendorSiteInsDetail vendorSiteInsDetail = vendorSiteInsDetailMapper.selectById(vendorSiteInsDetailDTO.getId());
        if (ObjectUtil.isNotNull(vendorSiteInsDetail)) {
            log.error("saveVendorSiteInsDetail() The VendorSiteInsDetail already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorSiteInsDetail = new VendorSiteInsDetail();
        BeanUtil.copyProperties(vendorSiteInsDetailDTO, vendorSiteInsDetail);
        vendorSiteInsDetail.setCreatedTime(DateUtil.current());
        vendorSiteInsDetail.setDeleted(0);

        return this.save(vendorSiteInsDetail);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorSiteInsDetail(VendorSiteInsDetailDTO vendorSiteInsDetailDTO) {

        VendorSiteInsDetail vendorSiteInsDetail = this.getById(vendorSiteInsDetailDTO.getId());
        if (ObjectUtil.isNull(vendorSiteInsDetail)) {
            log.error("updateVendorSiteInsDetail() The VendorSiteInsDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorSiteInsDetailDTO, vendorSiteInsDetail);
        vendorSiteInsDetail.setUpdatedUser(vendorSiteInsDetailDTO.getUpdatedUser());
        vendorSiteInsDetail.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorSiteInsDetail);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorSiteInsDetail> vendorSiteInsDetailList) {

        vendorSiteInsDetailList.forEach(vendorSiteInsDetail -> {
            VendorSiteInsDetail vendorSiteInsDetailParam = new VendorSiteInsDetail();
            vendorSiteInsDetailParam.setId(vendorSiteInsDetail.getId());
            if (ObjectUtil.isNotNull(vendorSiteInsDetail.getId())) {
                vendorSiteInsDetail.setId(vendorSiteInsDetail.getId());
                vendorSiteInsDetail.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorSiteInsDetail> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorSiteInsDetail::getId, vendorSiteInsDetail.getId());
                update(vendorSiteInsDetail, lambdaUpdate);
            } else {
                vendorSiteInsDetail.setCreatedTime(DateUtil.current());
                vendorSiteInsDetail.setDeleted(0);
                save(vendorSiteInsDetail);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsDetailDTO> vendorSiteInsDetailDTOList) {

        List<VendorSiteInsDetail> vendorSiteInsDetailList = BeanUtils.copyProperties(vendorSiteInsDetailDTOList, VendorSiteInsDetail.class);
        return saveOrUpdateBatch(vendorSiteInsDetailList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorSiteInsDetailLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorSiteInsDetail vendorSiteInsDetail = this.getById(id);

        if (ObjectUtil.isNull(vendorSiteInsDetail)) {
            log.error("deleteVendorSiteInsDetail() The VendorSiteInsDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorSiteInsDetail.setDeleted(1);

        return this.updateById(vendorSiteInsDetail);
    }

    private List<VendorSiteInsDetail> queryVendorSiteInsDetailList(VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsDetailFilterDTO)) {
            vendorSiteInsDetailFilterDTO = new VendorSiteInsDetailFilterDTO();
        }
        vendorSiteInsDetailFilterDTO.setDeleted(0);
        VendorSiteInsDetailQuery vendorSiteInsDetailQuery = BeanUtil.copyProperties(vendorSiteInsDetailFilterDTO, VendorSiteInsDetailQuery.class);

        return vendorSiteInsDetailMapper.queryVendorSiteInsDetail(vendorSiteInsDetailQuery);
    }

    @Override
    public List<VendorSiteInsDetailVO> queryVendorSiteInsDetailVOList(VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsDetailFilterDTO)) {
            vendorSiteInsDetailFilterDTO = new VendorSiteInsDetailFilterDTO();
        }
        vendorSiteInsDetailFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorSiteInsDetailList(vendorSiteInsDetailFilterDTO));
    }

    @Override
    public PageVO<VendorSiteInsDetailVO> queryVendorSiteInsDetail(VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsDetailFilterDTO)) {
            vendorSiteInsDetailFilterDTO = new VendorSiteInsDetailFilterDTO();
        }
        vendorSiteInsDetailFilterDTO.setDeleted(0);
        PageVO<VendorSiteInsDetailVO> pageVO = new PageVO<>();

        if (vendorSiteInsDetailFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorSiteInsDetailFilterDTO.getCurrentPage(), vendorSiteInsDetailFilterDTO.getPageSize(),
                    vendorSiteInsDetailFilterDTO.getOrderType(), vendorSiteInsDetailFilterDTO.getOrderField());
        }

        List<VendorSiteInsDetail> vendorSiteInsDetailList = queryVendorSiteInsDetailList(vendorSiteInsDetailFilterDTO);

        PageInfo<VendorSiteInsDetail> pageInfo = new PageInfo<>(vendorSiteInsDetailList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorSiteInsDetailList));

        return pageVO;
    }

    @Override
    public VendorSiteInsDetailVO queryVendorSiteInsDetailById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

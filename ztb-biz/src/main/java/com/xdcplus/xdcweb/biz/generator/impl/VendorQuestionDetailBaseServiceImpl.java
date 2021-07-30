package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQuestionDetailMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionDetail;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionDetailVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuestionDetailQuery;
import com.xdcplus.xdcweb.biz.generator.VendorQuestionDetailBaseService;
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
 * 供应商电子调查表明细(VendorQuestionDetail)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:51
 */
public class VendorQuestionDetailBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorQuestionDetail, VendorQuestionDetailVO, VendorQuestionDetail, VendorQuestionDetailMapper> implements VendorQuestionDetailBaseService<S, T, E> {

    @Autowired
    protected VendorQuestionDetailMapper vendorQuestionDetailMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorQuestionDetail(VendorQuestionDetailDTO vendorQuestionDetailDTO) {

        VendorQuestionDetail vendorQuestionDetail = vendorQuestionDetailMapper.selectById(vendorQuestionDetailDTO.getId());
        if (ObjectUtil.isNotNull(vendorQuestionDetail)) {
            log.error("saveVendorQuestionDetail() The VendorQuestionDetail already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorQuestionDetail = new VendorQuestionDetail();
        BeanUtil.copyProperties(vendorQuestionDetailDTO, vendorQuestionDetail);
        vendorQuestionDetail.setCreatedTime(DateUtil.current());
        vendorQuestionDetail.setDeleted(0);

        return this.save(vendorQuestionDetail);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorQuestionDetail(VendorQuestionDetailDTO vendorQuestionDetailDTO) {

        VendorQuestionDetail vendorQuestionDetail = this.getById(vendorQuestionDetailDTO.getId());
        if (ObjectUtil.isNull(vendorQuestionDetail)) {
            log.error("updateVendorQuestionDetail() The VendorQuestionDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorQuestionDetailDTO, vendorQuestionDetail);
        vendorQuestionDetail.setUpdatedUser(vendorQuestionDetailDTO.getUpdatedUser());
        vendorQuestionDetail.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorQuestionDetail);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorQuestionDetail> vendorQuestionDetailList) {

        vendorQuestionDetailList.forEach(vendorQuestionDetail -> {
            VendorQuestionDetail vendorQuestionDetailParam = new VendorQuestionDetail();
            vendorQuestionDetailParam.setId(vendorQuestionDetail.getId());
            if (ObjectUtil.isNotNull(vendorQuestionDetail.getId())) {
                vendorQuestionDetail.setId(vendorQuestionDetail.getId());
                vendorQuestionDetail.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorQuestionDetail> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorQuestionDetail::getId, vendorQuestionDetail.getId());
                update(vendorQuestionDetail, lambdaUpdate);
            } else {
                vendorQuestionDetail.setCreatedTime(DateUtil.current());
                vendorQuestionDetail.setDeleted(0);
                save(vendorQuestionDetail);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorQuestionDetailDTO> vendorQuestionDetailDTOList) {

        List<VendorQuestionDetail> vendorQuestionDetailList = BeanUtils.copyProperties(vendorQuestionDetailDTOList, VendorQuestionDetail.class);
        return saveOrUpdateBatch(vendorQuestionDetailList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorQuestionDetailLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorQuestionDetail vendorQuestionDetail = this.getById(id);

        if (ObjectUtil.isNull(vendorQuestionDetail)) {
            log.error("deleteVendorQuestionDetail() The VendorQuestionDetail does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorQuestionDetail.setDeleted(1);

        return this.updateById(vendorQuestionDetail);
    }

    private List<VendorQuestionDetail> queryVendorQuestionDetailList(VendorQuestionDetailFilterDTO vendorQuestionDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionDetailFilterDTO)) {
            vendorQuestionDetailFilterDTO = new VendorQuestionDetailFilterDTO();
        }
        vendorQuestionDetailFilterDTO.setDeleted(0);
        VendorQuestionDetailQuery vendorQuestionDetailQuery = BeanUtil.copyProperties(vendorQuestionDetailFilterDTO, VendorQuestionDetailQuery.class);

        return vendorQuestionDetailMapper.queryVendorQuestionDetail(vendorQuestionDetailQuery);
    }

    @Override
    public List<VendorQuestionDetailVO> queryVendorQuestionDetailVOList(VendorQuestionDetailFilterDTO vendorQuestionDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionDetailFilterDTO)) {
            vendorQuestionDetailFilterDTO = new VendorQuestionDetailFilterDTO();
        }
        vendorQuestionDetailFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorQuestionDetailList(vendorQuestionDetailFilterDTO));
    }

    @Override
    public PageVO<VendorQuestionDetailVO> queryVendorQuestionDetail(VendorQuestionDetailFilterDTO vendorQuestionDetailFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionDetailFilterDTO)) {
            vendorQuestionDetailFilterDTO = new VendorQuestionDetailFilterDTO();
        }
        vendorQuestionDetailFilterDTO.setDeleted(0);
        PageVO<VendorQuestionDetailVO> pageVO = new PageVO<>();

        if (vendorQuestionDetailFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorQuestionDetailFilterDTO.getCurrentPage(), vendorQuestionDetailFilterDTO.getPageSize(),
                    vendorQuestionDetailFilterDTO.getOrderType(), vendorQuestionDetailFilterDTO.getOrderField());
        }

        List<VendorQuestionDetail> vendorQuestionDetailList = queryVendorQuestionDetailList(vendorQuestionDetailFilterDTO);

        PageInfo<VendorQuestionDetail> pageInfo = new PageInfo<>(vendorQuestionDetailList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorQuestionDetailList));

        return pageVO;
    }

    @Override
    public VendorQuestionDetailVO queryVendorQuestionDetailById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InquiryVendorMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryVendor;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorQuery;
import com.xdcplus.xdcweb.biz.generator.InquiryVendorBaseService;
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
 * 询价供应商(InquiryVendor)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:10
 */
public class InquiryVendorBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<InquiryVendor, InquiryVendorVO, InquiryVendor, InquiryVendorMapper> implements InquiryVendorBaseService<S, T, E> {

    @Autowired
    protected InquiryVendorMapper inquiryVendorMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveInquiryVendor(InquiryVendorDTO inquiryVendorDTO) {

        InquiryVendor inquiryVendor = inquiryVendorMapper.selectById(inquiryVendorDTO.getId());
        if (ObjectUtil.isNotNull(inquiryVendor)) {
            log.error("saveInquiryVendor() The InquiryVendor already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inquiryVendor = new InquiryVendor();
        BeanUtil.copyProperties(inquiryVendorDTO, inquiryVendor);
        inquiryVendor.setCreatedTime(DateUtil.current());
        inquiryVendor.setDeleted(0);

        return this.save(inquiryVendor);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateInquiryVendor(InquiryVendorDTO inquiryVendorDTO) {

        InquiryVendor inquiryVendor = this.getById(inquiryVendorDTO.getId());
        if (ObjectUtil.isNull(inquiryVendor)) {
            log.error("updateInquiryVendor() The InquiryVendor does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(inquiryVendorDTO, inquiryVendor);
        inquiryVendor.setUpdatedUser(inquiryVendorDTO.getUpdatedUser());
        inquiryVendor.setUpdatedTime(DateUtil.current());

        return this.updateById(inquiryVendor);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<InquiryVendor> inquiryVendorList) {

        inquiryVendorList.forEach(inquiryVendor -> {
            InquiryVendor inquiryVendorParam = new InquiryVendor();
            inquiryVendorParam.setId(inquiryVendor.getId());
            if (ObjectUtil.isNotNull(inquiryVendor.getId())) {
                inquiryVendor.setId(inquiryVendor.getId());
                inquiryVendor.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<InquiryVendor> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(InquiryVendor::getId, inquiryVendor.getId());
                update(inquiryVendor, lambdaUpdate);
            } else {
                inquiryVendor.setCreatedTime(DateUtil.current());
                inquiryVendor.setDeleted(0);
                save(inquiryVendor);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<InquiryVendorDTO> inquiryVendorDTOList) {

        List<InquiryVendor> inquiryVendorList = BeanUtils.copyProperties(inquiryVendorDTOList, InquiryVendor.class);
        return saveOrUpdateBatch(inquiryVendorList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteInquiryVendorLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        InquiryVendor inquiryVendor = this.getById(id);

        if (ObjectUtil.isNull(inquiryVendor)) {
            log.error("deleteInquiryVendor() The InquiryVendor does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        inquiryVendor.setDeleted(1);

        return this.updateById(inquiryVendor);
    }

    private List<InquiryVendor> queryInquiryVendorList(InquiryVendorFilterDTO inquiryVendorFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorFilterDTO)) {
            inquiryVendorFilterDTO = new InquiryVendorFilterDTO();
        }
        inquiryVendorFilterDTO.setDeleted(0);
        InquiryVendorQuery inquiryVendorQuery = BeanUtil.copyProperties(inquiryVendorFilterDTO, InquiryVendorQuery.class);

        return inquiryVendorMapper.queryInquiryVendor(inquiryVendorQuery);
    }

    @Override
    public List<InquiryVendorVO> queryInquiryVendorVOList(InquiryVendorFilterDTO inquiryVendorFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorFilterDTO)) {
            inquiryVendorFilterDTO = new InquiryVendorFilterDTO();
        }
        inquiryVendorFilterDTO.setDeleted(0);
        return this.objectConversion(queryInquiryVendorList(inquiryVendorFilterDTO));
    }

    @Override
    public PageVO<InquiryVendorVO> queryInquiryVendor(InquiryVendorFilterDTO inquiryVendorFilterDTO) {
        if (ObjectUtil.isNull(inquiryVendorFilterDTO)) {
            inquiryVendorFilterDTO = new InquiryVendorFilterDTO();
        }
        inquiryVendorFilterDTO.setDeleted(0);
        PageVO<InquiryVendorVO> pageVO = new PageVO<>();

        if (inquiryVendorFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inquiryVendorFilterDTO.getCurrentPage(), inquiryVendorFilterDTO.getPageSize(),
                    inquiryVendorFilterDTO.getOrderType(), inquiryVendorFilterDTO.getOrderField());
        }

        List<InquiryVendor> inquiryVendorList = queryInquiryVendorList(inquiryVendorFilterDTO);

        PageInfo<InquiryVendor> pageInfo = new PageInfo<>(inquiryVendorList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inquiryVendorList));

        return pageVO;
    }

    @Override
    public InquiryVendorVO queryInquiryVendorById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

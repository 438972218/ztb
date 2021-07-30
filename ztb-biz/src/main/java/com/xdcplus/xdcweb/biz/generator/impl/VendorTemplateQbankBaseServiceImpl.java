package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorTemplateQbankMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplateQbank;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateQbankVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorTemplateQbankQuery;
import com.xdcplus.xdcweb.biz.generator.VendorTemplateQbankBaseService;
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
 * 模板题库中间表(VendorTemplateQbank)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:41:00
 */
public class VendorTemplateQbankBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorTemplateQbank, VendorTemplateQbankVO, VendorTemplateQbank, VendorTemplateQbankMapper> implements VendorTemplateQbankBaseService<S, T, E> {

    @Autowired
    protected VendorTemplateQbankMapper vendorTemplateQbankMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorTemplateQbank(VendorTemplateQbankDTO vendorTemplateQbankDTO) {

        VendorTemplateQbank vendorTemplateQbank = vendorTemplateQbankMapper.selectById(vendorTemplateQbankDTO.getId());
        if (ObjectUtil.isNotNull(vendorTemplateQbank)) {
            log.error("saveVendorTemplateQbank() The VendorTemplateQbank already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorTemplateQbank = new VendorTemplateQbank();
        BeanUtil.copyProperties(vendorTemplateQbankDTO, vendorTemplateQbank);
        vendorTemplateQbank.setCreatedTime(DateUtil.current());
        vendorTemplateQbank.setDeleted(0);

        return this.save(vendorTemplateQbank);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorTemplateQbank(VendorTemplateQbankDTO vendorTemplateQbankDTO) {

        VendorTemplateQbank vendorTemplateQbank = this.getById(vendorTemplateQbankDTO.getId());
        if (ObjectUtil.isNull(vendorTemplateQbank)) {
            log.error("updateVendorTemplateQbank() The VendorTemplateQbank does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorTemplateQbankDTO, vendorTemplateQbank);
        vendorTemplateQbank.setUpdatedUser(vendorTemplateQbankDTO.getUpdatedUser());
        vendorTemplateQbank.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorTemplateQbank);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorTemplateQbank> vendorTemplateQbankList) {

        vendorTemplateQbankList.forEach(vendorTemplateQbank -> {
            VendorTemplateQbank vendorTemplateQbankParam = new VendorTemplateQbank();
            vendorTemplateQbankParam.setId(vendorTemplateQbank.getId());
            if (ObjectUtil.isNotNull(vendorTemplateQbank.getId())) {
                vendorTemplateQbank.setId(vendorTemplateQbank.getId());
                vendorTemplateQbank.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorTemplateQbank> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorTemplateQbank::getId, vendorTemplateQbank.getId());
                update(vendorTemplateQbank, lambdaUpdate);
            } else {
                vendorTemplateQbank.setCreatedTime(DateUtil.current());
                vendorTemplateQbank.setDeleted(0);
                save(vendorTemplateQbank);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorTemplateQbankDTO> vendorTemplateQbankDTOList) {

        List<VendorTemplateQbank> vendorTemplateQbankList = BeanUtils.copyProperties(vendorTemplateQbankDTOList, VendorTemplateQbank.class);
        return saveOrUpdateBatch(vendorTemplateQbankList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorTemplateQbankLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorTemplateQbank vendorTemplateQbank = this.getById(id);

        if (ObjectUtil.isNull(vendorTemplateQbank)) {
            log.error("deleteVendorTemplateQbank() The VendorTemplateQbank does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorTemplateQbank.setDeleted(1);

        return this.updateById(vendorTemplateQbank);
    }

    private List<VendorTemplateQbank> queryVendorTemplateQbankList(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO) {
        if (ObjectUtil.isNull(vendorTemplateQbankFilterDTO)) {
            vendorTemplateQbankFilterDTO = new VendorTemplateQbankFilterDTO();
        }
        vendorTemplateQbankFilterDTO.setDeleted(0);
        VendorTemplateQbankQuery vendorTemplateQbankQuery = BeanUtil.copyProperties(vendorTemplateQbankFilterDTO, VendorTemplateQbankQuery.class);

        return vendorTemplateQbankMapper.queryVendorTemplateQbank(vendorTemplateQbankQuery);
    }

    @Override
    public List<VendorTemplateQbankVO> queryVendorTemplateQbankVOList(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO) {
        if (ObjectUtil.isNull(vendorTemplateQbankFilterDTO)) {
            vendorTemplateQbankFilterDTO = new VendorTemplateQbankFilterDTO();
        }
        vendorTemplateQbankFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorTemplateQbankList(vendorTemplateQbankFilterDTO));
    }

    @Override
    public PageVO<VendorTemplateQbankVO> queryVendorTemplateQbank(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO) {
        if (ObjectUtil.isNull(vendorTemplateQbankFilterDTO)) {
            vendorTemplateQbankFilterDTO = new VendorTemplateQbankFilterDTO();
        }
        vendorTemplateQbankFilterDTO.setDeleted(0);
        PageVO<VendorTemplateQbankVO> pageVO = new PageVO<>();

        if (vendorTemplateQbankFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorTemplateQbankFilterDTO.getCurrentPage(), vendorTemplateQbankFilterDTO.getPageSize(),
                    vendorTemplateQbankFilterDTO.getOrderType(), vendorTemplateQbankFilterDTO.getOrderField());
        }

        List<VendorTemplateQbank> vendorTemplateQbankList = queryVendorTemplateQbankList(vendorTemplateQbankFilterDTO);

        PageInfo<VendorTemplateQbank> pageInfo = new PageInfo<>(vendorTemplateQbankList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorTemplateQbankList));

        return pageVO;
    }

    @Override
    public VendorTemplateQbankVO queryVendorTemplateQbankById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

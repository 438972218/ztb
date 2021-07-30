package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQuestionBankMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionBank;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionBankDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionBankFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionBankVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuestionBankQuery;
import com.xdcplus.xdcweb.biz.generator.VendorQuestionBankBaseService;
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
 * 模板题库表(VendorQuestionBank)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:50
 */
public class VendorQuestionBankBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorQuestionBank, VendorQuestionBankVO, VendorQuestionBank, VendorQuestionBankMapper> implements VendorQuestionBankBaseService<S, T, E> {

    @Autowired
    protected VendorQuestionBankMapper vendorQuestionBankMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorQuestionBank(VendorQuestionBankDTO vendorQuestionBankDTO) {

        VendorQuestionBank vendorQuestionBank = vendorQuestionBankMapper.selectById(vendorQuestionBankDTO.getId());
        if (ObjectUtil.isNotNull(vendorQuestionBank)) {
            log.error("saveVendorQuestionBank() The VendorQuestionBank already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorQuestionBank = new VendorQuestionBank();
        BeanUtil.copyProperties(vendorQuestionBankDTO, vendorQuestionBank);
        vendorQuestionBank.setCreatedTime(DateUtil.current());
        vendorQuestionBank.setDeleted(0);

        return this.save(vendorQuestionBank);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorQuestionBank(VendorQuestionBankDTO vendorQuestionBankDTO) {

        VendorQuestionBank vendorQuestionBank = this.getById(vendorQuestionBankDTO.getId());
        if (ObjectUtil.isNull(vendorQuestionBank)) {
            log.error("updateVendorQuestionBank() The VendorQuestionBank does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorQuestionBankDTO, vendorQuestionBank);
        vendorQuestionBank.setUpdatedUser(vendorQuestionBankDTO.getUpdatedUser());
        vendorQuestionBank.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorQuestionBank);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorQuestionBank> vendorQuestionBankList) {

        vendorQuestionBankList.forEach(vendorQuestionBank -> {
            VendorQuestionBank vendorQuestionBankParam = new VendorQuestionBank();
            vendorQuestionBankParam.setId(vendorQuestionBank.getId());
            if (ObjectUtil.isNotNull(vendorQuestionBank.getId())) {
                vendorQuestionBank.setId(vendorQuestionBank.getId());
                vendorQuestionBank.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorQuestionBank> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorQuestionBank::getId, vendorQuestionBank.getId());
                update(vendorQuestionBank, lambdaUpdate);
            } else {
                vendorQuestionBank.setCreatedTime(DateUtil.current());
                vendorQuestionBank.setDeleted(0);
                save(vendorQuestionBank);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorQuestionBankDTO> vendorQuestionBankDTOList) {

        List<VendorQuestionBank> vendorQuestionBankList = BeanUtils.copyProperties(vendorQuestionBankDTOList, VendorQuestionBank.class);
        return saveOrUpdateBatch(vendorQuestionBankList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorQuestionBankLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorQuestionBank vendorQuestionBank = this.getById(id);

        if (ObjectUtil.isNull(vendorQuestionBank)) {
            log.error("deleteVendorQuestionBank() The VendorQuestionBank does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorQuestionBank.setDeleted(1);

        return this.updateById(vendorQuestionBank);
    }

    private List<VendorQuestionBank> queryVendorQuestionBankList(VendorQuestionBankFilterDTO vendorQuestionBankFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionBankFilterDTO)) {
            vendorQuestionBankFilterDTO = new VendorQuestionBankFilterDTO();
        }
        vendorQuestionBankFilterDTO.setDeleted(0);
        VendorQuestionBankQuery vendorQuestionBankQuery = BeanUtil.copyProperties(vendorQuestionBankFilterDTO, VendorQuestionBankQuery.class);

        return vendorQuestionBankMapper.queryVendorQuestionBank(vendorQuestionBankQuery);
    }

    @Override
    public List<VendorQuestionBankVO> queryVendorQuestionBankVOList(VendorQuestionBankFilterDTO vendorQuestionBankFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionBankFilterDTO)) {
            vendorQuestionBankFilterDTO = new VendorQuestionBankFilterDTO();
        }
        vendorQuestionBankFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorQuestionBankList(vendorQuestionBankFilterDTO));
    }

    @Override
    public PageVO<VendorQuestionBankVO> queryVendorQuestionBank(VendorQuestionBankFilterDTO vendorQuestionBankFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionBankFilterDTO)) {
            vendorQuestionBankFilterDTO = new VendorQuestionBankFilterDTO();
        }
        vendorQuestionBankFilterDTO.setDeleted(0);
        PageVO<VendorQuestionBankVO> pageVO = new PageVO<>();

        if (vendorQuestionBankFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorQuestionBankFilterDTO.getCurrentPage(), vendorQuestionBankFilterDTO.getPageSize(),
                    vendorQuestionBankFilterDTO.getOrderType(), vendorQuestionBankFilterDTO.getOrderField());
        }

        List<VendorQuestionBank> vendorQuestionBankList = queryVendorQuestionBankList(vendorQuestionBankFilterDTO);

        PageInfo<VendorQuestionBank> pageInfo = new PageInfo<>(vendorQuestionBankList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorQuestionBankList));

        return pageVO;
    }

    @Override
    public VendorQuestionBankVO queryVendorQuestionBankById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteIns;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsQuery;
import com.xdcplus.xdcweb.biz.generator.VendorSiteInsBaseService;
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
 * 供应商现场考察表(VendorSiteIns)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:56
 */
public class VendorSiteInsBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorSiteIns, VendorSiteInsVO, VendorSiteIns, VendorSiteInsMapper> implements VendorSiteInsBaseService<S, T, E> {

    @Autowired
    protected VendorSiteInsMapper vendorSiteInsMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorSiteIns(VendorSiteInsDTO vendorSiteInsDTO) {

        VendorSiteIns vendorSiteIns = vendorSiteInsMapper.selectById(vendorSiteInsDTO.getId());
        if (ObjectUtil.isNotNull(vendorSiteIns)) {
            log.error("saveVendorSiteIns() The VendorSiteIns already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorSiteIns = new VendorSiteIns();
        BeanUtil.copyProperties(vendorSiteInsDTO, vendorSiteIns);
        vendorSiteIns.setCreatedTime(DateUtil.current());
        vendorSiteIns.setDeleted(0);

        return this.save(vendorSiteIns);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorSiteIns(VendorSiteInsDTO vendorSiteInsDTO) {

        VendorSiteIns vendorSiteIns = this.getById(vendorSiteInsDTO.getId());
        if (ObjectUtil.isNull(vendorSiteIns)) {
            log.error("updateVendorSiteIns() The VendorSiteIns does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorSiteInsDTO, vendorSiteIns);
        vendorSiteIns.setUpdatedUser(vendorSiteInsDTO.getUpdatedUser());
        vendorSiteIns.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorSiteIns);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorSiteIns> vendorSiteInsList) {

        vendorSiteInsList.forEach(vendorSiteIns -> {
            VendorSiteIns vendorSiteInsParam = new VendorSiteIns();
            vendorSiteInsParam.setId(vendorSiteIns.getId());
            if (ObjectUtil.isNotNull(vendorSiteIns.getId())) {
                vendorSiteIns.setId(vendorSiteIns.getId());
                vendorSiteIns.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorSiteIns> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorSiteIns::getId, vendorSiteIns.getId());
                update(vendorSiteIns, lambdaUpdate);
            } else {
                vendorSiteIns.setCreatedTime(DateUtil.current());
                vendorSiteIns.setDeleted(0);
                save(vendorSiteIns);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsDTO> vendorSiteInsDTOList) {

        List<VendorSiteIns> vendorSiteInsList = BeanUtils.copyProperties(vendorSiteInsDTOList, VendorSiteIns.class);
        return saveOrUpdateBatch(vendorSiteInsList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorSiteInsLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorSiteIns vendorSiteIns = this.getById(id);

        if (ObjectUtil.isNull(vendorSiteIns)) {
            log.error("deleteVendorSiteIns() The VendorSiteIns does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorSiteIns.setDeleted(1);

        return this.updateById(vendorSiteIns);
    }

    private List<VendorSiteIns> queryVendorSiteInsList(VendorSiteInsFilterDTO vendorSiteInsFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsFilterDTO)) {
            vendorSiteInsFilterDTO = new VendorSiteInsFilterDTO();
        }
        vendorSiteInsFilterDTO.setDeleted(0);
        VendorSiteInsQuery vendorSiteInsQuery = BeanUtil.copyProperties(vendorSiteInsFilterDTO, VendorSiteInsQuery.class);

        return vendorSiteInsMapper.queryVendorSiteIns(vendorSiteInsQuery);
    }

    @Override
    public List<VendorSiteInsVO> queryVendorSiteInsVOList(VendorSiteInsFilterDTO vendorSiteInsFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsFilterDTO)) {
            vendorSiteInsFilterDTO = new VendorSiteInsFilterDTO();
        }
        vendorSiteInsFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorSiteInsList(vendorSiteInsFilterDTO));
    }

    @Override
    public PageVO<VendorSiteInsVO> queryVendorSiteIns(VendorSiteInsFilterDTO vendorSiteInsFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsFilterDTO)) {
            vendorSiteInsFilterDTO = new VendorSiteInsFilterDTO();
        }
        vendorSiteInsFilterDTO.setDeleted(0);
        PageVO<VendorSiteInsVO> pageVO = new PageVO<>();

        if (vendorSiteInsFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorSiteInsFilterDTO.getCurrentPage(), vendorSiteInsFilterDTO.getPageSize(),
                    vendorSiteInsFilterDTO.getOrderType(), vendorSiteInsFilterDTO.getOrderField());
        }

        List<VendorSiteIns> vendorSiteInsList = queryVendorSiteInsList(vendorSiteInsFilterDTO);

        PageInfo<VendorSiteIns> pageInfo = new PageInfo<>(vendorSiteInsList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorSiteInsList));

        return pageVO;
    }

    @Override
    public VendorSiteInsVO queryVendorSiteInsById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

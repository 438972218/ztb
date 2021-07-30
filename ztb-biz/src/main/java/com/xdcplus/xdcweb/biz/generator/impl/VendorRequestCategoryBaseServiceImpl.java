package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorRequestCategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCategory;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCategoryVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorRequestCategoryQuery;
import com.xdcplus.xdcweb.biz.generator.VendorRequestCategoryBaseService;
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
 * 供应商注册单品类(VendorRequestCategory)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:54
 */
public class VendorRequestCategoryBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorRequestCategory, VendorRequestCategoryVO, VendorRequestCategory, VendorRequestCategoryMapper> implements VendorRequestCategoryBaseService<S, T, E> {

    @Autowired
    protected VendorRequestCategoryMapper vendorRequestCategoryMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorRequestCategory(VendorRequestCategoryDTO vendorRequestCategoryDTO) {

        VendorRequestCategory vendorRequestCategory = vendorRequestCategoryMapper.selectById(vendorRequestCategoryDTO.getId());
        if (ObjectUtil.isNotNull(vendorRequestCategory)) {
            log.error("saveVendorRequestCategory() The VendorRequestCategory already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorRequestCategory = new VendorRequestCategory();
        BeanUtil.copyProperties(vendorRequestCategoryDTO, vendorRequestCategory);
        vendorRequestCategory.setCreatedTime(DateUtil.current());
        vendorRequestCategory.setDeleted(0);

        return this.save(vendorRequestCategory);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorRequestCategory(VendorRequestCategoryDTO vendorRequestCategoryDTO) {

        VendorRequestCategory vendorRequestCategory = this.getById(vendorRequestCategoryDTO.getId());
        if (ObjectUtil.isNull(vendorRequestCategory)) {
            log.error("updateVendorRequestCategory() The VendorRequestCategory does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorRequestCategoryDTO, vendorRequestCategory);
        vendorRequestCategory.setUpdatedUser(vendorRequestCategoryDTO.getUpdatedUser());
        vendorRequestCategory.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorRequestCategory);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorRequestCategory> vendorRequestCategoryList) {

        vendorRequestCategoryList.forEach(vendorRequestCategory -> {
            VendorRequestCategory vendorRequestCategoryParam = new VendorRequestCategory();
            vendorRequestCategoryParam.setId(vendorRequestCategory.getId());
            if (ObjectUtil.isNotNull(vendorRequestCategory.getId())) {
                vendorRequestCategory.setId(vendorRequestCategory.getId());
                vendorRequestCategory.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorRequestCategory> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorRequestCategory::getId, vendorRequestCategory.getId());
                update(vendorRequestCategory, lambdaUpdate);
            } else {
                vendorRequestCategory.setCreatedTime(DateUtil.current());
                vendorRequestCategory.setDeleted(0);
                save(vendorRequestCategory);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorRequestCategoryDTO> vendorRequestCategoryDTOList) {

        List<VendorRequestCategory> vendorRequestCategoryList = BeanUtils.copyProperties(vendorRequestCategoryDTOList, VendorRequestCategory.class);
        return saveOrUpdateBatch(vendorRequestCategoryList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorRequestCategoryLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorRequestCategory vendorRequestCategory = this.getById(id);

        if (ObjectUtil.isNull(vendorRequestCategory)) {
            log.error("deleteVendorRequestCategory() The VendorRequestCategory does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorRequestCategory.setDeleted(1);

        return this.updateById(vendorRequestCategory);
    }

    private List<VendorRequestCategory> queryVendorRequestCategoryList(VendorRequestCategoryFilterDTO vendorRequestCategoryFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestCategoryFilterDTO)) {
            vendorRequestCategoryFilterDTO = new VendorRequestCategoryFilterDTO();
        }
        vendorRequestCategoryFilterDTO.setDeleted(0);
        VendorRequestCategoryQuery vendorRequestCategoryQuery = BeanUtil.copyProperties(vendorRequestCategoryFilterDTO, VendorRequestCategoryQuery.class);

        return vendorRequestCategoryMapper.queryVendorRequestCategory(vendorRequestCategoryQuery);
    }

    @Override
    public List<VendorRequestCategoryVO> queryVendorRequestCategoryVOList(VendorRequestCategoryFilterDTO vendorRequestCategoryFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestCategoryFilterDTO)) {
            vendorRequestCategoryFilterDTO = new VendorRequestCategoryFilterDTO();
        }
        vendorRequestCategoryFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorRequestCategoryList(vendorRequestCategoryFilterDTO));
    }

    @Override
    public PageVO<VendorRequestCategoryVO> queryVendorRequestCategory(VendorRequestCategoryFilterDTO vendorRequestCategoryFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestCategoryFilterDTO)) {
            vendorRequestCategoryFilterDTO = new VendorRequestCategoryFilterDTO();
        }
        vendorRequestCategoryFilterDTO.setDeleted(0);
        PageVO<VendorRequestCategoryVO> pageVO = new PageVO<>();

        if (vendorRequestCategoryFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorRequestCategoryFilterDTO.getCurrentPage(), vendorRequestCategoryFilterDTO.getPageSize(),
                    vendorRequestCategoryFilterDTO.getOrderType(), vendorRequestCategoryFilterDTO.getOrderField());
        }

        List<VendorRequestCategory> vendorRequestCategoryList = queryVendorRequestCategoryList(vendorRequestCategoryFilterDTO);

        PageInfo<VendorRequestCategory> pageInfo = new PageInfo<>(vendorRequestCategoryList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorRequestCategoryList));

        return pageVO;
    }

    @Override
    public VendorRequestCategoryVO queryVendorRequestCategoryById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

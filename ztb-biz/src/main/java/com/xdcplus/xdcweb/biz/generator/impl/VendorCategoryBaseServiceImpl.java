package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorCategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCategory;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCategoryVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorCategoryQuery;
import com.xdcplus.xdcweb.biz.generator.VendorCategoryBaseService;
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
 * 供应商品类(VendorCategory)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:43
 */
public class VendorCategoryBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorCategory, VendorCategoryVO, VendorCategory, VendorCategoryMapper> implements VendorCategoryBaseService<S, T, E> {

    @Autowired
    protected VendorCategoryMapper vendorCategoryMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorCategory(VendorCategoryDTO vendorCategoryDTO) {

        VendorCategory vendorCategory = vendorCategoryMapper.selectById(vendorCategoryDTO.getId());
        if (ObjectUtil.isNotNull(vendorCategory)) {
            log.error("saveVendorCategory() The VendorCategory already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorCategory = new VendorCategory();
        BeanUtil.copyProperties(vendorCategoryDTO, vendorCategory);
        vendorCategory.setCreatedTime(DateUtil.current());
        vendorCategory.setDeleted(0);

        return this.save(vendorCategory);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorCategory(VendorCategoryDTO vendorCategoryDTO) {

        VendorCategory vendorCategory = this.getById(vendorCategoryDTO.getId());
        if (ObjectUtil.isNull(vendorCategory)) {
            log.error("updateVendorCategory() The VendorCategory does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorCategoryDTO, vendorCategory);
        vendorCategory.setUpdatedUser(vendorCategoryDTO.getUpdatedUser());
        vendorCategory.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorCategory);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorCategory> vendorCategoryList) {

        vendorCategoryList.forEach(vendorCategory -> {
            VendorCategory vendorCategoryParam = new VendorCategory();
            vendorCategoryParam.setId(vendorCategory.getId());
            if (ObjectUtil.isNotNull(vendorCategory.getId())) {
                vendorCategory.setId(vendorCategory.getId());
                vendorCategory.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorCategory> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorCategory::getId, vendorCategory.getId());
                update(vendorCategory, lambdaUpdate);
            } else {
                vendorCategory.setCreatedTime(DateUtil.current());
                vendorCategory.setDeleted(0);
                save(vendorCategory);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorCategoryDTO> vendorCategoryDTOList) {

        List<VendorCategory> vendorCategoryList = BeanUtils.copyProperties(vendorCategoryDTOList, VendorCategory.class);
        return saveOrUpdateBatch(vendorCategoryList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorCategoryLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorCategory vendorCategory = this.getById(id);

        if (ObjectUtil.isNull(vendorCategory)) {
            log.error("deleteVendorCategory() The VendorCategory does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorCategory.setDeleted(1);

        return this.updateById(vendorCategory);
    }

    private List<VendorCategory> queryVendorCategoryList(VendorCategoryFilterDTO vendorCategoryFilterDTO) {
        if (ObjectUtil.isNull(vendorCategoryFilterDTO)) {
            vendorCategoryFilterDTO = new VendorCategoryFilterDTO();
        }
        vendorCategoryFilterDTO.setDeleted(0);
        VendorCategoryQuery vendorCategoryQuery = BeanUtil.copyProperties(vendorCategoryFilterDTO, VendorCategoryQuery.class);

        return vendorCategoryMapper.queryVendorCategory(vendorCategoryQuery);
    }

    @Override
    public List<VendorCategoryVO> queryVendorCategoryVOList(VendorCategoryFilterDTO vendorCategoryFilterDTO) {
        if (ObjectUtil.isNull(vendorCategoryFilterDTO)) {
            vendorCategoryFilterDTO = new VendorCategoryFilterDTO();
        }
        vendorCategoryFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorCategoryList(vendorCategoryFilterDTO));
    }

    @Override
    public PageVO<VendorCategoryVO> queryVendorCategory(VendorCategoryFilterDTO vendorCategoryFilterDTO) {
        if (ObjectUtil.isNull(vendorCategoryFilterDTO)) {
            vendorCategoryFilterDTO = new VendorCategoryFilterDTO();
        }
        vendorCategoryFilterDTO.setDeleted(0);
        PageVO<VendorCategoryVO> pageVO = new PageVO<>();

        if (vendorCategoryFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorCategoryFilterDTO.getCurrentPage(), vendorCategoryFilterDTO.getPageSize(),
                    vendorCategoryFilterDTO.getOrderType(), vendorCategoryFilterDTO.getOrderField());
        }

        List<VendorCategory> vendorCategoryList = queryVendorCategoryList(vendorCategoryFilterDTO);

        PageInfo<VendorCategory> pageInfo = new PageInfo<>(vendorCategoryList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorCategoryList));

        return pageVO;
    }

    @Override
    public VendorCategoryVO queryVendorCategoryById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

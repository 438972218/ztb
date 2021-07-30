package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.CategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Category;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CategoryVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.CategoryQuery;
import com.xdcplus.xdcweb.biz.generator.CategoryBaseService;
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
 * 品类管理(Category)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:06
 */
public class CategoryBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<Category, CategoryVO, Category, CategoryMapper> implements CategoryBaseService<S, T, E> {

    @Autowired
    protected CategoryMapper categoryMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveCategory(CategoryDTO categoryDTO) {

        Category category = categoryMapper.selectById(categoryDTO.getId());
        if (ObjectUtil.isNotNull(category)) {
            log.error("saveCategory() The Category already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        category = new Category();
        BeanUtil.copyProperties(categoryDTO, category);
        category.setCreatedTime(DateUtil.current());
        category.setDeleted(0);

        return this.save(category);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateCategory(CategoryDTO categoryDTO) {

        Category category = this.getById(categoryDTO.getId());
        if (ObjectUtil.isNull(category)) {
            log.error("updateCategory() The Category does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(categoryDTO, category);
        category.setUpdatedUser(categoryDTO.getUpdatedUser());
        category.setUpdatedTime(DateUtil.current());

        return this.updateById(category);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<Category> categoryList) {

        categoryList.forEach(category -> {
            Category categoryParam = new Category();
            categoryParam.setId(category.getId());
            if (ObjectUtil.isNotNull(category.getId())) {
                category.setId(category.getId());
                category.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<Category> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(Category::getId, category.getId());
                update(category, lambdaUpdate);
            } else {
                category.setCreatedTime(DateUtil.current());
                category.setDeleted(0);
                save(category);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<CategoryDTO> categoryDTOList) {

        List<Category> categoryList = BeanUtils.copyProperties(categoryDTOList, Category.class);
        return saveOrUpdateBatch(categoryList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteCategoryLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Category category = this.getById(id);

        if (ObjectUtil.isNull(category)) {
            log.error("deleteCategory() The Category does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        category.setDeleted(1);

        return this.updateById(category);
    }

    private List<Category> queryCategoryList(CategoryFilterDTO categoryFilterDTO) {
        if (ObjectUtil.isNull(categoryFilterDTO)) {
            categoryFilterDTO = new CategoryFilterDTO();
        }
        categoryFilterDTO.setDeleted(0);
        CategoryQuery categoryQuery = BeanUtil.copyProperties(categoryFilterDTO, CategoryQuery.class);

        return categoryMapper.queryCategory(categoryQuery);
    }

    @Override
    public List<CategoryVO> queryCategoryVOList(CategoryFilterDTO categoryFilterDTO) {
        if (ObjectUtil.isNull(categoryFilterDTO)) {
            categoryFilterDTO = new CategoryFilterDTO();
        }
        categoryFilterDTO.setDeleted(0);
        return this.objectConversion(queryCategoryList(categoryFilterDTO));
    }

    @Override
    public PageVO<CategoryVO> queryCategory(CategoryFilterDTO categoryFilterDTO) {
        if (ObjectUtil.isNull(categoryFilterDTO)) {
            categoryFilterDTO = new CategoryFilterDTO();
        }
        categoryFilterDTO.setDeleted(0);
        PageVO<CategoryVO> pageVO = new PageVO<>();

        if (categoryFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(categoryFilterDTO.getCurrentPage(), categoryFilterDTO.getPageSize(),
                    categoryFilterDTO.getOrderType(), categoryFilterDTO.getOrderField());
        }

        List<Category> categoryList = queryCategoryList(categoryFilterDTO);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(categoryList));

        return pageVO;
    }

    @Override
    public CategoryVO queryCategoryById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}

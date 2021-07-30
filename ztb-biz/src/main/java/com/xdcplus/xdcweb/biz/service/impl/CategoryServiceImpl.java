package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryFilterDTO;
import com.xdcplus.xdcweb.biz.generator.impl.CategoryBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.CategoryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Category;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CategoryVO;
import com.xdcplus.xdcweb.biz.service.CategoryService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 品类管理(Category)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:49:31
 */
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends CategoryBaseServiceImpl<Category, CategoryVO, Category, CategoryMapper> implements CategoryService {

    @Override
    public List<CategoryVO> queryTreeCategory(CategoryFilterDTO categoryFilterDTO) {
        QueryWrapper q=new QueryWrapper<Category>();
        q.isNull("p_id");
        List<Category> nodes = categoryMapper.selectList((q));
        Assert.notNull(nodes, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        List<CategoryVO> l=new ArrayList<>();
        nodes.forEach(n-> l.add(recursiveTree(n.getId())));
        return l;
    }

    private CategoryVO recursiveTree(Long id) {
        Category category = categoryMapper.selectById(id);
        CategoryVO categoryVO= BeanUtil.copyProperties(category,CategoryVO.class);
        List<Category> childTreeNodes = categoryMapper.selectList((new QueryWrapper<Category>()).eq("p_id",id));
        //遍历子节点
        for (Category child : childTreeNodes) {
            CategoryVO n = recursiveTree(child.getId()); //递归
            if (categoryVO.getChildren() != null) {
                categoryVO.getChildren().add(n);
            } else {
                List<CategoryVO> cDTOS = new ArrayList<>();
                cDTOS.add(n);
                categoryVO.setChildren(cDTOS);
            }
        }
        return categoryVO;
    }

}

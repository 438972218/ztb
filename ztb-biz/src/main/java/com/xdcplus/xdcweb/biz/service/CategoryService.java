package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryFilterDTO;
import com.xdcplus.xdcweb.biz.generator.CategoryBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Category;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CategoryVO;

import java.util.List;


/**
 * 品类管理(Category)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:49:31
 */
public interface CategoryService extends CategoryBaseService<Category, CategoryVO, Category> {

    /**
     * 查询品类管理树状(Category)
     *
     * @param categoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <CategoryVO>} 状态信息
     */
    List<CategoryVO> queryTreeCategory(CategoryFilterDTO categoryFilterDTO);


}

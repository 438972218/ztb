package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Category;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CategoryVO;

import java.util.List;

/**
 * 品类管理(Category)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:49:29
 */
public interface CategoryBaseService<S, T, E> extends BaseService<Category, CategoryVO, Category> {

    /**
     * 添加品类管理(Category)
     *
     * @param categoryDTO 品类管理(CategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveCategory(CategoryDTO categoryDTO);

    /**
     * 修改品类管理(Category)
     *
     * @param categoryDTO 品类管理(CategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateCategory(CategoryDTO categoryDTO);

    /**
     * 批量保存或更新品类管理(Category)
     *
     * @param categoryList 品类管理(CategoryList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<Category> categoryList);

    /**
     * 批量保存或更新品类管理(CategoryDTOList)
     *
     * @param categoryDTOList 品类管理(CategoryDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<CategoryDTO> categoryDTOList);

    /**
     * 删除品类管理(Category)
     *
     * @param id 品类管理(Category)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteCategoryLogical(Long id);

    /**
     * 查询品类管理(Category)
     *
     * @param categoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<CategoryVO>} 状态信息
     */
    List<CategoryVO> queryCategoryVOList(CategoryFilterDTO categoryFilterDTO);

    /**
     * 查询品类管理(Category)
     *
     * @param categoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<CategoryVO>} 状态信息
     */
    PageVO<CategoryVO> queryCategory(CategoryFilterDTO categoryFilterDTO);

    /**
     * 查询一个
     *
     * @param id 品类管理(Category)主键
     * @return {@link CategoryVO} 品类管理(Category)信息
     */
    CategoryVO queryCategoryById(Long id);


}

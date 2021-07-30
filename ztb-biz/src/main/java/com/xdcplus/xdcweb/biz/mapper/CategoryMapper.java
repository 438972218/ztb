package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Category;
import com.xdcplus.xdcweb.biz.common.pojo.query.CategoryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品类管理(Category)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-02 10:28:10
 */
public interface CategoryMapper extends IBaseMapper<Category> {

    /**
     * 查询品类管理(Category)
     *
     * @param categoryQuery 品类管理(Category)查询
     * @return {@link List<Category>}
     */
    List<Category> queryCategory(CategoryQuery categoryQuery);

}

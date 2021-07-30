package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Dictionary;
import com.xdcplus.xdcweb.biz.common.pojo.query.DictionaryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典管理(Dictionary)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-02 10:28:07
 */
public interface DictionaryMapper extends IBaseMapper<Dictionary> {

    /**
     * 查询字典管理(Dictionary)
     *
     * @param dictionaryQuery 字典管理(Dictionary)查询
     * @return {@link List<Dictionary>}
     */
    List<Dictionary> queryDictionary(DictionaryQuery dictionaryQuery);

}

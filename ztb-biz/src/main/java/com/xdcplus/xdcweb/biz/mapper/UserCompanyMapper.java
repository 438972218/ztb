package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCompany;
import com.xdcplus.xdcweb.biz.common.pojo.query.UserCompanyQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户公司中间表(UserCompany)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-22 15:38:39
 */
public interface UserCompanyMapper extends IBaseMapper<UserCompany> {

    /**
     * 查询用户公司中间表(UserCompany)
     *
     * @param userCompanyQuery 用户公司中间表(UserCompany)查询
     * @return {@link List<UserCompany>}
     */
    List<UserCompany> queryUserCompany(UserCompanyQuery userCompanyQuery);

}

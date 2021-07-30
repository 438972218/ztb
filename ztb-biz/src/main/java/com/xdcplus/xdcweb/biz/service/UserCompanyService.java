package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.UserCompanyBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCompany;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCompanyVO;


/**
 * 用户公司中间表(UserCompany)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:43
 */
public interface UserCompanyService extends UserCompanyBaseService<UserCompany, UserCompanyVO, UserCompany> {

    /**
     * 删除用户公司中间表(UserCompany)
     *
     * @param id 用户表(UserCompany)主键
     * @return {@link int} 是否成功
     */
    int deleteUserCompanyByUserId(Long id);

}

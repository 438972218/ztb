package com.xdcplus.xdcweb.biz.service;

import com.xdcplus.xdcweb.biz.generator.UserCategoryBaseService;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCategory;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCategoryVO;


/**
 * 用户品类中间表(UserCategory)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:42
 */
public interface UserCategoryService extends UserCategoryBaseService<UserCategory, UserCategoryVO, UserCategory> {

    /**
     * 删除用户品类中间表(UserCategory)
     *
     * @param id 用户表(UserCategory)主键
     * @return {@link int} 是否成功
     */
    int deleteUserCategoryByUserId(Long id);

}

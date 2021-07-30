package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.UserCompanyBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserCompanyMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCompany;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCompanyVO;
import com.xdcplus.xdcweb.biz.service.UserCompanyService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户公司中间表(UserCompany)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:44
 */
@Slf4j
@Service("userCompanyService")
public class UserCompanyServiceImpl extends UserCompanyBaseServiceImpl<UserCompany, UserCompanyVO, UserCompany, UserCompanyMapper> implements UserCompanyService {


    @Override
    public int deleteUserCompanyByUserId(Long id) {
        return userCompanyMapper.delete(new QueryWrapper<UserCompany>().eq("user_id",id));
    }
}

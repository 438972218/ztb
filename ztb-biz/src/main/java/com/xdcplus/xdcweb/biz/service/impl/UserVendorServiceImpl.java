package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.UserVendorBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserVendorMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserVendorVO;
import com.xdcplus.xdcweb.biz.service.UserVendorService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户供应商中间表(UserVendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:46
 */
@Slf4j
@Service("userVendorService")
public class UserVendorServiceImpl extends UserVendorBaseServiceImpl<UserVendor, UserVendorVO, UserVendor, UserVendorMapper> implements UserVendorService {

    @Override
    public int deleteUserVendorByUserId(Long id) {
        return userVendorMapper.delete(new QueryWrapper<UserVendor>().eq("user_id",id));
    }

}

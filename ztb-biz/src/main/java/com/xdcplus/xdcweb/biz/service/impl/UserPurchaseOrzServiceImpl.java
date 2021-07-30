package com.xdcplus.xdcweb.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdcplus.xdcweb.biz.generator.impl.UserPurchaseOrzBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.UserPurchaseOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserPurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserPurchaseOrzVO;
import com.xdcplus.xdcweb.biz.service.UserPurchaseOrzService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户采购组织中间表(UserPurchaseOrz)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:44
 */
@Slf4j
@Service("userPurchaseOrzService")
public class UserPurchaseOrzServiceImpl extends UserPurchaseOrzBaseServiceImpl<UserPurchaseOrz, UserPurchaseOrzVO, UserPurchaseOrz, UserPurchaseOrzMapper> implements UserPurchaseOrzService {


    @Override
    public int deleteUserPurchaseOrzByUserId(Long id) {
        return userPurchaseOrzMapper.delete(new QueryWrapper<UserPurchaseOrz>().eq("user_id",id));
    }
}

package com.xdcplus.xdcweb.biz.common.permission;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.xdcweb.biz.common.config.UrlConfig;
import com.xdcplus.xdcweb.biz.common.constants.UrlConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Slf4j
public class PceUserResponseUtils {

    private static RestTemplateUtils restTemplateUtils = SpringUtil.getBean(RestTemplateUtils.class);
    private static UrlConfig urlPceConfig = SpringUtil.getBean(UrlConfig.class);

    public static UserVO queryById(@Nullable Long id) {
        return queryById(id, NumberConstant.ZERO);
    }

    private static UserVO queryById(@NotNull Long id, int retryCount) {

        String url = getUrl(UrlConstant.PERM_CONTROL_ENGINE_IP, UrlConstant.PERM_CONTROL_ENGINE_PORT,
                urlPceConfig.getPath().getPceSysUserQueryByUserId());
        if (ObjectUtil.isNull(url)) {
            log.error("PceUserResponseUtils.queryById() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            ResponseVO responseVO = restTemplateUtils.get(url, ResponseVO.class, id);
            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                log.error("PceUserResponseUtils.queryById() get api user failed");
                throw new InvException(ResponseEnum.API_USER_SELECT_FAIL);
            }

            String data = JSONObject.toJSONString(responseVO.getData());
            UserVO userVO = JSONObject.parseObject(data, UserVO.class);

            if (ObjectUtil.isNull(userVO)) {
                log.error("queryById() user found failed");
                throw new InvException(ResponseEnum.API_USER_SELECT_FAIL);
            }

            return userVO;

        } catch (Exception e) {
            log.error("PceRoleResponseUtils.queryByUserId {}", e.getMessage());
            retryCount++;
            queryById(id, retryCount);
        }

        throw new InvException(ResponseEnum.API_USER_SELECT_FAIL);
//        return null;
    }

    /**
     * 查询url
     *
     * @param ipType   ip类型
     * @param portType 端口类型
     * @return {@link String}  URL
     */
    private static String getUrl(String ipType, String portType, String path) {
        return urlPceConfig.gainWholeUrl(ipType, portType, path);
    }


}

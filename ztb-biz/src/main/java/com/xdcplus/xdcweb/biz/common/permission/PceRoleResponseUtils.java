package com.xdcplus.xdcweb.biz.common.permission;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.xdcweb.biz.common.config.UrlConfig;
import com.xdcplus.xdcweb.biz.common.constants.UrlConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.SysRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PceRoleResponseUtils {

    private static RestTemplateUtils restTemplateUtils = SpringUtil.getBean(RestTemplateUtils.class);
    private static UrlConfig urlPceConfig = SpringUtil.getBean(UrlConfig.class);

    public static List<Long> queryByUserId(@Nullable Long id) {
        return queryByUserId(id, NumberConstant.ZERO);
    }

    private static List<Long> queryByUserId(@NotNull Long id, int retryCount) {

        String url = getUrl(UrlConstant.PERM_CONTROL_ENGINE_IP, UrlConstant.PERM_CONTROL_ENGINE_PORT,
                urlPceConfig.getPath().getPceSysRoleQueryByUserId());
        if (ObjectUtil.isNull(url)) {
            log.error("PceRoleResponseUtils.queryByUserId() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            ResponseVO responseVO = restTemplateUtils.get(url, ResponseVO.class, id);
            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                log.error("PceRoleResponseUtils.queryByUserId() get api roleIds failed");
                throw new InvException(ResponseEnum.API_ROLE_SELECT_FAIL);
            }

            String data = JSONObject.toJSONString(responseVO.getData());

            List<SysRoleVO> sysRoleVOS = JSON.parseArray(data, SysRoleVO.class);
            if (CollUtil.isEmpty(sysRoleVOS)) {
                log.error("queryByUserId() role found failed");
                throw new InvException(ResponseEnum.API_ROLE_SELECT_FAIL);
            }
            List<Long> roleIds = sysRoleVOS.stream().map(s -> s.getId()).collect(Collectors.toList());

            if (CollUtil.isEmpty(roleIds)) {
                log.error("PceRoleResponseUtils.queryByUserId() get api roleIds failed");
                throw new InvException(ResponseEnum.API_ROLE_SELECT_FAIL);
            }

            if (CollUtil.isNotEmpty(roleIds)) {
                return roleIds;
            }
            retryCount++;
            queryByUserId(id, retryCount);
        } catch (Exception e) {
            log.error("PceRoleResponseUtils.queryByUserId {}", e.getMessage());
            retryCount++;
            queryByUserId(id, retryCount);
        }

        throw new InvException(ResponseEnum.API_ROLE_SELECT_FAIL);
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

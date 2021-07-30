package com.xdcplus.xdcweb.biz.common.permission;

import cn.hutool.core.collection.CollectionUtil;
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
import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.SysRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
public class PceCompanyResponseUtils {

    private static RestTemplateUtils restTemplateUtils = SpringUtil.getBean(RestTemplateUtils.class);
    private static UrlConfig urlConfig = SpringUtil.getBean(UrlConfig.class);

    public static CompanyVO queryById(@Nullable Long id) {
        return queryById(id, NumberConstant.ZERO);
    }

    private static CompanyVO queryById(@NotNull Long id, int retryCount) {

        String url = getUrl(UrlConstant.PERM_CONTROL_ENGINE_IP, UrlConstant.PERM_CONTROL_ENGINE_PORT,
                urlConfig.getPath().getPceSysCompanyQueryByUserId());
        if (ObjectUtil.isNull(url)) {
            log.error("queryById() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            ResponseVO responseVO = restTemplateUtils.get(url, ResponseVO.class, id);

            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                log.error("queryById() get api companyVO failed");
                throw new InvException(ResponseEnum.API_COMPANY_SELECT_FAIL);
            }

            String data = JSONObject.toJSONString(responseVO.getData());
            CompanyVO companyVO = JSONObject.parseObject(data, CompanyVO.class);
            if (ObjectUtil.isNull(companyVO)) {
                log.error("queryById() get api companyVO failed");
                throw new InvException(ResponseEnum.API_COMPANY_SELECT_FAIL);
            }

            if (ObjectUtil.isNotNull(companyVO)) {
                return companyVO;
            }
            retryCount++;
            queryById(id, retryCount);
        } catch (Exception e) {
            log.error("queryById {}", e.getMessage());
            retryCount++;
            queryById(id, retryCount);
        }

        throw new InvException(ResponseEnum.API_COMPANY_SELECT_FAIL);
//        return null;
    }

    public static List<CompanyVO> getSysCompanyTree() {
        return getSysCompanyTree(NumberConstant.ZERO);
    }

    private static List<CompanyVO> getSysCompanyTree(int retryCount) {

        String url = getUrl(UrlConstant.PERM_CONTROL_ENGINE_IP, UrlConstant.PERM_CONTROL_ENGINE_PORT,
                urlConfig.getPath().getPceSysCompanyGetSysCompanyTree());
        if (ObjectUtil.isNull(url)) {
            log.error("getCompanyVO() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            ResponseVO responseVO = restTemplateUtils.get(url, new ParameterizedTypeReference<ResponseVO>() {
            });
            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                log.error("getCompanyVO() get api companyVO failed");
                throw new InvException(ResponseEnum.API_COMPANY_SELECT_FAIL);
            }

            String data = JSONObject.toJSONString(responseVO.getData());
//            List<CompanyVO> companyVOS = JSONObject.parseObject(data, List.class);
            List<CompanyVO> companyVOS = JSON.parseArray(data, CompanyVO.class);
            if (CollectionUtil.isEmpty(companyVOS)) {
                log.error("getCompanyVO() get api companyVOS failed");
                throw new InvException(ResponseEnum.API_COMPANY_SELECT_FAIL);
            }

            if (CollectionUtil.isNotEmpty(companyVOS)) {
                return companyVOS;
            }
            retryCount++;
            getSysCompanyTree(retryCount);
        } catch (Exception e) {
            log.error("getCompanyVO {}", e.getMessage());
            retryCount++;
            getSysCompanyTree(retryCount);
        }

        throw new InvException(ResponseEnum.API_COMPANY_SELECT_FAIL);
//        return null;
    }

    public static SysRoleVO judgeGroupCompany(@Nullable Long id) {
        return judgeGroupCompany(id, NumberConstant.ZERO);
    }

    private static SysRoleVO judgeGroupCompany(@Nullable Long id, int retryCount) {

        String url = getUrl(UrlConstant.PERM_CONTROL_ENGINE_IP, UrlConstant.PERM_CONTROL_ENGINE_PORT,
                urlConfig.getPath().getPceSysCompanyJudgeGroupCompany());
        if (ObjectUtil.isNull(url)) {
            log.error("judgeGroupCompany() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            ResponseVO responseVO = restTemplateUtils.get(url, new ParameterizedTypeReference<ResponseVO>() {
            });
            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                log.error("judgeGroupCompany() judgeGroupCompany failed");
                throw new InvException(ResponseEnum.API_JUDGE_GROUP_COMPANY_FAIL);
            }

            String data = JSONObject.toJSONString(responseVO.getData());
            SysRoleVO sysRoleVO = JSONObject.parseObject(data, SysRoleVO.class);
            if (ObjectUtil.isNull(sysRoleVO)) {
                log.error("judgeGroupCompany() judgeGroupCompany failed");
                throw new InvException(ResponseEnum.API_JUDGE_GROUP_COMPANY_FAIL);
            }

            if (ObjectUtil.isNotNull(sysRoleVO)) {
                return sysRoleVO;
            }
            retryCount++;
            judgeGroupCompany(id, retryCount);
        } catch (Exception e) {
            log.error("judgeGroupCompany {}", e.getMessage());
            retryCount++;
            judgeGroupCompany(id, retryCount);
        }

        throw new InvException(ResponseEnum.API_JUDGE_GROUP_COMPANY_FAIL);
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
        return urlConfig.gainWholeUrl(ipType, portType, path);
    }


}

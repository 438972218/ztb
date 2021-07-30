package com.xdcplus.xdcweb.biz.common.interaction;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.xdcweb.biz.common.config.UrlConfig;
import com.xdcplus.xdcweb.biz.common.constants.UrlConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.RequestDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class IeRequestResponseUtils {

    private static RestTemplateUtils restTemplateUtils = SpringUtil.getBean(RestTemplateUtils.class);
    private static UrlConfig urlIeConfig = SpringUtil.getBean(UrlConfig.class);

    public static RequestVO saveRequest(RequestDTO requestDTO, Boolean ifBid) {
        return saveRequest(requestDTO, ifBid, NumberConstant.ZERO);
    }

    private static RequestVO saveRequest(@NotNull RequestDTO requestDTO, @NotNull Boolean ifBid, int retryCount) {

        String url = getUrl(UrlConstant.INTERACTION_ENGINE_IP, UrlConstant.INTERACTION_ENGINE_PORT,
                urlIeConfig.getPath().getIeRequestSaveRequest());
        if (ObjectUtil.isNull(url)) {
            log.error("getRequestVO() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }
        String errorMsg = null;

        try {
            Object obj = JSONArray.toJSON(requestDTO);
            ResponseVO responseVO = restTemplateUtils.post(url, obj, new ParameterizedTypeReference<ResponseVO>() {
            });

            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                errorMsg = responseVO.getMessage();
                log.error("getRequestVO() get api RequestFlowVO failed");
                throw new InvException(ResponseEnum.API_REQUEST_FLOWVO_FAIL, responseVO.getMessage());
            }

            String data = JSONObject.toJSONString(responseVO.getData());
            RequestVO requestVO = JSONObject.parseObject(data, RequestVO.class);
            if (ObjectUtil.isNull(requestVO)) {
                log.error("getRequestVO() get api RequestVO failed");
                throw new InvException(ResponseEnum.API_REQUEST_FLOWVO_FAIL);
            }

            if (ObjectUtil.isNotNull(requestVO)) {
                return requestVO;
            }
            retryCount++;
            saveRequest(requestDTO, ifBid, retryCount);
        } catch (Exception e) {
            log.error("getRequestVO {}", e.getMessage());
            retryCount++;
            saveRequest(requestDTO, ifBid, retryCount);
        }

        throw new InvException(ResponseEnum.REQUEST_CREATE_FAIL, errorMsg);
//        return null;
    }

    public static RequestVO queryRequestById(Long id) {
        return queryRequestById(id, NumberConstant.ZERO);
    }

    private static RequestVO queryRequestById(@NotNull Long id, int retryCount) {

//        Map<String, Object> params = CollectionUtil.newHashMap();
//
//        Optional.ofNullable(id).ifPresent(a -> params.put("id", a.toString()));
//        params.put("currentPage", "-1");

        String url = getUrl(UrlConstant.INTERACTION_ENGINE_IP, UrlConstant.INTERACTION_ENGINE_PORT,
                urlIeConfig.getPath().getIeRequestQueryRequestById());
        if (ObjectUtil.isNull(url)) {
            log.error("getRequestFlowVO() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            ResponseVO responseVO = restTemplateUtils.get(url, ResponseVO.class, id);

            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                log.error("getRequestFlowVO() get api RequestFlowVO failed");
                throw new InvException(ResponseEnum.API_REQUEST_FLOWVO_FAIL);
            }

            String data = JSONObject.toJSONString(responseVO.getData());
            RequestVO requestVO = JSONObject.parseObject(data, RequestVO.class);

            if (ObjectUtil.isNull(requestVO)) {
                log.error("queryRequestById() get api request failed");
                throw new InvException(ResponseEnum.REQUEST_SELECT_FAIL);
            }

            if (ObjectUtil.isNull(requestVO.getStatus())) {
                log.error("queryRequest() request status found failed");
                throw new InvException(ResponseEnum.REQUEST_SELECT_FAIL);
            }
            return requestVO;

        } catch (Exception e) {
            log.error("getRequestFlowVO {}", e.getMessage());
            retryCount++;
            queryRequestById(id, retryCount);
        }

        throw new InvException(ResponseEnum.REQUEST_SELECT_FAIL);
//        return null;
    }

    public static List<RequestVO> queryRequestByParentId(Long id) {
        return queryRequestByParentId(id, NumberConstant.ZERO);
    }

    private static List<RequestVO> queryRequestByParentId(@NotNull Long id, int retryCount) {

        Map<String, Object> params = CollectionUtil.newHashMap();

        Optional.ofNullable(id).ifPresent(a -> params.put("parentId", a.toString()));
        params.put("currentPage", "-1");


        String url = getUrl(UrlConstant.INTERACTION_ENGINE_IP, UrlConstant.INTERACTION_ENGINE_PORT,
                urlIeConfig.getPath().getIeRequestQueryRequestByParentId());
        if (ObjectUtil.isNull(url)) {
            log.error("getRequestFlowVO() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            ResponseVO responseVO = restTemplateUtils.get(url, params, null, new ParameterizedTypeReference<ResponseVO>() {
            });


            if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

                log.error("getRequestFlowVO() get api RequestFlowVO failed");
                throw new InvException(ResponseEnum.API_REQUEST_FLOWVO_FAIL);
            }

            String data = JSONObject.toJSONString(responseVO.getData());
            JSONObject jsondata = JSONObject.parseObject(data);
            String records = jsondata.getString("records");

            List<RequestVO> requestVOs = JSON.parseArray(records, RequestVO.class);
            if (CollUtil.isEmpty(requestVOs)) {
                return null;
            }

            return requestVOs;

//            String data = JSONObject.toJSONString(responseVO.getData());
//            if (ObjectUtil.isNull(responseVO)) {
//                log.error("getRequestFlowVO() get api RequestFlowVO failed");
//                throw new InvException(ResponseEnum.GET_API_REQUESTFLOWVO_FAILED);
//            }
//
//            if (ObjectUtil.isNotNull(responseVO)) {
//                return responseVO;
//            }
//            retryCount++;
//            postProcess(processTransforDTO, retryCount);
        } catch (Exception e) {
            log.error("getRequestFlowVO {}", e.getMessage());
            retryCount++;
            queryRequestById(id, retryCount);
        }

        throw new InvException(ResponseEnum.API_REQUEST_FLOWVO_FAIL);
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
        return urlIeConfig.gainWholeUrl(ipType, portType, path);
    }


}

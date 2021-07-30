package com.xdcplus.xdcweb.biz.common.interaction;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONArray;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.xdcweb.biz.common.config.UrlConfig;
import com.xdcplus.xdcweb.biz.common.constants.UrlConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.ProcessTransforDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;

import javax.validation.constraints.NotNull;

@Slf4j
public class IeRequestFlowResponseUtils {

    private static RestTemplateUtils restTemplateUtils = SpringUtil.getBean(RestTemplateUtils.class);
    private static UrlConfig urlConfig = SpringUtil.getBean(UrlConfig.class);

    public static ResponseVO postProcess(@NotNull ProcessTransforDTO processTransforDTO) {
        return postProcess(processTransforDTO, NumberConstant.ZERO);
    }

    private static ResponseVO postProcess(@NotNull ProcessTransforDTO processTransforDTO, int retryCount) {

        String url = getUrl(UrlConstant.INTERACTION_ENGINE_IP, UrlConstant.INTERACTION_ENGINE_PORT,
                urlConfig.getPath().getIeRequestFlowPostProcess());
        if (ObjectUtil.isNull(url)) {
            log.error("postProcess() url is null");
            return null;
        }
        if (retryCount >= NumberConstant.THREE) {
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            Object obj = JSONArray.toJSON(processTransforDTO);
            ResponseVO responseVO = restTemplateUtils.post(url, obj, new ParameterizedTypeReference<ResponseVO>() {
            });

            if (!NumberConstant.ZERO.equals(responseVO.getCode())) {

                log.error("postProcess() get api postProcess failed");
                throw new InvException(ResponseEnum.API_REQUEST_FLOWVO_FAIL);
            }
            return responseVO;

//            String data = JSONObject.toJSONString(responseVO.getData());
//            RequestVO requestVO = JSONObject.parseObject(data, RequestVO.class);
//            if (ObjectUtil.isNull(requestVO)) {
//                log.error("postProcess() get api postProcess failed");
//                throw new InvException(ResponseEnum.GET_API_REQUESTFLOWVO_FAILED);
//            }
//
//            if (ObjectUtil.isNotNull(requestVO)) {
//                return requestVO;
//            }
//            retryCount++;
//            postProcess(processTransforDTO, retryCount);
        } catch (Exception e) {
            log.error("postProcess {}", e.getMessage());
            retryCount++;
            postProcess(processTransforDTO, retryCount);
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
        return urlConfig.gainWholeUrl(ipType, portType, path);
    }


}

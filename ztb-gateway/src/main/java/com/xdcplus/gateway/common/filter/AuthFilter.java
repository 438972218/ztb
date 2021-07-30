package com.xdcplus.gateway.common.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;
import com.xdcplus.cache.redis.RedisUtils;
import com.xdcplus.gateway.common.authorization.AuthConfig;
import com.xdcplus.gateway.common.authorization.WhiteListConfig;
import com.xdcplus.gateway.common.enums.ResponseEnum;
import com.xdcplus.gateway.common.utils.WebUtils;
import com.xdcplus.tool.constants.AuthConstant;
import com.xdcplus.tool.constants.QueryConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;


/**
 * 鉴权认证
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {

    private final WhiteListConfig whiteListConfig;
    private final ObjectMapper objectMapper;
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private final RedisUtils redisUtils;
    private final AuthConfig authConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //校验 Token 放行
        String path = exchange.getRequest().getURI().getPath();
        if (isSkip(path)) {
            return chain.filter(exchange);
        }

        String headerToken = exchange.getRequest().getHeaders().getFirst(AuthConstant.AUTHORIZATION_HEADER);
        String paramToken = exchange.getRequest().getQueryParams().getFirst(AuthConstant.AUTHORIZATION_HEADER);
        if (StringUtils.isBlank(headerToken) && StringUtils.isBlank(paramToken)) {
            return WebUtils.writeFailedToResponse(response, ResponseEnum.MISSING_TOKEN_AUTHENTICATION_FAILED);
        }

        String token = StringUtils.isBlank(headerToken) ? paramToken : headerToken;
        if (StrUtil.isBlank(token)) {
            return WebUtils.writeFailedToResponse(response, ResponseEnum.MISSING_TOKEN_AUTHENTICATION_FAILED);
        }
        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在拦截响应token失效
        String realToken = token.replace(AuthConstant.AUTHORIZATION_PREFIX, StrUtil.EMPTY);

        if (!redisUtils.hasKey(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + realToken)) {
            log.error("Authorization expired, request to log in again");
            return WebUtils.writeFailedToResponse(response, ResponseEnum.AUTHORIZATION_EXPIRES);
        }

        try {

            JWSObject jwsObject = JWSObject.parse(realToken);
            Payload payload = JSONObject.parseObject(jwsObject.getPayload().toString(), Payload.class);

            request = exchange.getRequest().mutate()
                    .header(AuthConstant.ACCOUNT, payload.getAccount())
                    .header(QueryConstant.ID, Convert.toStr(payload.getId()))
                    .header(AuthConstant.AUTHORIZATION_HEADER, realToken)
                    .build();
            exchange = exchange.mutate().request(request).build();

            redisUtils.expire(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + realToken,
                    Integer.parseInt(authConfig.getRefreshTokenExpireTime()), TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("AuthFilter {}", e.getMessage());
            return WebUtils.writeFailedToResponse(response, ResponseEnum.AUTHENTICATION_FAILED);
        }

        return chain.filter(exchange);
    }

    private boolean isSkip(String path) {

        for (String url : whiteListConfig.getUrls()) {
            if (pathMatcher.match(url, path)) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;

    }

    @Override
    public int getOrder() {
        return -100;
    }

}

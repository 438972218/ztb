package com.xdcplus.gateway.common.dynamic;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.xdcplus.gateway.common.constants.NacosConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;


/**
 * 动态路由监听器
 */
@Order
@Slf4j
@Component
public class DynamicRouteServiceListener {

    private final DynamicRouteService dynamicRouteService;
    private final NacosDiscoveryProperties nacosDiscoveryProperties;
    private final NacosConfigProperties nacosConfigProperties;


    public DynamicRouteServiceListener(DynamicRouteService dynamicRouteService,
                                       NacosDiscoveryProperties nacosDiscoveryProperties,
                                       NacosConfigProperties nacosConfigProperties) {
        this.dynamicRouteService = dynamicRouteService;
        this.nacosDiscoveryProperties = nacosDiscoveryProperties;
        this.nacosConfigProperties = nacosConfigProperties;
        dynamicRouteServiceListener();
    }

    /**
     * 监听Nacos下发的动态路由配置
     */
    private void dynamicRouteServiceListener() {
        try {
            String dataId = NacosConstant.dataId(nacosConfigProperties.getName(), nacosConfigProperties.getPrefix(), nacosConfigProperties.getFileExtension());
            String group = nacosConfigProperties.getGroup();
            Properties properties = new Properties();
            properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosDiscoveryProperties.getServerAddr());
            properties.setProperty(PropertyKeyConst.NAMESPACE, nacosDiscoveryProperties.getNamespace());
            ConfigService configService = NacosFactory.createConfigService(properties);
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
                    dynamicRouteService.updateList(routeDefinitions);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
            String configInfo = configService.getConfig(dataId, group, 5000);
            if (configInfo != null) {
                List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
                dynamicRouteService.updateList(routeDefinitions);
            }
        } catch (NacosException ignored) {

        }
    }

}

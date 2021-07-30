package com.xdcplus.gateway.common.config;

//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.core.util.StrUtil;
//import com.github.xiaoymin.knife4j.aggre.nacos.NacosRoute;
//import com.github.xiaoymin.knife4j.aggre.spring.configuration.Knife4jAggregationProperties;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.config.GatewayProperties;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.support.NameUtils;
//import org.springframework.stereotype.Component;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 聚合其他微服务的api资源
// *
// * @author Rong.Jia
// * @date 2021/05/11 13:33
// */
//@Slf4j
//@Component
//@AllArgsConstructor
//public class SwaggerResourceConfig implements SwaggerResourcesProvider {
//
//
//    private static final String API_URI = "v2/api-docs";
//
//    private final RouteLocator routeLocator;
//    private final GatewayProperties gatewayProperties;
//    private final Knife4jAggregationProperties knife4jAggregationProperties;
//
//    @Override
//    public List<SwaggerResource> get() {
//        return getSwaggerResources();
//    }
//
//    private List<SwaggerResource> getSwaggerResources() {
//
//        List<SwaggerResource> resources = new ArrayList<>();
//        List<String> routes = getRoutes();
//
//        List<RouteDefinition> routeDefinitionList = gatewayProperties.getRoutes().stream()
//                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
//                .collect(Collectors.toList());
//
//        routeDefinitionList.forEach(route -> {
//            route.getPredicates().stream()
//                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
//                    .forEach(predicateDefinition -> resources.add(swaggerResource(getName(route.getId()),
//                            predicateDefinition.getArgs()
//                                    .get(NameUtils.GENERATED_NAME_PREFIX + "0")
//                                    .replace("**", API_URI))));
//        });
//
//        return resources;
//
//    }
//
//    private List<String> getRoutes() {
//        List<String> routes = new ArrayList<>();
//        routeLocator.getRoutes().subscribe(route
//                -> routes.add(route.getId()));
//        return routes;
//    }
//
//    private List<NacosRoute> getNacosRoutes() {
//        return knife4jAggregationProperties.getNacos().getRoutes();
//    }
//
//    private String getName(String serviceName) {
//
//        NacosRoute nacosRoute = getNacosRoutes().stream()
//                .filter(route -> StrUtil.equals(serviceName, route.getServiceName()))
//                .findAny().orElse(null);
//
//        return ObjectUtil.isNull(nacosRoute) ? serviceName : nacosRoute.getName();
//
//    }
//
//
//    private SwaggerResource swaggerResource(String name, String location) {
//        log.info("name:{},location:{}", name, location);
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion("2.0");
//        return swaggerResource;
//    }


//}

package com.xdcplus.gateway.common.constants;

/**
 * nacos 常量类
 * @date 2021/05/13 09:15
 * @author Rong.Jia
 */
public class NacosConstant {

    public static final String NACOS_ADDR = "127.0.0.1:8848";
    public static final String NACOS_CONFIG_PREFIX = "blade";
    public static final String NACOS_GROUP_SUFFIX = "-group";
    public static final String NACOS_CONFIG_FORMAT = "yml";
    public static final String NACOS_CONFIG_JSON_FORMAT = "json";
    public static final String NACOS_CONFIG_REFRESH = "true";
    public static final String NACOS_CONFIG_GROUP = "DEFAULT_GROUP";
    public static final String NACOS_SEATA_GROUP = "SEATA_GROUP";

    public static String dataId(String appName) {
        return appName + "." + NACOS_CONFIG_FORMAT;
    }

    public static String dataId(String appName, String profile) {
        return dataId(appName, profile, "yaml");
    }

    public static String dataId(String appName, String profile, String format) {
        return appName + "-" + profile + "." + format;
    }


}

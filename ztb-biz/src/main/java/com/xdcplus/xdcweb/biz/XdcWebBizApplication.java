package com.xdcplus.xdcweb.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 *
 * @author Rong.Jia
 * @date 2021/05/24 09:10
 */
@EnableTransactionManagement
@MapperScan("com.xdcplus.xdcweb.biz.mapper")
@EnableAsync
@EnableCaching
@EnableScheduling
//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.xdcplus.*", "cn.hutool.extra.*"})
public class XdcWebBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdcWebBizApplication.class, args);
    }

}

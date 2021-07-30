package com.xdcplus.xdcweb.biz.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.tool.utils.OkHttpUtils;
import com.xdcplus.xdcweb.biz.XdcWebBizApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateTest extends XdcWebBizApplicationTest {

    @Autowired(required = false)
    private RestTemplate restTemplate;


    @Test
    void test1() {

        Map<String, Object> map = CollectionUtil.newHashMap();
        map.put("processId", 1);
        map.put("title", "cehaisads");

        restTemplate.postForLocation("http://10.20.54.133:10001/request", map);
        System.out.println("");


    }

    @Test
    public void sendRequestAsync() {

        String a = OkHttpUtils.builder().url("http://10.20.54.133:9004/sysCompany/getSysCompanyDepartmentTree")
                .get()
                .async();
        System.out.println(a);
    }


}

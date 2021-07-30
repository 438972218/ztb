package com.xdcplus.xdcweb.biz;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONArray;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceCompanyResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.RequestDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Vendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.generator.VendorBaseService;
import com.xdcplus.xdcweb.biz.service.VendorService;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

@SpringBootTest
public class XdcWebBizApplicationTest {
    @Autowired
    public VendorSiteInsService vendorSiteInsService;
    @Autowired
    public VendorBaseService vendorService;

    @Test
    void contextLoads() {
    }


    @Test
    void testQueryCompanyById() {
        CompanyVO companyVO = PceCompanyResponseUtils.queryById(1L);
        System.out.println("aa");
    }

    @Test
    void test1() {
        RestTemplateUtils restTemplateUtils = SpringUtil.getBean(RestTemplateUtils.class);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle("1234");
        requestDTO.setProcessId(1415211599963095042L);
        requestDTO.setRuleId(1L);
        ResponseVO responseVO = restTemplateUtils.post("http://10.20.54.133:10000/api/ie/request", requestDTO, new ParameterizedTypeReference<ResponseVO>() {
        });

        Object obj = JSONArray.toJSON(requestDTO);
        String json = obj.toString();
        ResponseVO responseVO1 = restTemplateUtils.post("http://10.20.54.133:10000/api/ie/request", obj, new ParameterizedTypeReference<ResponseVO>() {
        });
        System.out.println("aa");
    }

    @Test
    void test2() {
//        vendorSiteInsService.calculateScore(1L);
        Vendor vendor=new Vendor();
        vendor.setId(null);
        vendor.setStatus("已过审");
        vendorService.saveOrUpdate(vendor);
    }
    //    @LoadBalanced
//    public RestTemplate restTemplage() {
//        return new RestTemplate();
//    }
}

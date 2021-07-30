package com.xdcplus.xdcweb.biz.utils;

import com.xdcplus.xdcweb.biz.XdcWebBizApplicationTest;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RequestFlowTest extends XdcWebBizApplicationTest {

    @Data
    private static class FlowCondition {
        private Integer age;
    }

    @Test
    void test2() {
        List<RequestVO> responseVO = IeRequestResponseUtils.queryRequestByParentId(1413340059109814274L);
        System.out.println("");

    }


}

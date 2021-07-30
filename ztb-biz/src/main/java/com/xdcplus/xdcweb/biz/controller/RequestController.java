package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 表单(request)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:07
 */
@Api(tags = "表单(request)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("request")
public class RequestController extends AbstractController {

    @ApiOperation("退回")
    @PostMapping(value = "/back", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO backRequest(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("backRequest {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(2);
        processTransforDTO.setToUserId(requestFlowDTO.getToUserId());
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setDescription("退回");

        ProcessTransforDTO.SendBack sendBack = new ProcessTransforDTO.SendBack();
        sendBack.setToStatusId(requestFlowDTO.getStatusId());

        ResponseVO responseVO = IeRequestFlowResponseUtils.postProcess(processTransforDTO);

        return responseVO;
    }


    @ApiOperation("show")
    @GetMapping(value = "/show", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO showRequest(RequestDTO requestDTO) {

        log.info("showRequest {}", requestDTO.toString());

        RequestVO responseVO = IeRequestResponseUtils.queryRequestById(requestDTO.getRequestId());

        return ResponseVO.success(responseVO);
    }

    @ApiOperation("新增审核表单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveRequest(@RequestBody RequestDTO requestDTO) {

        log.info("saveRequest {}", requestDTO.toString());

        requestDTO.setCreatedUser(getAccount());
        requestDTO.setProcessId(1415125159027109889L);
        requestDTO.setRuleId(2L);
        Long parentId = requestDTO.getParentIds().iterator().next();
        RequestVO requestVO = IeRequestResponseUtils.queryRequestById(parentId);
        requestDTO.setTitle(requestVO.getTitle() + "-审批单");

        return ResponseVO.success(IeRequestResponseUtils.saveRequest(requestDTO, false));

    }

}

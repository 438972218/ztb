package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceRoleResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionVO;
import com.xdcplus.xdcweb.biz.service.VendorQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 供应商电子调查表(VendorQuestion)表服务控制层
 *
 * @author makejava
 * @since 2021-07-15 10:53:35
 */
@Api(tags = "供应商电子调查表(VendorQuestion)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorQuestion")
public class VendorQuestionController extends AbstractController {

    @Autowired
    private final VendorQuestionService vendorQuestionService;

    @ApiOperation("新增供应商电子调查表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)


    public ResponseVO saveVendorQuestion(@RequestBody @Valid VendorQuestionDTO vendorQuestionDTO) {

        log.info("saveVendorQuestion {}", vendorQuestionDTO.toString());

        vendorQuestionDTO.setCreatedUser(getAccount());
        vendorQuestionService.saveVendorQuestion(vendorQuestionDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商电子调查表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQuestion(@RequestBody @Valid VendorQuestionDTO vendorQuestionDTO) {

        log.info("updateVendorQuestion {}", vendorQuestionDTO.toString());

        vendorQuestionDTO.setUpdatedUser(getAccount());
        vendorQuestionService.updateVendorQuestion(vendorQuestionDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商电子调查表")
    @DeleteMapping(value = "/{vendorQuestionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorQuestionId", dataType = "Long", value = "供应商电子调查表ID", required = true),
    })
    public ResponseVO deleteVendorQuestionLogical(@PathVariable("vendorQuestionId")
                                                  @NotNull(message = "供应商电子调查表ID不能为空") Long vendorQuestionId) {

        log.info("deleteVendorQuestionLogical {}", vendorQuestionId);

        vendorQuestionService.deleteVendorQuestionLogical(vendorQuestionId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商电子调查表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorQuestionVO>> findVendorQuestion(VendorQuestionFilterDTO vendorQuestionFilterDTO) {

        log.info("findVendorQuestion {}", vendorQuestionFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQuestionFilterDTO);

        return ResponseVO.success(vendorQuestionService.queryVendorQuestion(vendorQuestionFilterDTO));
    }

    @ApiOperation("新增电子调查单返回VO")
    @PostMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorQuestionReturnVO(@RequestBody VendorQuestionDTO vendorQuestionDTO) {

        log.info("saveVendorQuestionReturnVO {}", vendorQuestionDTO.toString());

        vendorQuestionDTO.setCreatedUser(getAccount());
        VendorQuestionVO vendorQuestionVO = vendorQuestionService.saveVendorQuestionReturnVO(vendorQuestionDTO);

        return ResponseVO.success(vendorQuestionVO);
    }

    @ApiOperation("查询供应商电子调查表(request)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorQuestionVO>> findVendorQuestionWithRequest(VendorQuestionFilterDTO vendorQuestionFilterDTO) {

        log.info("findVendorQuestionWithRequest {}", vendorQuestionFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQuestionFilterDTO);
        PageVO<VendorQuestionVO> vendorQuestionVOPageVO = vendorQuestionService.queryVendorQuestionWithRequest(vendorQuestionFilterDTO);

        return ResponseVO.success(vendorQuestionVOPageVO);
    }

    @ApiOperation("show电子调查表")
    @GetMapping(value = "/show/{vendorQuestionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorQuestionId", dataType = "Long", value = "电子调查表", required = true),
    })

    public ResponseVO<VendorQuestionVO> showVendorQuestionById(@PathVariable("vendorQuestionId")
                                                               @NotNull(message = "电子调查表ID不能为空") Long vendorQuestionId) {
        log.info("showVendorQuestionById {}", vendorQuestionId);
        return ResponseVO.success(vendorQuestionService.showVendorQuestionById(vendorQuestionId));
    }

    @ApiOperation("修改供应商电子调查表WithDetail")
    @PutMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQuestionWithDetail(@RequestBody @Valid VendorQuestionDTO vendorQuestionDTO) {

        log.info("updateVendorQualifyReviewWithDetail {}", vendorQuestionDTO.toString());

        vendorQuestionDTO.setUpdatedUser(getAccount());

        vendorQuestionService.updateVendorQuestionWithDetail(vendorQuestionDTO);

        return ResponseVO.success();
    }

    @ApiOperation("提交审批电子调查单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO submitVendorQuestion(@RequestBody VendorQuestionDTO vendorQuestionDTO) {
        log.info("submitvendorQuestion {}", vendorQuestionDTO.toString());
        if(vendorQuestionDTO.getId()==null){
            vendorQuestionDTO.setCreatedUser(getAccount());
            VendorQuestionVO vendorQuestionVO = vendorQuestionService.saveVendorQuestionReturnVO(vendorQuestionDTO);
            vendorQuestionDTO.setId(vendorQuestionVO.getId());
        }
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle(vendorQuestionDTO.getName());
        requestDTO.setRuleId(vendorQuestionDTO.getRuleId());
//        requestDTO.setConfigVersion(inquirySheetDTO.getConfigVersion());
        requestDTO.setProcessId(vendorQuestionDTO.getProcessId());
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);

        vendorQuestionDTO.setRequestId(requestVO.getId());
        vendorQuestionService.updateVendorQuestion(vendorQuestionDTO);

        return ResponseVO.success();
    }

    @ApiOperation("流转电子调查单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreeVendorQuestion(@RequestBody RequestFlowDTO requestFlowDTO) {
        log.info("agreeVendorQuestion {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = approveJudgeStatus(requestFlowDTO);

        ResponseVO responseVO = IeRequestFlowResponseUtils.postProcess(processTransforDTO);

        return responseVO;
    }

    private ProcessTransforDTO approveJudgeStatus(RequestFlowDTO requestFlowDTO) {
        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(1);
        processTransforDTO.setUserId(requestFlowDTO.getUserId());
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setDescription(requestFlowDTO.getDescription());
        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();
        List<Long> roleIds = PceRoleResponseUtils.queryByUserId(requestFlowDTO.getUserId());
        agree.setRoleIds(roleIds);
        processTransforDTO.setAgree(agree);

        return processTransforDTO;
    }

}

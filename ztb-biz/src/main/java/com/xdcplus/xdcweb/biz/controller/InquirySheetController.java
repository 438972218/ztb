package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.config.AssemblyBuilder;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceRoleResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquirySheetVO;
import com.xdcplus.xdcweb.biz.service.InquirySheetService;
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
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


/**
 * 询价单(InquirySheet)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:27:44
 */
@Api(tags = "询价单(InquirySheet)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("inquirySheet")
public class InquirySheetController extends AbstractController {

    @Autowired
    private InquirySheetService inquirySheetService;

    @ApiOperation("新增询价单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquirySheet(@RequestBody InquirySheetDTO inquirySheetDTO) {

        log.info("saveInquirySheet {}", inquirySheetDTO.toString());

        inquirySheetDTO.setCreatedUser(getAccount());
        inquirySheetService.saveInquirySheet(inquirySheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改询价单")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInquirySheet(@RequestBody InquirySheetDTO inquirySheetDTO) {

        log.info("updateInquirySheet {}", inquirySheetDTO.toString());

        inquirySheetDTO.setUpdatedUser(getAccount());
        inquirySheetService.updateInquirySheet(inquirySheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除询价单")
    @DeleteMapping(value = "/{inquirySheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inquirySheetId", dataType = "Long", value = "询价单ID", required = true),
    })
    public ResponseVO deleteInquirySheetLogical(@PathVariable("inquirySheetId")
                                                @NotNull(message = "询价单ID不能为空") Long inquirySheetId) {

        log.info("deleteInquirySheetLogical {}", inquirySheetId);

        inquirySheetService.deleteInquirySheetLogical(inquirySheetId);

        return ResponseVO.success();
    }

    @ApiOperation("查询询价单")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquirySheetVO>> findInquirySheet(InquirySheetFilterDTO inquirySheetFilterDTO) {

        log.info("findInquirySheet {}", inquirySheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquirySheetFilterDTO);

        return ResponseVO.success(inquirySheetService.queryInquirySheet(inquirySheetFilterDTO));
    }

    @ApiOperation("新增询价单返回VO")
    @PostMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquirySheetReturnVO(@RequestBody InquirySheetDTO inquirySheetDTO) {

        log.info("saveInquirySheetReturnVO {}", inquirySheetDTO.toString());

        inquirySheetDTO.setCreatedUser(getAccount());
        inquirySheetDTO.setTitle("询价单-" + inquirySheetDTO.getCreatedUser() + "-" + DateUtil.formatDateTime(new Date()));
        InquirySheetVO inquirySheetVO = inquirySheetService.saveInquirySheetReturnVO(inquirySheetDTO);

        return ResponseVO.success(inquirySheetVO);
    }

    @ApiOperation("show询价单")
    @GetMapping(value = "/show/{inquirySheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inquirySheetId", dataType = "Long", value = "询价单ID", required = true),
    })
    public ResponseVO<InquirySheetVO> showInquirySheetById(@PathVariable("inquirySheetId")
                                                           @NotNull(message = "询价单ID不能为空") Long inquirySheetId) {

        log.info("showInquirySheetById {}", inquirySheetId);

        return ResponseVO.success(inquirySheetService.showInquirySheetById(inquirySheetId));
    }

    @ApiOperation("查询询价单(requst)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquirySheetVO>> findInquirySheetWithRequest(InquirySheetFilterDTO inquirySheetFilterDTO) {

        log.info("findInquirySheet {}", inquirySheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquirySheetFilterDTO);
        PageVO<InquirySheetVO> inquirySheetVOPageVO = inquirySheetService.queryInquirySheetWithRequest(inquirySheetFilterDTO);

        return ResponseVO.success(inquirySheetVOPageVO);
    }

    @ApiOperation("查询询价单(供应商)")
    @GetMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquirySheetVO>> findInquirySheetByVendor(InquirySheetFilterDTO inquirySheetFilterDTO) {

        log.info("findInquirySheet {}", inquirySheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquirySheetFilterDTO);
        PageVO<InquirySheetVO> inquirySheetVOPageVO = inquirySheetService.queryInquirySheetByVendor(inquirySheetFilterDTO);

        return ResponseVO.success(inquirySheetVOPageVO);
    }

    @ApiOperation("提交审批询价单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<InquirySheetVO> submitInquirySheet(@RequestBody InquirySheetDTO inquirySheetDTO) {

        log.info("submitInquirySheet {}", inquirySheetDTO.toString());

        inquirySheetDTO.setCreatedUser(getAccount());
        inquirySheetDTO.setTitle(ZtbConstant.INQUIRY_TITLE + inquirySheetDTO.getCreatedUser() + "-" + DateUtil.formatDateTime(new Date()));
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle(inquirySheetDTO.getTitle());
        requestDTO.setRuleId(inquirySheetDTO.getRuleId());
//        requestDTO.setConfigVersion(inquirySheetDTO.getConfigVersion());
        requestDTO.setProcessId(inquirySheetDTO.getProcessId());

        submitBeforeJudgeStatus( inquirySheetDTO, requestDTO);
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);

        inquirySheetDTO.setRequestId(requestVO.getId());
        InquirySheetVO inquirySheetVO = inquirySheetService.saveInquirySheetReturnVO(inquirySheetDTO);

        inquirySheetDTO.setId(inquirySheetVO.getId());
        submitAfterJudgeStatus(inquirySheetDTO);

        return ResponseVO.success(inquirySheetVO);
    }

    @ApiOperation("流转询价单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreeInquirySheet(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreeInquirySheet {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(1);
        processTransforDTO.setUserId(requestFlowDTO.getUserId());
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setDescription(requestFlowDTO.getDescription());

        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();

        Long requestId = requestFlowDTO.getRequestId();
        List<RequestVO> requestVOS = IeRequestResponseUtils.queryRequestByParentId(requestId);
        if (CollectionUtil.isNotEmpty(requestVOS)) {
            RequestVO requestVO = requestVOS.get(requestVOS.size() - 1);
            @Data
            class FlowCondition {
                private Long requestRelationRequestStatusId;
            }
            FlowCondition flowCondition = new FlowCondition();
            flowCondition.setRequestRelationRequestStatusId(requestVO.getStatus().getId());
            agree.setFlowConditions(flowCondition);
        }

        List<Long> roleIds = PceRoleResponseUtils.queryByUserId(requestFlowDTO.getUserId());
        agree.setRoleIds(roleIds);

        processTransforDTO.setAgree(agree);
        ResponseVO responseVO = IeRequestFlowResponseUtils.postProcess(processTransforDTO);

        return responseVO;
    }

    private void submitBeforeJudgeStatus(InquirySheetDTO inquirySheetDTO,RequestDTO requestDTO) {
        Circulation circulation=new Circulation();
        AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
        if(inquirySheetDTO.getApprovalProcess()!=null){
            assemblyBuilder.addParameter("approvalProcess",inquirySheetDTO.getApprovalProcess());
            circulation.setFlowConditions(assemblyBuilder.build());
        }
        circulation.setUserId(inquirySheetDTO.getUserId());
        requestDTO.setCirculation(circulation);
    }

    private void submitAfterJudgeStatus(InquirySheetDTO inquirySheetDTO) {
        InquirySheetVO inquirySheetVO = inquirySheetService.queryInquirySheetById(inquirySheetDTO.getId());
        if (inquirySheetVO.getApprovalProcess() != null && !inquirySheetVO.getApprovalProcess().equals("不需要")) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setRuleId(1L);
            requestDTO.setProcessId(ZtbConstant.SHENGPI_PROCESS);
            requestDTO.setTitle(inquirySheetVO.getTitle() + "-审核单");
            requestDTO.setCreatedUser(inquirySheetVO.getCreatedUser());
            HashSet<Long> parentIds = new HashSet<>();
            parentIds.add(inquirySheetVO.getRequestId());
            requestDTO.setParentIds(parentIds);

            RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);
        }
    }

}

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
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSheetVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.xdcweb.biz.service.PaidSheetService;
import com.xdcplus.xdcweb.biz.service.PaidVendorService;
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
 * 竞价单(PaidSheet)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:35:13
 */
@Api(tags = "竞价单(PaidSheet)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("paidSheet")
public class PaidSheetController extends AbstractController {

    @Autowired
    private PaidSheetService paidSheetService;

    @Autowired
    private PaidVendorService paidVendorService;

    @ApiOperation("新增竞价单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidSheet(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("savePaidSheet {}", paidSheetDTO.toString());

        paidSheetDTO.setCreatedUser(getAccount());
        paidSheetService.savePaidSheet(paidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改竞价单")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePaidSheet(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("updatePaidSheet {}", paidSheetDTO.toString());

        paidSheetDTO.setUpdatedUser(getAccount());
        paidSheetService.updatePaidSheet(paidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除竞价单")
    @DeleteMapping(value = "/{paidSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidSheetId", dataType = "Long", value = "竞价单ID", required = true),
    })
    public ResponseVO deletePaidSheetLogical(@PathVariable("paidSheetId")
                                             @NotNull(message = "竞价单ID不能为空") Long paidSheetId) {

        log.info("deletePaidSheetLogical {}", paidSheetId);

        paidSheetService.deletePaidSheetLogical(paidSheetId);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价单")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidSheetVO>> findPaidSheet(PaidSheetFilterDTO paidSheetFilterDTO) {

        log.info("findPaidSheet {}", paidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidSheetFilterDTO);

        return ResponseVO.success(paidSheetService.queryPaidSheet(paidSheetFilterDTO));
    }

    @ApiOperation("新增竞价单返回VO")
    @PostMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidSheetReturnVO(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("savePaidSheetReturnVO {}", paidSheetDTO.toString());

        paidSheetDTO.setCreatedUser(getAccount());
        paidSheetDTO.setTitle("竞价单-" + paidSheetDTO.getCreatedUser() + "-" + DateUtil.formatDateTime(new Date()));
        PaidSheetVO paidSheetVO = paidSheetService.savePaidSheetReturnVO(paidSheetDTO);

        return ResponseVO.success(paidSheetVO);
    }

    @ApiOperation("show竞价单")
    @GetMapping(value = "/show/{paidSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidSheetId", dataType = "Long", value = "竞价单ID", required = true),
    })
    public ResponseVO<PaidSheetVO> showPaidSheetById(@PathVariable("paidSheetId")
                                                     @NotNull(message = "竞价单ID不能为空") Long paidSheetId) {

        log.info("showPaidSheetById {}", paidSheetId);

        return ResponseVO.success(paidSheetService.showPaidSheetById(paidSheetId));
    }

    @ApiOperation("查询竞价单(供应商)")
    @GetMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidSheetVO>> findPaidSheetByVendor(PaidSheetFilterDTO paidSheetFilterDTO) {

        log.info("findPaidSheetByVendor {}", paidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidSheetFilterDTO);
        PageVO<PaidSheetVO> paidSheetVOPageVO = paidSheetService.queryPaidSheetByVendor(paidSheetFilterDTO);

        return ResponseVO.success(paidSheetVOPageVO);
    }

    @ApiOperation("查询竞价单(request)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidSheetVO>> findPaidSheetWithRequest(PaidSheetFilterDTO paidSheetFilterDTO) {

        log.info("findPaidSheetByVendor {}", paidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidSheetFilterDTO);
        PageVO<PaidSheetVO> paidSheetVOPageVO = paidSheetService.queryPaidSheetWithRequest(paidSheetFilterDTO);

        return ResponseVO.success(paidSheetVOPageVO);
    }

    @ApiOperation("提交审批竞价单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PaidSheetVO> submitPaidSheet(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("submitPaidSheet {}", paidSheetDTO.toString());

        paidSheetDTO.setCreatedUser(getAccount());
        paidSheetDTO.setTitle(ZtbConstant.PAID_TITLE + paidSheetDTO.getCreatedUser() + "-" + DateUtil.formatDateTime(new Date()));

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle(paidSheetDTO.getTitle());
        requestDTO.setRuleId(paidSheetDTO.getRuleId());
        requestDTO.setProcessId(paidSheetDTO.getProcessId());
        requestDTO.setCreatedUser(paidSheetDTO.getCreatedUser());
        submitBeforeJudgeStatus(paidSheetDTO,requestDTO);
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);

        paidSheetDTO.setRequestId(requestVO.getId());
        PaidSheetVO paidSheetVO = paidSheetService.savePaidSheetReturnVO(paidSheetDTO);
        paidSheetDTO.setId(paidSheetVO.getId());
        submitAfterJudgeStatus(paidSheetDTO);

        return ResponseVO.success(paidSheetVO);
    }

    @ApiOperation("流转竞价单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreePaidSheet(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreePaidSheet {}", requestFlowDTO.toString());

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
        RequestVO requestVO = IeRequestResponseUtils.queryRequestById(requestFlowDTO.getRequestId());
        if (requestVO.getStatus().getName().equals("待采购核价")) {
            paidVendorService.updatePaidVendorStatusByPricing(requestFlowDTO.getId());
        }

        List<Long> roleIds = PceRoleResponseUtils.queryByUserId(requestFlowDTO.getUserId());
        agree.setRoleIds(roleIds);

        processTransforDTO.setAgree(agree);
        ResponseVO responseVO = IeRequestFlowResponseUtils.postProcess(processTransforDTO);

        return responseVO;
    }

    private void submitBeforeJudgeStatus(PaidSheetDTO paidSheetDTO,RequestDTO requestDTO) {
        Circulation circulation=new Circulation();
        AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
        if(paidSheetDTO.getApprovalProcess()!=null){
            assemblyBuilder.addParameter("approvalProcess",paidSheetDTO.getApprovalProcess());
            circulation.setFlowConditions(assemblyBuilder.build());
        }
        circulation.setUserId(paidSheetDTO.getUserId());
        requestDTO.setCirculation(circulation);
    }

    private void submitAfterJudgeStatus(PaidSheetDTO paidSheetDTO) {
        PaidSheetVO paidSheetVO = paidSheetService.queryPaidSheetById(paidSheetDTO.getId());
        if (paidSheetVO.getApprovalProcess() != null && !paidSheetVO.getApprovalProcess().equals("不需要")) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setRuleId(1L);
            requestDTO.setProcessId(ZtbConstant.SHENGPI_PROCESS);
            requestDTO.setTitle(paidSheetVO.getTitle() + "-审批单");
            requestDTO.setCreatedUser(paidSheetVO.getCreatedUser());
            HashSet<Long> parentIds = new HashSet<>();
            parentIds.add(paidSheetVO.getRequestId());
            requestDTO.setParentIds(parentIds);

            RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);
        }
    }
    
}

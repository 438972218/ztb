package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceRoleResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestVO;
import com.xdcplus.xdcweb.biz.service.VendorRequestService;
import com.xdcplus.xdcweb.biz.service.VendorService;
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
import java.util.stream.Collectors;


/**
 * 供应商注册单(VendorRequest)表服务控制层
 *
 * @author makejava
 * @since 2021-07-26 15:32:00
 */
@Api(tags = "供应商注册单(VendorRequest)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorRequest")
public class VendorRequestController extends AbstractController {

    @Autowired
    private final VendorRequestService vendorRequestService;
    @Autowired
    private final VendorService vendorService;

    @ApiOperation("新增供应商注册单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorRequest(@RequestBody VendorRequestDTO vendorRequestDTO) {

        log.info("saveVendorRequest {}", vendorRequestDTO.toString());

        vendorRequestDTO.setCreatedUser(getAccount());
        vendorRequestService.saveVendorRequest(vendorRequestDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商注册单")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorRequest(@RequestBody VendorRequestDTO vendorRequestDTO) {

        log.info("updateVendorRequest {}", vendorRequestDTO.toString());

        vendorRequestDTO.setUpdatedUser(getAccount());
        vendorRequestService.updateVendorRequest(vendorRequestDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商注册单")
    @DeleteMapping(value = "/{vendorRequestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorRequestId", dataType = "Long", value = "供应商注册单ID", required = true),
    })
    public ResponseVO deleteVendorRequestLogical(@PathVariable("vendorRequestId")
                                                 @NotNull(message = "供应商注册单ID不能为空") Long vendorRequestId) {

        log.info("deleteVendorRequestLogical {}", vendorRequestId);

        vendorRequestService.deleteVendorRequestLogical(vendorRequestId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商注册单")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorRequestVO>> findVendorRequest(VendorRequestFilterDTO vendorRequestFilterDTO) {

        log.info("findVendorRequest {}", vendorRequestFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorRequestFilterDTO);

        return ResponseVO.success(vendorRequestService.queryVendorRequest(vendorRequestFilterDTO));
    }
    @ApiOperation("查询供应商注册单(request)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorRequestVO>> findVendorRequestWithRequest(VendorRequestFilterDTO vendorRequestFilterDTO) {

        log.info("findVendorRequestWithRequest {}", vendorRequestFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorRequestFilterDTO);
        PageVO<VendorRequestVO> vendorRequestVOPageVO = vendorRequestService.queryVendorRequestWithRequest(vendorRequestFilterDTO);

        return ResponseVO.success(vendorRequestVOPageVO);
    }

    @ApiOperation("新增供应商注册单返回VO")
    @PostMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorRequestReturnVO(@RequestBody VendorRequestDTO vendorRequestDTO) {

        log.info("saveVendorRequestReturnVO {}", vendorRequestDTO.toString());

        vendorRequestDTO.setCreatedUser(getAccount());
        VendorRequestVO vendorRequestVO = vendorRequestService.saveVendorQuestionReturnVO(vendorRequestDTO);

        return ResponseVO.success(vendorRequestVO);
    }

    @ApiOperation("show供应商注册单")
    @GetMapping(value = "/show/{vendorRequestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorRequestId", dataType = "Long", value = "供应商注册单", required = true),
    })
    public ResponseVO<VendorRequestVO> showVendorRequestById(@PathVariable("vendorRequestId")
                                                               @NotNull(message = "电子调查表ID不能为空") Long vendorRequestId) {
        log.info("showVendorRequestById {}", vendorRequestId);
        return ResponseVO.success(vendorRequestService.showVendorRequestById(vendorRequestId));
    }

    @ApiOperation("修改供应商注册单WithDetail")
    @PutMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorRequestWithDetail(@RequestBody @Valid VendorRequestDTO vendorRequestDTO) {

        log.info("updateVendorRequestWithDetail {}", vendorRequestDTO.toString());

        vendorRequestDTO.setUpdatedUser(getAccount());

        vendorRequestService.updateVendorRequestWithDetail(vendorRequestDTO);

        return ResponseVO.success();
    }

    @ApiOperation("提交供应商注册单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO submitVendorRequest(@RequestBody VendorRequestDTO vendorRequestDTO) {
        log.info("submitVendorRequest {}", vendorRequestDTO.toString());

        if(vendorRequestDTO.getId()==null){
            vendorRequestDTO.setCreatedUser(getAccount());
            VendorRequestVO vendorRequestVO = vendorRequestService.saveVendorQuestionReturnVO(vendorRequestDTO);
            vendorRequestDTO.setId(vendorRequestVO.getId());
        }

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle(vendorRequestDTO.getRequestName());
        requestDTO.setRuleId(vendorRequestDTO.getRuleId());
//        requestDTO.setConfigVersion(inquirySheetDTO.getConfigVersion());
        requestDTO.setProcessId(vendorRequestDTO.getProcessId());
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);

        vendorRequestDTO.setRequestId(requestVO.getId());
        vendorRequestService.updateVendorRequest(vendorRequestDTO);

        return ResponseVO.success();
    }

    @ApiOperation("流转供应商注册单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreeVendorQuestion(@RequestBody RequestFlowDTO requestFlowDTO) {
        log.info("agreeVendorQuestion {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = approveJudgeStatus(requestFlowDTO);

        ResponseVO responseVO = IeRequestFlowResponseUtils.postProcess(processTransforDTO);

        return responseVO;
    }

    private ProcessTransforDTO approveJudgeStatus(RequestFlowDTO requestFlowDTO) {
        RequestVO requestVO = IeRequestResponseUtils.queryRequestById(requestFlowDTO.getRequestId());
        ProcessTransforDTO processTransforDTO = null;

        if (ZtbConstant.ARCHIVED.equals(requestVO.getStatus().getName())) {
            vendorRequestService.saveVendorByRequestId(requestFlowDTO.getRequestId());
        }

        processTransforDTO = new ProcessTransforDTO();
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

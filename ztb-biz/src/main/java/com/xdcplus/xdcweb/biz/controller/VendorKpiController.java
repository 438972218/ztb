package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceRoleResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.vo.*;
import com.xdcplus.xdcweb.biz.service.VendorKpiService;
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


import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 供应商绩效考核表(VendorKpi)表服务控制层
 *
 * @author makejava
 * @since 2021-07-12 20:52:50
 */
@Api(tags = "供应商绩效考核表(VendorKpi)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorKpi")
public class VendorKpiController extends AbstractController {

    @Autowired
    private final VendorKpiService vendorKpiService;

    @ApiOperation("新增供应商绩效考核表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorKpi(@RequestBody @Valid VendorKpiDTO vendorKpiDTO) {

        log.info("saveVendorKpi {}", vendorKpiDTO.toString());

        vendorKpiDTO.setCreatedUser(getAccount());
        vendorKpiService.saveVendorKpi(vendorKpiDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商绩效考核表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorKpi(@RequestBody @Valid VendorKpiDTO vendorKpiDTO) {

        log.info("updateVendorKpi {}", vendorKpiDTO.toString());

        vendorKpiDTO.setUpdatedUser(getAccount());
        vendorKpiService.updateVendorKpi(vendorKpiDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商绩效考核表")
    @DeleteMapping(value = "/{vendorKpiId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorKpiId", dataType = "Long", value = "供应商绩效考核表ID", required = true),
    })
    public ResponseVO deleteVendorKpiLogical(@PathVariable("vendorKpiId")
                                             @NotNull(message = "供应商绩效考核表ID不能为空") Long vendorKpiId) {

        log.info("deleteVendorKpiLogical {}", vendorKpiId);

        vendorKpiService.deleteVendorKpiLogical(vendorKpiId);

        return ResponseVO.success();
    }
    @ApiOperation("查询供应商绩效考核表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorKpiVO>> findVendorKpi(VendorKpiFilterDTO vendorKpiFilterDTO) {

        log.info("findVendorKpi {}", vendorKpiFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorKpiFilterDTO);

        return ResponseVO.success(vendorKpiService.queryVendorKpi(vendorKpiFilterDTO));
    }

    @ApiOperation("查询供应商绩效考核表(requst)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorKpiVO>> findVendorQualifyReviewWithRequest(VendorKpiFilterDTO vendorKpiFilterDTO) {

        log.info("findVendorKpiWithRequest {}", vendorKpiFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorKpiFilterDTO);
        PageVO<VendorKpiVO> vendorKpiVOPageVO = vendorKpiService.queryVendorKpiWithRequest(vendorKpiFilterDTO);

        return ResponseVO.success(vendorKpiVOPageVO);
    }


    @ApiOperation("新增供应商绩效考核表返回VO")
    @PostMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorKpiReturnVO(@RequestBody VendorKpiDTO vendorKpiDTO) {

        log.info("saveVendorKpiReturnVO {}", vendorKpiDTO.toString());

        vendorKpiDTO.setCreatedUser(getAccount());
        VendorKpiVO vendorKpiVO = vendorKpiService.saveVendorKpiReturnVO(vendorKpiDTO);

        return ResponseVO.success(vendorKpiVO);
    }

    @ApiOperation("show供应商绩效考核表")
    @GetMapping(value = "/show/{vendorKpiId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorKpiId", dataType = "Long", value = "供应商绩效考核表", required = true),
    })

    public ResponseVO<VendorKpiVO> showInquirySheetById(@PathVariable("vendorKpiId")
                                                             @NotNull(message = "电子调查表ID不能为空") Long vendorKpiId) {
        log.info("showVendorKpiById {}", vendorKpiId);
        return ResponseVO.success(vendorKpiService.showVendorKpiById(vendorKpiId));
    }

    @ApiOperation("修改供应商绩效考核表WithDetail")
    @PutMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorKpiWithDetail(@RequestBody @Valid VendorKpiDTO vendorKpiDTO) {

        log.info("updateVendorKpiWithDetail {}", vendorKpiDTO.toString());

        vendorKpiDTO.setUpdatedUser(getAccount());

        vendorKpiService.updateVendorKpiWithDetail(vendorKpiDTO);

        return ResponseVO.success();
    }

    @ApiOperation("提交绩效单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO submitVendorKpi(@RequestBody VendorKpiDTO vendorKpiDTO) {

        log.info("submitVendorKpi {}", vendorKpiDTO.toString());
        if(vendorKpiDTO.getId()==null){
            vendorKpiDTO.setCreatedUser(getAccount());
            VendorKpiVO vendorKpiVO = vendorKpiService.saveVendorKpiReturnVO(vendorKpiDTO);
            vendorKpiDTO.setId(vendorKpiVO.getId());
        }
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle(vendorKpiDTO.getName());
        requestDTO.setRuleId(vendorKpiDTO.getRuleId());
//        requestDTO.setConfigVersion(inquirySheetDTO.getConfigVersion());
        requestDTO.setProcessId(vendorKpiDTO.getProcessId());
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);
        vendorKpiDTO.setRequestId(requestVO.getId());
        vendorKpiService.updateVendorKpi(vendorKpiDTO);

        return ResponseVO.success();
    }

    @ApiOperation("流转绩效单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreeVendorKpi(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreeVendorKpiDTO {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = approveJudgeStatus(requestFlowDTO);
        ResponseVO responseVO = IeRequestFlowResponseUtils.postProcess(processTransforDTO);

        return responseVO;
    }
    private ProcessTransforDTO approveJudgeStatus(RequestFlowDTO requestFlowDTO) {
        Long requestId=requestFlowDTO.getRequestId();
        RequestVO requestVO = IeRequestResponseUtils.queryRequestById(requestId);
        ProcessTransforDTO processTransforDTO = null;

        if (ZtbConstant.TOBESUBMITTED.equals(requestVO.getStatus().getName())) {//待提交
            processTransforDTO = new ProcessTransforDTO();
            processTransforDTO.setFlowOption(1);
            processTransforDTO.setUserId(requestFlowDTO.getUserId());
            processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
            processTransforDTO.setDescription(requestFlowDTO.getDescription());
            ProcessTransforDTO.AdditionalSign additionalSign = new ProcessTransforDTO.AdditionalSign();
            List<VendorKpiUserVO> vendorKpiUserVOS=vendorKpiService.getVendorKpiUserVosByRequestId(requestId);
            List<Long> userIds = vendorKpiUserVOS.stream().map(VendorKpiUserVO::getUserId).collect(Collectors.toList());
            additionalSign.setToUserIds(userIds);
            processTransforDTO.setAdditional(additionalSign);
        }else if(requestVO.getStatus().getName().equals("待评分")){
            //算分
            vendorKpiService.calculateScore(requestId);
            processTransforDTO = new ProcessTransforDTO();
            processTransforDTO.setFlowOption(1);
            processTransforDTO.setUserId(requestFlowDTO.getUserId());
            processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
            processTransforDTO.setDescription(requestFlowDTO.getDescription());
        }else{
            processTransforDTO = new ProcessTransforDTO();
            processTransforDTO.setFlowOption(1);
            processTransforDTO.setUserId(requestFlowDTO.getUserId());
            processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
            processTransforDTO.setDescription(requestFlowDTO.getDescription());
        }

        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();
        List<Long> roleIds = PceRoleResponseUtils.queryByUserId(requestFlowDTO.getUserId());
        agree.setRoleIds(roleIds);
        processTransforDTO.setAgree(agree);

        return processTransforDTO;
    }

}

package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceRoleResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.vo.*;
import com.xdcplus.xdcweb.biz.service.VendorQualifyReviewService;
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


/**
 * 供应商合格评审表(VendorQualifyReview)表服务控制层
 *
 * @author makejava
 * @since 2021-07-12 20:52:52
 */
@Api(tags = "供应商合格评审表(VendorQualifyReview)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorQualifyReview")
public class VendorQualifyReviewController extends AbstractController {

    @Autowired
    private final VendorQualifyReviewService vendorQualifyReviewService;

    @ApiOperation("新增供应商合格评审表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorQualifyReview(@RequestBody @Valid VendorQualifyReviewDTO vendorQualifyReviewDTO) {

        log.info("saveVendorQualifyReview {}", vendorQualifyReviewDTO.toString());

        vendorQualifyReviewDTO.setCreatedUser(getAccount());
        vendorQualifyReviewService.saveVendorQualifyReview(vendorQualifyReviewDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商合格评审表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQualifyReview(@RequestBody @Valid VendorQualifyReviewDTO vendorQualifyReviewDTO) {

        log.info("updateVendorQualifyReview {}", vendorQualifyReviewDTO.toString());

        vendorQualifyReviewDTO.setUpdatedUser(getAccount());
        vendorQualifyReviewService.updateVendorQualifyReview(vendorQualifyReviewDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商合格评审表")
    @DeleteMapping(value = "/{vendorQualifyReviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorQualifyReviewId", dataType = "Long", value = "供应商合格评审表ID", required = true),
    })
    public ResponseVO deleteVendorQualifyReviewLogical(@PathVariable("vendorQualifyReviewId")
                                                       @NotNull(message = "供应商合格评审表ID不能为空") Long vendorQualifyReviewId) {

        log.info("deleteVendorQualifyReviewLogical {}", vendorQualifyReviewId);

        vendorQualifyReviewService.deleteVendorQualifyReviewLogical(vendorQualifyReviewId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商合格评审表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorQualifyReviewVO>> findVendorQualifyReview(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO) {

        log.info("findVendorQualifyReview {}", vendorQualifyReviewFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQualifyReviewFilterDTO);

        return ResponseVO.success(vendorQualifyReviewService.queryVendorQualifyReview(vendorQualifyReviewFilterDTO));
    }

    @ApiOperation("查询供应商合格评审表(requst)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorQualifyReviewVO>> findVendorQualifyReviewWithRequest(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO) {

        log.info("findVendorQualifyReviewWithRequest {}", vendorQualifyReviewFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQualifyReviewFilterDTO);
        PageVO<VendorQualifyReviewVO> vendorQualifyReviewVOPageVO = vendorQualifyReviewService.queryVendorQualifyReviewWithRequest(vendorQualifyReviewFilterDTO);

        return ResponseVO.success(vendorQualifyReviewVOPageVO);
    }

    @ApiOperation("新增供应商合格评审表返回vo")
    @PostMapping(value = "/returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorQualifyReviewReturnVO(@RequestBody @Valid VendorQualifyReviewDTO vendorQualifyReviewDTO) {

        log.info("saveVendorQualifyReview {}", vendorQualifyReviewDTO.toString());

        vendorQualifyReviewDTO.setCreatedUser(getAccount());

        return ResponseVO.success(vendorQualifyReviewService.saveVendorQualifyReviewReturnVO(vendorQualifyReviewDTO));
    }

    @ApiOperation("show供应商合格评审表")
    @GetMapping(value = "/show/{vendorQualifyReviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorQualifyReviewId", dataType = "Long", value = "合格评审表ID", required = true),
    })
    public ResponseVO showVendorQualifyReview(@PathVariable("vendorQualifyReviewId")
                                              @NotNull(message = "合格评审表ID不能为空") Long vendorQualifyReviewId) {

        log.info("showVendorQualifyReview {}", vendorQualifyReviewId);

        VendorQualifyReviewVO vendorQualifyReviewVO = vendorQualifyReviewService.showVendorQualifyReview(vendorQualifyReviewId);

        return ResponseVO.success(vendorQualifyReviewVO);
    }

    @ApiOperation("修改供应商合格评审表WithDetail")
    @PutMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQualifyReviewWithDetail(@RequestBody @Valid VendorQualifyReviewDTO vendorQualifyReviewDTO) {

        log.info("updateVendorQualifyReviewWithDetail {}", vendorQualifyReviewDTO.toString());

        vendorQualifyReviewDTO.setUpdatedUser(getAccount());
        vendorQualifyReviewService.updateVendorQualifyReviewWithDetail(vendorQualifyReviewDTO);

        return ResponseVO.success();
    }

    @ApiOperation("提交合格评审单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO submitVendorQualifyReview(@RequestBody VendorQualifyReviewDTO vendorQualifyReviewDTO) {

        log.info("submitVendorQualifyReview {}", vendorQualifyReviewDTO.toString());
        if(vendorQualifyReviewDTO.getId()==null){
            vendorQualifyReviewDTO.setCreatedUser(getAccount());
            VendorQualifyReviewVO vendorQualifyReviewVO= vendorQualifyReviewService.saveVendorQualifyReviewReturnVO(vendorQualifyReviewDTO);
            vendorQualifyReviewDTO.setId(vendorQualifyReviewVO.getId());
        }
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle(vendorQualifyReviewDTO.getName());
        requestDTO.setRuleId(vendorQualifyReviewDTO.getRuleId());
//        requestDTO.setConfigVersion(inquirySheetDTO.getConfigVersion());
        requestDTO.setProcessId(vendorQualifyReviewDTO.getProcessId());
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);

        vendorQualifyReviewDTO.setRequestId(requestVO.getId());
        vendorQualifyReviewService.updateVendorQualifyReview(vendorQualifyReviewDTO);

        return ResponseVO.success();
    }

    @ApiOperation("流转合格评审单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreeVendorQualifyReview(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreeVendorQualifyReviewDTO {}", requestFlowDTO.toString());

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

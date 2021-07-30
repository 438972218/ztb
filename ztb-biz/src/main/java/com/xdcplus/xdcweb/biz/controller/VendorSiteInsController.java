package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceRoleResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Vendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.*;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsService;
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
 * 供应商现场考察表(VendorSiteIns)表服务控制层
 *
 * @author makejava
 * @since 2021-07-12 20:52:56
 */
@Api(tags = "供应商现场考察表(VendorSiteIns)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorSiteIns")
public class VendorSiteInsController extends AbstractController {

    @Autowired
    private final VendorSiteInsService vendorSiteInsService;

    @ApiOperation("新增供应商现场考察表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorSiteIns(@RequestBody @Valid VendorSiteInsDTO vendorSiteInsDTO) {

        log.info("saveVendorSiteIns {}", vendorSiteInsDTO.toString());

        vendorSiteInsDTO.setCreatedUser(getAccount());
        vendorSiteInsService.saveVendorSiteIns(vendorSiteInsDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商现场考察表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorSiteIns(@RequestBody @Valid VendorSiteInsDTO vendorSiteInsDTO) {

        log.info("updateVendorSiteIns {}", vendorSiteInsDTO.toString());

        vendorSiteInsDTO.setUpdatedUser(getAccount());
        vendorSiteInsService.updateVendorSiteIns(vendorSiteInsDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商现场考察表")
    @DeleteMapping(value = "/{vendorSiteInsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorSiteInsId", dataType = "Long", value = "供应商现场考察表ID", required = true),
    })
    public ResponseVO deleteVendorSiteInsLogical(@PathVariable("vendorSiteInsId")
                                                 @NotNull(message = "供应商现场考察表ID不能为空") Long vendorSiteInsId) {

        log.info("deleteVendorSiteInsLogical {}", vendorSiteInsId);

        vendorSiteInsService.deleteVendorSiteInsLogical(vendorSiteInsId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商现场考察表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorSiteInsVO>> findVendorSiteIns(VendorSiteInsFilterDTO vendorSiteInsFilterDTO) {

        log.info("findVendorSiteIns {}", vendorSiteInsFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorSiteInsFilterDTO);

        return ResponseVO.success(vendorSiteInsService.queryVendorSiteIns(vendorSiteInsFilterDTO));
    }

    @ApiOperation("查询供应商电子调查表(requst)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorSiteInsVO>> findVendorQuestionWithRequest(VendorSiteInsFilterDTO vendorSiteInsFilterDTO) {

        log.info("findVendorSiteInsWithRequest {}", vendorSiteInsFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorSiteInsFilterDTO);
        PageVO<VendorSiteInsVO> vendorSiteInsVOPageVO = vendorSiteInsService.queryVendorSiteInsWithRequest(vendorSiteInsFilterDTO);

        return ResponseVO.success(vendorSiteInsVOPageVO);
    }

    @ApiOperation("新增供应商现场考察表返回vo")
    @PostMapping(value = "/returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorSiteInsReturnVO(@RequestBody @Valid VendorSiteInsDTO vendorSiteInsDTO) {

        log.info("saveVendorSiteIns {}", vendorSiteInsDTO.toString());

        vendorSiteInsDTO.setCreatedUser(getAccount());

        return ResponseVO.success(vendorSiteInsService.saveVendorSiteInsReturnVO(vendorSiteInsDTO));
    }

    @ApiOperation("show供应商现场考察表")
    @GetMapping(value = "/show/{vendorSiteInsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorSiteInsId", dataType = "Long", value = "现场考察表ID", required = true),
    })
    public ResponseVO showVendorSiteIns(@PathVariable("vendorSiteInsId")
                                        @NotNull(message = "现场考察表ID不能为空") Long vendorSiteInsId) {

        log.info("showVendorSiteIns {}", vendorSiteInsId);

        VendorSiteInsVO vendorSiteInsVO = vendorSiteInsService.showVendorSiteIns(vendorSiteInsId);

        return ResponseVO.success(vendorSiteInsVO);
    }

    @ApiOperation("修改供应商现场考察表WithDetail")
    @PutMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorSiteInsWithDetail(@RequestBody @Valid VendorSiteInsDTO vendorSiteInsDTO) {

        log.info("updateVendorSiteInsWithDetail {}", vendorSiteInsDTO.toString());

        vendorSiteInsDTO.setUpdatedUser(getAccount());

        vendorSiteInsService.updateVendorSiteInsWithDetail(vendorSiteInsDTO);

        return ResponseVO.success();
    }

    @ApiOperation("提交现场考察单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO submitVendorSiteIns(@RequestBody VendorSiteInsDTO vendorSiteInsDTO) {

        log.info("submitVendorSiteIns {}", vendorSiteInsDTO.toString());
        if(vendorSiteInsDTO.getId()==null){
            vendorSiteInsDTO.setCreatedUser(getAccount());
            VendorSiteInsVO vendorSiteInsVO = vendorSiteInsService.saveVendorSiteInsReturnVO(vendorSiteInsDTO);
            vendorSiteInsDTO.setId(vendorSiteInsVO.getId());
        }
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle(vendorSiteInsDTO.getName());
        requestDTO.setRuleId(vendorSiteInsDTO.getRuleId());
//        requestDTO.setConfigVersion(vendorSiteInsDTO.getConfigVersion());
        requestDTO.setProcessId(vendorSiteInsDTO.getProcessId());
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);

        vendorSiteInsDTO.setRequestId(requestVO.getId());
        vendorSiteInsService.updateVendorSiteIns(vendorSiteInsDTO);

        return ResponseVO.success();
    }

    @ApiOperation("流转现场考察单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreeVendorQualifyReview(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreeVendorSiteInsDTO {}", requestFlowDTO.toString());

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
            List<VendorSiteInsUserVO> vendorSiteInsUserVOS=vendorSiteInsService.getVendorSiteInsUserVosByRequestId(requestId);
            List<Long> userIds = vendorSiteInsUserVOS.stream().map(VendorSiteInsUserVO::getUserId).collect(Collectors.toList());
            additionalSign.setToUserIds(userIds);
            processTransforDTO.setAdditional(additionalSign);
        }else if(requestVO.getStatus().getName().equals("待评分")){
            //算分
            vendorSiteInsService.calculateScore(requestId);
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

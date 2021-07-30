package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.config.AssemblyBuilder;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestFlowResponseUtils;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceRoleResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.xdcweb.biz.common.pojo.vo.*;
import com.xdcplus.xdcweb.biz.service.BidSheetService;
import com.xdcplus.xdcweb.biz.service.BidSpecialistScoreService;
import com.xdcplus.xdcweb.biz.service.BidSpecialistService;
import com.xdcplus.xdcweb.biz.service.BidVendorService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * bid招标单(BidSheet)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:31
 */
@Api(tags = "bid招标单(BidSheet)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidSheet")
public class BidSheetController extends AbstractController {

    @Autowired
    private BidSheetService bidSheetService;

    @Autowired
    private BidVendorService bidVendorService;

    @Autowired
    private BidSpecialistService bidSpecialistService;

    @Autowired
    private BidSpecialistScoreService bidSpecialistScoreService;

    @ApiOperation("新增bid招标单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidSheet(@RequestBody BidSheetDTO bidSheetDTO) {

        log.info("saveBidSheet {}", bidSheetDTO.toString());

        bidSheetDTO.setCreatedUser(getAccount());
        bidSheetService.saveBidSheet(bidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改bid招标单")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidSheet(@RequestBody BidSheetDTO bidSheetDTO) {

        log.info("updateBidSheet {}", bidSheetDTO.toString());

        bidSheetDTO.setUpdatedUser(getAccount());
        bidSheetService.updateBidSheet(bidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除bid招标单")
    @DeleteMapping(value = "/{bidSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidSheetId", dataType = "Long", value = "bid招标单ID", required = true),
    })
    public ResponseVO deleteBidSheetLogical(@PathVariable("bidSheetId")
                                            @NotNull(message = "bid招标单ID不能为空") Long bidSheetId) {

        log.info("deleteBidSheetLogical {}", bidSheetId);

        bidSheetService.deleteBidSheetLogical(bidSheetId);

        return ResponseVO.success();
    }

    @ApiOperation("查询bid招标单")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSheetVO>> findBidSheet(BidSheetFilterDTO bidSheetFilterDTO) {

        log.info("findBidSheet {}", bidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSheetFilterDTO);

        return ResponseVO.success(bidSheetService.queryBidSheet(bidSheetFilterDTO));
    }

    @ApiOperation("新增bid招标单返回VO")
    @PostMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidInvitationReturnVO(@RequestBody @Valid BidSheetDTO bidSheetDTO) {

        log.info("saveBidSheetReturnVO {}", bidSheetDTO.toString());

        bidSheetDTO.setCreatedUser(getAccount());

        bidSheetDTO.setTitle("招标单-" + bidSheetDTO.getCreatedUser() + "-" + DateUtil.formatDateTime(new Date()));

        BidSheetVO bidSheetVO = bidSheetService.saveBidSheetReturnVO(bidSheetDTO);

        return ResponseVO.success(bidSheetVO);
    }

    @ApiOperation("showbid招标单")
    @GetMapping(value = "show/{bidSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidSheetId", dataType = "Long", value = "bid招标单ID", required = true),
    })
    public ResponseVO showBidInvitation(@PathVariable("bidSheetId")
                                        @NotNull(message = "bid招标单ID不能为空") Long bidSheetId) {

        log.info("showBidInvitation {}", bidSheetId);

        BidSheetVO bidSheetVO = bidSheetService.showBidSheetById(bidSheetId);

        return ResponseVO.success(bidSheetVO);
    }

    @ApiOperation("查询招标单(供应商)")
    @GetMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSheetVO>> findBidSheetByVendor(BidSheetFilterDTO bidSheetFilterDTO) {

        log.info("findBidSheetByVendor {}", bidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSheetFilterDTO);
        PageVO<BidSheetVO> bidSheetVOPageVO = bidSheetService.queryBidSheetByVendor(bidSheetFilterDTO);

        return ResponseVO.success(bidSheetVOPageVO);
    }

    @ApiOperation("查询招标单(request)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSheetVO>> findBidSheetWithRequest(BidSheetFilterDTO bidSheetFilterDTO) {

        log.info("findBidSheetWithRequest {}", bidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSheetFilterDTO);
        PageVO<BidSheetVO> bidSheetVOPageVO = bidSheetService.queryBidSheetWithRequest(bidSheetFilterDTO);

        return ResponseVO.success(bidSheetVOPageVO);
    }

    @ApiOperation("提交审批招标单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<BidSheetVO> submitBidSheet(@RequestBody BidSheetDTO bidSheetDTO) {

        log.info("submitBidSheet {}", bidSheetDTO.toString());

        bidSheetDTO.setCreatedUser(getAccount());
        bidSheetDTO.setTitle(ZtbConstant.BID_TITLE + bidSheetDTO.getCreatedUser() + "-" + DateUtil.formatDateTime(new Date()));

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRuleId(bidSheetDTO.getRuleId());
        requestDTO.setProcessId(bidSheetDTO.getProcessId());
        requestDTO.setTitle(bidSheetDTO.getTitle());

        requestDTO.setCreatedUser(getAccount());
        submitBeforeJudgeStatus( bidSheetDTO, requestDTO);
        RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, true);

        bidSheetDTO.setRequestId(requestVO.getId());
        BidSheetVO bidSheetVO = bidSheetService.saveBidSheetReturnVO(bidSheetDTO);
        bidSheetDTO.setId(bidSheetVO.getId());
        submitAfterJudgeStatus(bidSheetDTO);

        return ResponseVO.success(bidSheetVO);
    }

    @ApiOperation("流转招标单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreeBidSheet(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreeBidSheet {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = approveBeforeJudgeStatus(requestFlowDTO);

        ResponseVO responseVO = IeRequestFlowResponseUtils.postProcess(processTransforDTO);

        approveAfterJudgeStatus(requestFlowDTO);

        return responseVO;
    }

    private void submitBeforeJudgeStatus(BidSheetDTO bidSheetDTO,RequestDTO requestDTO) {
        Circulation circulation=new Circulation();
            AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
            if(bidSheetDTO.getApprovalProcess()!=null){
                assemblyBuilder.addParameter("approvalProcess",bidSheetDTO.getApprovalProcess());
                circulation.setFlowConditions(assemblyBuilder.build());
            }
            circulation.setUserId(bidSheetDTO.getUserId());
            requestDTO.setCirculation(circulation);
    }

    private void submitAfterJudgeStatus(BidSheetDTO bidSheetDTO) {
        BidSheetVO bidSheetVO = bidSheetService.queryBidSheetById(bidSheetDTO.getId());
        if (bidSheetVO.getApprovalProcess() != null && !bidSheetVO.getApprovalProcess().equals(ZtbConstant.NONEED)) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setRuleId(1L);
            requestDTO.setProcessId(ZtbConstant.SHENGPI_PROCESS);
            requestDTO.setTitle(bidSheetVO.getTitle() + "-审批单");
            requestDTO.setCreatedUser(bidSheetVO.getCreatedUser());
            HashSet<Long> parentIds = new HashSet<>();
            parentIds.add(bidSheetVO.getRequestId());
            requestDTO.setParentIds(parentIds);

            RequestVO requestVO = IeRequestResponseUtils.saveRequest(requestDTO, false);
        }
    }

    private ProcessTransforDTO approveBeforeJudgeStatus(RequestFlowDTO requestFlowDTO) {
        RequestVO requestVO = IeRequestResponseUtils.queryRequestById(requestFlowDTO.getRequestId());
        ProcessTransforDTO processTransforDTO = null;
        if (requestVO.getStatus().getName().equals(ZtbConstant.BIDOPENING)) {
            if (StrUtil.equals(requestFlowDTO.getFlowOption(), "专家评分")) {
                //专家 前加签
                BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
                bidSpecialistFilterDTO.setBidSheetId(requestFlowDTO.getSheetId());
                bidSpecialistFilterDTO.setSpecialistGroupLeader(0);
                List<BidSpecialistVO> bidSpecialistVOS = bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO);
                if (CollectionUtil.isEmpty(bidSpecialistVOS)) {
                    log.error("approveJudgeStatus() specialist select fail");
                    throw new InvException(ResponseEnum.BID_SPECIALIST_SELECT_FAIL);
                }
                processTransforDTO = new ProcessTransforDTO();
                processTransforDTO.setFlowOption(6);
                processTransforDTO.setUserId(requestFlowDTO.getUserId());
                processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
                processTransforDTO.setDescription(requestFlowDTO.getDescription());
                ProcessTransforDTO.AdditionalSign additionalSign = new ProcessTransforDTO.AdditionalSign();
                List<Long> userIds = bidSpecialistVOS.stream().map(BidSpecialistVO::getUserId).collect(Collectors.toList());
                additionalSign.setToUserIds(userIds);
                processTransforDTO.setAdditional(additionalSign);

            } else if (StrUtil.equals(requestFlowDTO.getFlowOption(), "组长评分")) {
                //专家组长 后加签
                BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
                bidSpecialistFilterDTO.setBidSheetId(requestFlowDTO.getSheetId());
                bidSpecialistFilterDTO.setSpecialistGroupLeader(1);
                List<BidSpecialistVO> bidSpecialistVOS = bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO);
                if (CollectionUtil.isEmpty(bidSpecialistVOS)) {
                    log.error("approveJudgeStatus() specialist group select fail");
                    throw new InvException(ResponseEnum.BID_SPECIALIST_GROUP_SELECT_FAIL);
                }
                processTransforDTO = new ProcessTransforDTO();
                processTransforDTO.setFlowOption(5);
                processTransforDTO.setUserId(requestFlowDTO.getUserId());
                processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
                processTransforDTO.setDescription(requestFlowDTO.getDescription());
                ProcessTransforDTO.AdditionalSign additionalSign = new ProcessTransforDTO.AdditionalSign();
                additionalSign.setToUserIds(CollectionUtil.newArrayList(bidSpecialistVOS.get(0).getUserId()));
                processTransforDTO.setAdditional(additionalSign);
            }
        } else {
            processTransforDTO = new ProcessTransforDTO();
            processTransforDTO.setFlowOption(1);
            processTransforDTO.setUserId(requestFlowDTO.getUserId());
            processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
            processTransforDTO.setDescription(requestFlowDTO.getDescription());
            ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();
            Long requestId = requestFlowDTO.getRequestId();
            List<RequestVO> requestVOS = IeRequestResponseUtils.queryRequestByParentId(requestId);
            if (requestVO.getStatus().getName().equals(ZtbConstant.TOAUDIT)) {
                if (CollectionUtil.isNotEmpty(requestVOS)) {
                    RequestVO childRequestVO = requestVOS.get(requestVOS.size() - 1);
                    AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
                    assemblyBuilder.addParameter("requestRelationRequestStatusId",childRequestVO.getStatus().getId());
                    agree.setFlowConditions(assemblyBuilder.build());
                }
            } else if (requestVO.getStatus().getName().equals(ZtbConstant.AWARDOFBID)) {
                bidVendorService.updateBidVendorStatusByPricing(requestFlowDTO.getId());
            }
            List<Long> roleIds = PceRoleResponseUtils.queryByUserId(requestFlowDTO.getUserId());
            agree.setRoleIds(roleIds);
            processTransforDTO.setAgree(agree);
        }
        return processTransforDTO;
    }

    private void approveAfterJudgeStatus(RequestFlowDTO requestFlowDTO) {
        RequestVO requestVO = IeRequestResponseUtils.queryRequestById(requestFlowDTO.getRequestId());
        if (requestVO.getStatus().getName().equals(ZtbConstant.QIANJIAQIAN)) {
            //专家
            BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
            bidSpecialistFilterDTO.setBidSheetId(requestFlowDTO.getSheetId());
            bidSpecialistFilterDTO.setSpecialistGroupLeader(0);
            List<BidSpecialistVO> bidSpecialistVOS = bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO);
            if (CollectionUtil.isEmpty(bidSpecialistVOS)) {
                log.error("approveJudgeStatus() specialist select fail");
                throw new InvException(ResponseEnum.BID_SPECIALIST_SELECT_FAIL);
            }
            BidVendorFilterDTO bidVendorFilterDTO =new BidVendorFilterDTO();
            bidVendorFilterDTO.setBidSheetId(requestFlowDTO.getSheetId());
            bidVendorFilterDTO.setVendorStatus("已投标");
            List<BidVendorVO> bidVendorVOS = bidVendorService.queryBidVendorVOList(bidVendorFilterDTO);
            List<BidSpecialistScore> bidSpecialistScores =new ArrayList<>();
            for (BidSpecialistVO bidSpecialistVO : bidSpecialistVOS) {
                BidSpecialistScore bidSpecialistScore =new BidSpecialistScore();
                bidSpecialistScore.setUserId(bidSpecialistVO.getId());
                for (BidVendorVO bidVendorVO : bidVendorVOS) {
                    bidSpecialistScore.setBidVendorId(bidVendorVO.getId());
                    bidSpecialistScores.add(bidSpecialistScore);
                }
            }
            bidSpecialistScoreService.saveOrUpdateBatch(bidSpecialistScores);
        }
    }


}

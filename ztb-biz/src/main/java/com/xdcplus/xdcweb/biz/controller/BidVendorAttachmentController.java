package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.service.BidVendorAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Validation;
import javax.validation.constraints.NotNull;


/**
 * 招标投标方附件(BidVendorAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:34
 */
@Api(tags = "招标投标方附件(BidVendorAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidVendorAttachment")
public class BidVendorAttachmentController extends AbstractController {

    @Autowired
    private BidVendorAttachmentService bidVendorAttachmentService;

    @ApiOperation("新增招标投标方附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendorAttachment(@RequestBody BidVendorAttachmentDTO bidVendorAttachmentDTO) {

        log.info("saveBidVendorAttachment {}", bidVendorAttachmentDTO.toString());

        bidVendorAttachmentDTO.setCreatedUser(getAccount());
        bidVendorAttachmentService.saveBidVendorAttachment(bidVendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标投标方附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorAttachment(@RequestBody BidVendorAttachmentDTO bidVendorAttachmentDTO) {

        log.info("updateBidVendorAttachment {}", bidVendorAttachmentDTO.toString());

        bidVendorAttachmentDTO.setUpdatedUser(getAccount());
        bidVendorAttachmentService.updateBidVendorAttachment(bidVendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标投标方附件")
    @DeleteMapping(value = "/{bidVendorAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidVendorAttachmentId", dataType = "Long", value = "招标投标方附件ID", required = true),
    })
    public ResponseVO deleteBidVendorAttachmentLogical(@PathVariable("bidVendorAttachmentId")
                                                       @NotNull(message = "招标投标方附件ID不能为空") Long bidVendorAttachmentId) {

        log.info("deleteBidVendorAttachmentLogical {}", bidVendorAttachmentId);

        bidVendorAttachmentService.deleteBidVendorAttachmentLogical(bidVendorAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标投标方附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidVendorAttachmentVO>> findBidVendorAttachment(BidVendorAttachmentFilterDTO bidVendorAttachmentFilterDTO) {

        log.info("findBidVendorAttachment {}", bidVendorAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorAttachmentFilterDTO);

        return ResponseVO.success(bidVendorAttachmentService.queryBidVendorAttachment(bidVendorAttachmentFilterDTO));
    }


}

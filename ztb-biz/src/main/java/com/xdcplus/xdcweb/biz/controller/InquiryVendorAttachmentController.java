package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.service.InquiryVendorAttachmentService;
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
 * 询价供应商附件(InquiryVendorAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:27:50
 */
@Api(tags = "询价供应商附件(InquiryVendorAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("inquiryVendorAttachment")
public class InquiryVendorAttachmentController extends AbstractController {

    @Autowired
    private InquiryVendorAttachmentService inquiryVendorAttachmentService;

    @ApiOperation("新增询价供应商附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquiryVendorAttachment(@RequestBody InquiryVendorAttachmentDTO inquiryVendorAttachmentDTO) {

        log.info("saveInquiryVendorAttachment {}", inquiryVendorAttachmentDTO.toString());

        inquiryVendorAttachmentDTO.setCreatedUser(getAccount());
        inquiryVendorAttachmentService.saveInquiryVendorAttachment(inquiryVendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改询价供应商附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInquiryVendorAttachment(@RequestBody InquiryVendorAttachmentDTO inquiryVendorAttachmentDTO) {

        log.info("updateInquiryVendorAttachment {}", inquiryVendorAttachmentDTO.toString());

        inquiryVendorAttachmentDTO.setUpdatedUser(getAccount());
        inquiryVendorAttachmentService.updateInquiryVendorAttachment(inquiryVendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除询价供应商附件")
    @DeleteMapping(value = "/{inquiryVendorAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inquiryVendorAttachmentId", dataType = "Long", value = "询价供应商附件ID", required = true),
    })
    public ResponseVO deleteInquiryVendorAttachmentLogical(@PathVariable("inquiryVendorAttachmentId")
                                                           @NotNull(message = "询价供应商附件ID不能为空") Long inquiryVendorAttachmentId) {

        log.info("deleteInquiryVendorAttachmentLogical {}", inquiryVendorAttachmentId);

        inquiryVendorAttachmentService.deleteInquiryVendorAttachmentLogical(inquiryVendorAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询询价供应商附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquiryVendorAttachmentVO>> findInquiryVendorAttachment(InquiryVendorAttachmentFilterDTO inquiryVendorAttachmentFilterDTO) {

        log.info("findInquiryVendorAttachment {}", inquiryVendorAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryVendorAttachmentFilterDTO);

        return ResponseVO.success(inquiryVendorAttachmentService.queryInquiryVendorAttachment(inquiryVendorAttachmentFilterDTO));
    }


}

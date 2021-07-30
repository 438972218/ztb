package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryAttachmentVO;
import com.xdcplus.xdcweb.biz.service.InquiryAttachmentService;
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
 * 询价单附件(InquiryAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:27:39
 */
@Api(tags = "询价单附件(InquiryAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("inquiryAttachment")
public class InquiryAttachmentController extends AbstractController {

    @Autowired
    private InquiryAttachmentService inquiryAttachmentService;

    @ApiOperation("新增询价单附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquiryAttachment(@RequestBody InquiryAttachmentDTO inquiryAttachmentDTO) {

        log.info("saveInquiryAttachment {}", inquiryAttachmentDTO.toString());

        inquiryAttachmentDTO.setCreatedUser(getAccount());
        inquiryAttachmentService.saveInquiryAttachment(inquiryAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改询价单附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInquiryAttachment(@RequestBody InquiryAttachmentDTO inquiryAttachmentDTO) {

        log.info("updateInquiryAttachment {}", inquiryAttachmentDTO.toString());

        inquiryAttachmentDTO.setUpdatedUser(getAccount());
        inquiryAttachmentService.updateInquiryAttachment(inquiryAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除询价单附件")
    @DeleteMapping(value = "/{inquiryAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inquiryAttachmentId", dataType = "Long", value = "询价单附件ID", required = true),
    })
    public ResponseVO deleteInquiryAttachmentLogical(@PathVariable("inquiryAttachmentId")
                                                     @NotNull(message = "询价单附件ID不能为空") Long inquiryAttachmentId) {

        log.info("deleteInquiryAttachmentLogical {}", inquiryAttachmentId);

        inquiryAttachmentService.deleteInquiryAttachmentLogical(inquiryAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询询价单附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquiryAttachmentVO>> findInquiryAttachment(InquiryAttachmentFilterDTO inquiryAttachmentFilterDTO) {

        log.info("findInquiryAttachment {}", inquiryAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryAttachmentFilterDTO);

        return ResponseVO.success(inquiryAttachmentService.queryInquiryAttachment(inquiryAttachmentFilterDTO));
    }


}

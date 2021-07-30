package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorAttachmentDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorAttachmentFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorAttachmentVO;
import com.xdcplus.xdcweb.biz.service.PaidVendorAttachmentService;
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
 * 竞价供应商附件(PaidVendorAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:35:15
 */
@Api(tags = "竞价供应商附件(PaidVendorAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("paidVendorAttachment")
public class PaidVendorAttachmentController extends AbstractController {

    @Autowired
    private PaidVendorAttachmentService paidVendorAttachmentService;

    @ApiOperation("新增竞价供应商附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidVendorAttachment(@RequestBody PaidVendorAttachmentDTO paidVendorAttachmentDTO) {

        log.info("savePaidVendorAttachment {}", paidVendorAttachmentDTO.toString());

        paidVendorAttachmentDTO.setCreatedUser(getAccount());
        paidVendorAttachmentService.savePaidVendorAttachment(paidVendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改竞价供应商附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePaidVendorAttachment(@RequestBody PaidVendorAttachmentDTO paidVendorAttachmentDTO) {

        log.info("updatePaidVendorAttachment {}", paidVendorAttachmentDTO.toString());

        paidVendorAttachmentDTO.setUpdatedUser(getAccount());
        paidVendorAttachmentService.updatePaidVendorAttachment(paidVendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除竞价供应商附件")
    @DeleteMapping(value = "/{paidVendorAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidVendorAttachmentId", dataType = "Long", value = "竞价供应商附件ID", required = true),
    })
    public ResponseVO deletePaidVendorAttachmentLogical(@PathVariable("paidVendorAttachmentId")
                                                        @NotNull(message = "竞价供应商附件ID不能为空") Long paidVendorAttachmentId) {

        log.info("deletePaidVendorAttachmentLogical {}", paidVendorAttachmentId);

        paidVendorAttachmentService.deletePaidVendorAttachmentLogical(paidVendorAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价供应商附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidVendorAttachmentVO>> findPaidVendorAttachment(PaidVendorAttachmentFilterDTO paidVendorAttachmentFilterDTO) {

        log.info("findPaidVendorAttachment {}", paidVendorAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidVendorAttachmentFilterDTO);

        return ResponseVO.success(paidVendorAttachmentService.queryPaidVendorAttachment(paidVendorAttachmentFilterDTO));
    }


}

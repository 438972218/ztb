package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorVO;
import com.xdcplus.xdcweb.biz.service.InquiryVendorService;
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
 * 询价供应商(InquiryVendor)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:27:47
 */
@Api(tags = "询价供应商(InquiryVendor)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("inquiryVendor")
public class InquiryVendorController extends AbstractController {

    @Autowired
    private InquiryVendorService inquiryVendorService;

    @ApiOperation("新增询价供应商")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquiryVendor(@RequestBody InquiryVendorDTO inquiryVendorDTO) {

        log.info("saveInquiryVendor {}", inquiryVendorDTO.toString());

        inquiryVendorDTO.setCreatedUser(getAccount());
        inquiryVendorService.saveInquiryVendor(inquiryVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改询价供应商")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInquiryVendor(@RequestBody InquiryVendorDTO inquiryVendorDTO) {

        log.info("updateInquiryVendor {}", inquiryVendorDTO.toString());

        inquiryVendorDTO.setUpdatedUser(getAccount());
        inquiryVendorService.updateInquiryVendor(inquiryVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除询价供应商")
    @DeleteMapping(value = "/{inquiryVendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inquiryVendorId", dataType = "Long", value = "询价供应商ID", required = true),
    })
    public ResponseVO deleteInquiryVendorLogical(@PathVariable("inquiryVendorId")
                                                 @NotNull(message = "询价供应商ID不能为空") Long inquiryVendorId) {

        log.info("deleteInquiryVendorLogical {}", inquiryVendorId);

        inquiryVendorService.deleteInquiryVendorLogical(inquiryVendorId);

        return ResponseVO.success();
    }

    @ApiOperation("查询询价供应商")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquiryVendorVO>> findInquiryVendor(InquiryVendorFilterDTO inquiryVendorFilterDTO) {

        log.info("findInquiryVendor {}", inquiryVendorFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryVendorFilterDTO);

        return ResponseVO.success(inquiryVendorService.queryInquiryVendor(inquiryVendorFilterDTO));
    }

    @ApiOperation("供应商修改询价供应商（状态）")
    @PutMapping(value = "status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInquiryVendorStatus(@RequestBody InquiryVendorDTO inquiryVendorDTO) {

        log.info("updateInquiryVendorStatus {}", inquiryVendorDTO.toString());

        inquiryVendorDTO.setUpdatedUser(getAccount());
        inquiryVendorService.updateInquiryVendorStatus(inquiryVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("询价供应商参与/不参与询价单")
    @PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO joinInquiryVendor(@RequestBody InquiryVendorDTO inquiryVendorDTO) {

        log.info("saveInquiryVendor {}", inquiryVendorDTO.toString());

        inquiryVendorDTO.setCreatedUser(getAccount());
        inquiryVendorService.saveInquiryVendor(inquiryVendorDTO);

        return ResponseVO.success();
    }


}

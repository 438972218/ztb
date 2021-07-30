package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewDetailVO;
import com.xdcplus.xdcweb.biz.service.VendorQualifyReviewDetailService;
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


import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;


/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表服务控制层
 *
 * @author makejava
 * @since 2021-07-12 20:52:53
 */
@Api(tags = "供应商合格评审表明细(VendorQualifyReviewDetail)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorQualifyReviewDetail")
public class VendorQualifyReviewDetailController extends AbstractController {

    @Autowired
    private final VendorQualifyReviewDetailService vendorQualifyReviewDetailService;

    @ApiOperation("新增供应商合格评审表明细")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorQualifyReviewDetail(@RequestBody @Valid VendorQualifyReviewDetailDTO vendorQualifyReviewDetailDTO) {

        log.info("saveVendorQualifyReviewDetail {}", vendorQualifyReviewDetailDTO.toString());

        vendorQualifyReviewDetailDTO.setCreatedUser(getAccount());
        vendorQualifyReviewDetailService.saveVendorQualifyReviewDetail(vendorQualifyReviewDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商合格评审表明细")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQualifyReviewDetail(@RequestBody @Valid VendorQualifyReviewDetailDTO vendorQualifyReviewDetailDTO) {

        log.info("updateVendorQualifyReviewDetail {}", vendorQualifyReviewDetailDTO.toString());

        vendorQualifyReviewDetailDTO.setUpdatedUser(getAccount());
        vendorQualifyReviewDetailService.updateVendorQualifyReviewDetail(vendorQualifyReviewDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商合格评审表明细")
    @DeleteMapping(value = "/{vendorQualifyReviewDetailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorQualifyReviewDetailId", dataType = "Long", value = "供应商合格评审表明细ID", required = true),
    })
    public ResponseVO deleteVendorQualifyReviewDetailLogical(@PathVariable("vendorQualifyReviewDetailId")
                                                             @NotNull(message = "供应商合格评审表明细ID不能为空") Long vendorQualifyReviewDetailId) {

        log.info("deleteVendorQualifyReviewDetailLogical {}", vendorQualifyReviewDetailId);

        vendorQualifyReviewDetailService.deleteVendorQualifyReviewDetailLogical(vendorQualifyReviewDetailId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商合格评审表明细")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorQualifyReviewDetailVO>> findVendorQualifyReviewDetail(VendorQualifyReviewDetailFilterDTO vendorQualifyReviewDetailFilterDTO) {

        log.info("findVendorQualifyReviewDetail {}", vendorQualifyReviewDetailFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQualifyReviewDetailFilterDTO);

        return ResponseVO.success(vendorQualifyReviewDetailService.queryVendorQualifyReviewDetail(vendorQualifyReviewDetailFilterDTO));
    }


}

package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionDetailVO;
import com.xdcplus.xdcweb.biz.service.VendorQuestionDetailService;
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
 * 供应商电子调查表明细(VendorQuestionDetail)表服务控制层
 *
 * @author makejava
 * @since 2021-07-14 14:51:22
 */
@Api(tags = "供应商电子调查表明细(VendorQuestionDetail)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorQuestionDetail")
public class VendorQuestionDetailController extends AbstractController {

    @Autowired
    private final VendorQuestionDetailService vendorQuestionDetailService;

    @ApiOperation("新增供应商电子调查表明细")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorQuestionDetail(@RequestBody @Valid VendorQuestionDetailDTO vendorQuestionDetailDTO) {

        log.info("saveVendorQuestionDetail {}", vendorQuestionDetailDTO.toString());

        vendorQuestionDetailDTO.setCreatedUser(getAccount());
        vendorQuestionDetailService.saveVendorQuestionDetail(vendorQuestionDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商电子调查表明细")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQuestionDetail(@RequestBody @Valid VendorQuestionDetailDTO vendorQuestionDetailDTO) {

        log.info("updateVendorQuestionDetail {}", vendorQuestionDetailDTO.toString());

        vendorQuestionDetailDTO.setUpdatedUser(getAccount());
        vendorQuestionDetailService.updateVendorQuestionDetail(vendorQuestionDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商电子调查表明细")
    @DeleteMapping(value = "/{vendorQuestionDetailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorQuestionDetailId", dataType = "Long", value = "供应商电子调查表明细ID", required = true),
    })
    public ResponseVO deleteVendorQuestionDetailLogical(@PathVariable("vendorQuestionDetailId")
                                                        @NotNull(message = "供应商电子调查表明细ID不能为空") Long vendorQuestionDetailId) {

        log.info("deleteVendorQuestionDetailLogical {}", vendorQuestionDetailId);

        vendorQuestionDetailService.deleteVendorQuestionDetailLogical(vendorQuestionDetailId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商电子调查表明细")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorQuestionDetailVO>> findVendorQuestionDetail(VendorQuestionDetailFilterDTO vendorQuestionDetailFilterDTO) {

        log.info("findVendorQuestionDetail {}", vendorQuestionDetailFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQuestionDetailFilterDTO);

        return ResponseVO.success(vendorQuestionDetailService.queryVendorQuestionDetail(vendorQuestionDetailFilterDTO));
    }


}

package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailVO;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsDetailService;
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
 * 供应商现场考察表明细(VendorSiteInsDetail)表服务控制层
 *
 * @author makejava
 * @since 2021-07-15 10:53:33
 */
@Api(tags = "供应商现场考察表明细(VendorSiteInsDetail)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorSiteInsDetail")
public class VendorSiteInsDetailController extends AbstractController {

    @Autowired
    private final VendorSiteInsDetailService vendorSiteInsDetailService;

    @ApiOperation("新增供应商现场考察表明细")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorSiteInsDetail(@RequestBody @Valid VendorSiteInsDetailDTO vendorSiteInsDetailDTO) {

        log.info("saveVendorSiteInsDetail {}", vendorSiteInsDetailDTO.toString());

        vendorSiteInsDetailDTO.setCreatedUser(getAccount());
        vendorSiteInsDetailService.saveVendorSiteInsDetail(vendorSiteInsDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商现场考察表明细")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorSiteInsDetail(@RequestBody @Valid VendorSiteInsDetailDTO vendorSiteInsDetailDTO) {

        log.info("updateVendorSiteInsDetail {}", vendorSiteInsDetailDTO.toString());

        vendorSiteInsDetailDTO.setUpdatedUser(getAccount());
        vendorSiteInsDetailService.updateVendorSiteInsDetail(vendorSiteInsDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商现场考察表明细")
    @DeleteMapping(value = "/{vendorSiteInsDetailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorSiteInsDetailId", dataType = "Long", value = "供应商现场考察表明细ID", required = true),
    })
    public ResponseVO deleteVendorSiteInsDetailLogical(@PathVariable("vendorSiteInsDetailId")
                                                       @NotNull(message = "供应商现场考察表明细ID不能为空") Long vendorSiteInsDetailId) {

        log.info("deleteVendorSiteInsDetailLogical {}", vendorSiteInsDetailId);

        vendorSiteInsDetailService.deleteVendorSiteInsDetailLogical(vendorSiteInsDetailId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商现场考察表明细")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorSiteInsDetailVO>> findVendorSiteInsDetail(VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO) {

        log.info("findVendorSiteInsDetail {}", vendorSiteInsDetailFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorSiteInsDetailFilterDTO);

        return ResponseVO.success(vendorSiteInsDetailService.queryVendorSiteInsDetail(vendorSiteInsDetailFilterDTO));
    }


}

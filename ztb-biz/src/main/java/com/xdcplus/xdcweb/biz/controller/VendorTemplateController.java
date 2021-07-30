package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateVO;
import com.xdcplus.xdcweb.biz.service.VendorTemplateService;
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
 * 模板表(VendorTemplate)表服务控制层
 *
 * @author makejava
 * @since 2021-07-15 10:54:09
 */
@Api(tags = "模板表(VendorTemplate)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorTemplate")
public class VendorTemplateController extends AbstractController {

    @Autowired
    private final VendorTemplateService vendorTemplateService;

    @ApiOperation("新增模板表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorTemplate(@RequestBody @Valid VendorTemplateDTO vendorTemplateDTO) {

        log.info("saveVendorTemplate {}", vendorTemplateDTO.toString());

        vendorTemplateDTO.setCreatedUser(getAccount());
        vendorTemplateService.saveVendorTemplate(vendorTemplateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改模板表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorTemplate(@RequestBody @Valid VendorTemplateDTO vendorTemplateDTO) {

        log.info("updateVendorTemplate {}", vendorTemplateDTO.toString());

        vendorTemplateDTO.setUpdatedUser(getAccount());
        vendorTemplateService.updateVendorTemplate(vendorTemplateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除模板表")
    @DeleteMapping(value = "/{vendorTemplateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorTemplateId", dataType = "Long", value = "模板表ID", required = true),
    })
    public ResponseVO deleteVendorTemplateLogical(@PathVariable("vendorTemplateId")
                                                  @NotNull(message = "模板表ID不能为空") Long vendorTemplateId) {

        log.info("deleteVendorTemplateLogical {}", vendorTemplateId);

        vendorTemplateService.deleteVendorTemplateLogical(vendorTemplateId);

        return ResponseVO.success();
    }

    @ApiOperation("查询模板表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorTemplateVO>> findVendorTemplate(VendorTemplateFilterDTO vendorTemplateFilterDTO) {

        log.info("findVendorTemplate {}", vendorTemplateFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorTemplateFilterDTO);

        return ResponseVO.success(vendorTemplateService.queryVendorTemplate(vendorTemplateFilterDTO));
    }


}

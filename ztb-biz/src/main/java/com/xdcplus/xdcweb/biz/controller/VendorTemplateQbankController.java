package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorTemplateQbankFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorTemplateQbankVO;
import com.xdcplus.xdcweb.biz.service.VendorTemplateQbankService;
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
 * 模板题库中间表(VendorTemplateQbank)表服务控制层
 *
 * @author makejava
 * @since 2021-07-15 10:16:47
 */
@Api(tags = "模板题库中间表(VendorTemplateQbank)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorTemplateQbank")
public class VendorTemplateQbankController extends AbstractController {

    @Autowired
    private final VendorTemplateQbankService vendorTemplateQbankService;

    @ApiOperation("新增模板题库中间表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorTemplateQbank(@RequestBody @Valid VendorTemplateQbankDTO vendorTemplateQbankDTO) {

        log.info("saveVendorTemplateQbank {}", vendorTemplateQbankDTO.toString());

        vendorTemplateQbankDTO.setCreatedUser(getAccount());
        vendorTemplateQbankService.saveVendorTemplateQbank(vendorTemplateQbankDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改模板题库中间表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorTemplateQbank(@RequestBody @Valid VendorTemplateQbankDTO vendorTemplateQbankDTO) {

        log.info("updateVendorTemplateQbank {}", vendorTemplateQbankDTO.toString());

        vendorTemplateQbankDTO.setUpdatedUser(getAccount());
        vendorTemplateQbankService.updateVendorTemplateQbank(vendorTemplateQbankDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除模板题库中间表")
    @DeleteMapping(value = "/{vendorTemplateQbankId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorTemplateQbankId", dataType = "Long", value = "模板题库中间表ID", required = true),
    })
    public ResponseVO deleteVendorTemplateQbankLogical(@PathVariable("vendorTemplateQbankId")
                                                       @NotNull(message = "模板题库中间表ID不能为空") Long vendorTemplateQbankId) {

        log.info("deleteVendorTemplateQbankLogical {}", vendorTemplateQbankId);

        vendorTemplateQbankService.deleteVendorTemplateQbankLogical(vendorTemplateQbankId);

        return ResponseVO.success();
    }

    @ApiOperation("查询模板题库中间表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorTemplateQbankVO>> findVendorTemplateQbank(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO) {

        log.info("findVendorTemplateQbank {}", vendorTemplateQbankFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorTemplateQbankFilterDTO);

        return ResponseVO.success(vendorTemplateQbankService.queryVendorTemplateQbank(vendorTemplateQbankFilterDTO));
    }

    @ApiOperation("查询模板题库中间表返回VO")
    @GetMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorTemplateQbankVO>> findVendorTemplateQbankReturnVO(VendorTemplateQbankFilterDTO vendorTemplateQbankFilterDTO) {

        log.info("findVendorTemplateQbank {}", vendorTemplateQbankFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorTemplateQbankFilterDTO);

        return ResponseVO.success(vendorTemplateQbankService.queryVendorTemplateQbankReturnVO(vendorTemplateQbankFilterDTO));
    }


}

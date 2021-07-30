package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCertificateVO;
import com.xdcplus.xdcweb.biz.service.VendorRequestCertificateService;
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

import javax.validation.Validation;
import javax.validation.constraints.NotNull;


/**
 * 供应商注册单证书(VendorRequestCertificate)表服务控制层
 *
 * @author makejava
 * @since 2021-07-26 15:47:59
 */
@Api(tags = "供应商注册单证书(VendorRequestCertificate)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorRequestCertificate")
public class VendorRequestCertificateController extends AbstractController {

    @Autowired
    private final VendorRequestCertificateService vendorRequestCertificateService;

    @ApiOperation("新增供应商注册单证书")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorRequestCertificate(@RequestBody VendorRequestCertificateDTO vendorRequestCertificateDTO) {

        log.info("saveVendorRequestCertificate {}", vendorRequestCertificateDTO.toString());

        vendorRequestCertificateDTO.setCreatedUser(getAccount());
        vendorRequestCertificateService.saveVendorRequestCertificate(vendorRequestCertificateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商注册单证书")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorRequestCertificate(@RequestBody VendorRequestCertificateDTO vendorRequestCertificateDTO) {

        log.info("updateVendorRequestCertificate {}", vendorRequestCertificateDTO.toString());

        vendorRequestCertificateDTO.setUpdatedUser(getAccount());
        vendorRequestCertificateService.updateVendorRequestCertificate(vendorRequestCertificateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商注册单证书")
    @DeleteMapping(value = "/{vendorRequestCertificateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorRequestCertificateId", dataType = "Long", value = "供应商注册单证书ID", required = true),
    })
    public ResponseVO deleteVendorRequestCertificateLogical(@PathVariable("vendorRequestCertificateId")
                                                            @NotNull(message = "供应商注册单证书ID不能为空") Long vendorRequestCertificateId) {

        log.info("deleteVendorRequestCertificateLogical {}", vendorRequestCertificateId);

        vendorRequestCertificateService.deleteVendorRequestCertificateLogical(vendorRequestCertificateId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商注册单证书")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorRequestCertificateVO>> findVendorRequestCertificate(VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO) {

        log.info("findVendorRequestCertificate {}", vendorRequestCertificateFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorRequestCertificateFilterDTO);

        return ResponseVO.success(vendorRequestCertificateService.queryVendorRequestCertificate(vendorRequestCertificateFilterDTO));
    }


}

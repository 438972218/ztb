package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCertificateVO;
import com.xdcplus.xdcweb.biz.service.VendorCertificateService;
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
 * 供应商证书(VendorCertificate)表服务控制层
 *
 * @author makejava
 * @since 2021-07-12 20:52:49
 */
@Api(tags = "供应商证书(VendorCertificate)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorCertificate")
public class VendorCertificateController extends AbstractController {

    @Autowired
    private final VendorCertificateService vendorCertificateService;

    @ApiOperation("新增供应商证书")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorCertificate(@RequestBody @Valid VendorCertificateDTO vendorCertificateDTO) {

        log.info("saveVendorCertificate {}", vendorCertificateDTO.toString());

        vendorCertificateDTO.setCreatedUser(getAccount());
        vendorCertificateService.saveVendorCertificate(vendorCertificateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商证书")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorCertificate(@RequestBody @Valid VendorCertificateDTO vendorCertificateDTO) {

        log.info("updateVendorCertificate {}", vendorCertificateDTO.toString());

        vendorCertificateDTO.setUpdatedUser(getAccount());
        vendorCertificateService.updateVendorCertificate(vendorCertificateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商证书")
    @DeleteMapping(value = "/{vendorCertificateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorCertificateId", dataType = "Long", value = "供应商证书ID", required = true),
    })
    public ResponseVO deleteVendorCertificateLogical(@PathVariable("vendorCertificateId")
                                                     @NotNull(message = "供应商证书ID不能为空") Long vendorCertificateId) {

        log.info("deleteVendorCertificateLogical {}", vendorCertificateId);

        vendorCertificateService.deleteVendorCertificateLogical(vendorCertificateId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商证书")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorCertificateVO>> findVendorCertificate(VendorCertificateFilterDTO vendorCertificateFilterDTO) {

        log.info("findVendorCertificate {}", vendorCertificateFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorCertificateFilterDTO);

        return ResponseVO.success(vendorCertificateService.queryVendorCertificate(vendorCertificateFilterDTO));
    }


}

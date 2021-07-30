package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsUserVO;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsUserService;
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
 * 供应商现场考察人员表(VendorSiteInsUser)表服务控制层
 *
 * @author makejava
 * @since 2021-07-20 15:47:33
 */
@Api(tags = "供应商现场考察人员表(VendorSiteInsUser)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorSiteInsUser")
public class VendorSiteInsUserController extends AbstractController {

    @Autowired
    private final VendorSiteInsUserService vendorSiteInsUserService;

    @ApiOperation("新增供应商现场考察人员表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorSiteInsUser(@RequestBody @Valid VendorSiteInsUserDTO vendorSiteInsUserDTO) {

        log.info("saveVendorSiteInsUser {}", vendorSiteInsUserDTO.toString());

        vendorSiteInsUserDTO.setCreatedUser(getAccount());
        vendorSiteInsUserService.saveVendorSiteInsUser(vendorSiteInsUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商现场考察人员表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorSiteInsUser(@RequestBody @Valid VendorSiteInsUserDTO vendorSiteInsUserDTO) {

        log.info("updateVendorSiteInsUser {}", vendorSiteInsUserDTO.toString());

        vendorSiteInsUserDTO.setUpdatedUser(getAccount());
        vendorSiteInsUserService.updateVendorSiteInsUser(vendorSiteInsUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商现场考察人员表")
    @DeleteMapping(value = "/{vendorSiteInsUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorSiteInsUserId", dataType = "Long", value = "供应商现场考察人员表ID", required = true),
    })
    public ResponseVO deleteVendorSiteInsUserLogical(@PathVariable("vendorSiteInsUserId")
                                                     @NotNull(message = "供应商现场考察人员表ID不能为空") Long vendorSiteInsUserId) {

        log.info("deleteVendorSiteInsUserLogical {}", vendorSiteInsUserId);

        vendorSiteInsUserService.deleteVendorSiteInsUserLogical(vendorSiteInsUserId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商现场考察人员表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorSiteInsUserVO>> findVendorSiteInsUser(VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO) {

        log.info("findVendorSiteInsUser {}", vendorSiteInsUserFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorSiteInsUserFilterDTO);

        return ResponseVO.success(vendorSiteInsUserService.queryVendorSiteInsUser(vendorSiteInsUserFilterDTO));
    }


}

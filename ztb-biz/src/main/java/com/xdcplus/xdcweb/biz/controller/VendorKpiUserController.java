package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiUserVO;
import com.xdcplus.xdcweb.biz.service.VendorKpiUserService;
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
 * 供应商绩效人员表(VendorKpiUser)表服务控制层
 *
 * @author makejava
 * @since 2021-07-22 09:48:12
 */
@Api(tags = "供应商绩效人员表(VendorKpiUser)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorKpiUser")
public class VendorKpiUserController extends AbstractController {

    @Autowired
    private final VendorKpiUserService vendorKpiUserService;

    @ApiOperation("新增供应商绩效人员表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorKpiUser(@RequestBody VendorKpiUserDTO vendorKpiUserDTO) {

        log.info("saveVendorKpiUser {}", vendorKpiUserDTO.toString());

        vendorKpiUserDTO.setCreatedUser(getAccount());
        vendorKpiUserService.saveVendorKpiUser(vendorKpiUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商绩效人员表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorKpiUser(@RequestBody VendorKpiUserDTO vendorKpiUserDTO) {

        log.info("updateVendorKpiUser {}", vendorKpiUserDTO.toString());

        vendorKpiUserDTO.setUpdatedUser(getAccount());
        vendorKpiUserService.updateVendorKpiUser(vendorKpiUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商绩效人员表")
    @DeleteMapping(value = "/{vendorKpiUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorKpiUserId", dataType = "Long", value = "供应商绩效人员表ID", required = true),
    })
    public ResponseVO deleteVendorKpiUserLogical(@PathVariable("vendorKpiUserId")
                                                 @NotNull(message = "供应商绩效人员表ID不能为空") Long vendorKpiUserId) {

        log.info("deleteVendorKpiUserLogical {}", vendorKpiUserId);

        vendorKpiUserService.deleteVendorKpiUserLogical(vendorKpiUserId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商绩效人员表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorKpiUserVO>> findVendorKpiUser(VendorKpiUserFilterDTO vendorKpiUserFilterDTO) {

        log.info("findVendorKpiUser {}", vendorKpiUserFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorKpiUserFilterDTO);

        return ResponseVO.success(vendorKpiUserService.queryVendorKpiUser(vendorKpiUserFilterDTO));
    }


}

package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailUserVO;
import com.xdcplus.xdcweb.biz.service.VendorKpiDetailUserService;
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
 * 供应商绩效人员评价表(VendorKpiDetailUser)表服务控制层
 *
 * @author makejava
 * @since 2021-07-22 10:18:22
 */
@Api(tags = "供应商绩效人员评价表(VendorKpiDetailUser)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorKpiDetailUser")
public class VendorKpiDetailUserController extends AbstractController {

    @Autowired
    private final VendorKpiDetailUserService vendorKpiDetailUserService;

    @ApiOperation("新增供应商绩效人员评价表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorKpiDetailUser(@RequestBody VendorKpiDetailUserDTO vendorKpiDetailUserDTO) {

        log.info("saveVendorKpiDetailUser {}", vendorKpiDetailUserDTO.toString());

        vendorKpiDetailUserDTO.setCreatedUser(getAccount());
        vendorKpiDetailUserService.saveVendorKpiDetailUser(vendorKpiDetailUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商绩效人员评价表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorKpiDetailUser(@RequestBody VendorKpiDetailUserDTO vendorKpiDetailUserDTO) {

        log.info("updateVendorKpiDetailUser {}", vendorKpiDetailUserDTO.toString());

        vendorKpiDetailUserDTO.setUpdatedUser(getAccount());
        vendorKpiDetailUserService.updateVendorKpiDetailUser(vendorKpiDetailUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商绩效人员评价表")
    @DeleteMapping(value = "/{vendorKpiDetailUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorKpiDetailUserId", dataType = "Long", value = "供应商绩效人员评价表ID", required = true),
    })
    public ResponseVO deleteVendorKpiDetailUserLogical(@PathVariable("vendorKpiDetailUserId")
                                                       @NotNull(message = "供应商绩效人员评价表ID不能为空") Long vendorKpiDetailUserId) {

        log.info("deleteVendorKpiDetailUserLogical {}", vendorKpiDetailUserId);

        vendorKpiDetailUserService.deleteVendorKpiDetailUserLogical(vendorKpiDetailUserId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商绩效人员评价表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorKpiDetailUserVO>> findVendorKpiDetailUser(VendorKpiDetailUserFilterDTO vendorKpiDetailUserFilterDTO) {

        log.info("findVendorKpiDetailUser {}", vendorKpiDetailUserFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorKpiDetailUserFilterDTO);

        return ResponseVO.success(vendorKpiDetailUserService.queryVendorKpiDetailUser(vendorKpiDetailUserFilterDTO));
    }


}

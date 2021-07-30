package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailUserVO;
import com.xdcplus.xdcweb.biz.service.VendorSiteInsDetailUserService;
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
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表服务控制层
 *
 * @author makejava
 * @since 2021-07-20 15:47:30
 */
@Api(tags = "供应商现场考察人员评价表(VendorSiteInsDetailUser)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorSiteInsDetailUser")
public class VendorSiteInsDetailUserController extends AbstractController {

    @Autowired
    private final VendorSiteInsDetailUserService vendorSiteInsDetailUserService;

    @ApiOperation("新增供应商现场考察人员评价表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorSiteInsDetailUser(@RequestBody @Valid VendorSiteInsDetailUserDTO vendorSiteInsDetailUserDTO) {

        log.info("saveVendorSiteInsDetailUser {}", vendorSiteInsDetailUserDTO.toString());

        vendorSiteInsDetailUserDTO.setCreatedUser(getAccount());
        vendorSiteInsDetailUserService.saveVendorSiteInsDetailUser(vendorSiteInsDetailUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商现场考察人员评价表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorSiteInsDetailUser(@RequestBody @Valid VendorSiteInsDetailUserDTO vendorSiteInsDetailUserDTO) {

        log.info("updateVendorSiteInsDetailUser {}", vendorSiteInsDetailUserDTO.toString());

        vendorSiteInsDetailUserDTO.setUpdatedUser(getAccount());
        vendorSiteInsDetailUserService.updateVendorSiteInsDetailUser(vendorSiteInsDetailUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商现场考察人员评价表")
    @DeleteMapping(value = "/{vendorSiteInsDetailUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorSiteInsDetailUserId", dataType = "Long", value = "供应商现场考察人员评价表ID", required = true),
    })
    public ResponseVO deleteVendorSiteInsDetailUserLogical(@PathVariable("vendorSiteInsDetailUserId")
                                                           @NotNull(message = "供应商现场考察人员评价表ID不能为空") Long vendorSiteInsDetailUserId) {

        log.info("deleteVendorSiteInsDetailUserLogical {}", vendorSiteInsDetailUserId);

        vendorSiteInsDetailUserService.deleteVendorSiteInsDetailUserLogical(vendorSiteInsDetailUserId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商现场考察人员评价表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorSiteInsDetailUserVO>> findVendorSiteInsDetailUser(VendorSiteInsDetailUserFilterDTO vendorSiteInsDetailUserFilterDTO) {

        log.info("findVendorSiteInsDetailUser {}", vendorSiteInsDetailUserFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorSiteInsDetailUserFilterDTO);

        return ResponseVO.success(vendorSiteInsDetailUserService.queryVendorSiteInsDetailUser(vendorSiteInsDetailUserFilterDTO));
    }


}

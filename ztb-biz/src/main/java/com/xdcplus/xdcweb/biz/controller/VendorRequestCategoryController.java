package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCategoryVO;
import com.xdcplus.xdcweb.biz.service.VendorRequestCategoryService;
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
 * 供应商注册单品类(VendorRequestCategory)表服务控制层
 *
 * @author makejava
 * @since 2021-07-26 15:47:58
 */
@Api(tags = "供应商注册单品类(VendorRequestCategory)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorRequestCategory")
public class VendorRequestCategoryController extends AbstractController {

    @Autowired
    private final VendorRequestCategoryService vendorRequestCategoryService;

    @ApiOperation("新增供应商注册单品类")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorRequestCategory(@RequestBody VendorRequestCategoryDTO vendorRequestCategoryDTO) {

        log.info("saveVendorRequestCategory {}", vendorRequestCategoryDTO.toString());

        vendorRequestCategoryDTO.setCreatedUser(getAccount());
        vendorRequestCategoryService.saveVendorRequestCategory(vendorRequestCategoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商注册单品类")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorRequestCategory(@RequestBody VendorRequestCategoryDTO vendorRequestCategoryDTO) {

        log.info("updateVendorRequestCategory {}", vendorRequestCategoryDTO.toString());

        vendorRequestCategoryDTO.setUpdatedUser(getAccount());
        vendorRequestCategoryService.updateVendorRequestCategory(vendorRequestCategoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商注册单品类")
    @DeleteMapping(value = "/{vendorRequestCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorRequestCategoryId", dataType = "Long", value = "供应商注册单品类ID", required = true),
    })
    public ResponseVO deleteVendorRequestCategoryLogical(@PathVariable("vendorRequestCategoryId")
                                                         @NotNull(message = "供应商注册单品类ID不能为空") Long vendorRequestCategoryId) {

        log.info("deleteVendorRequestCategoryLogical {}", vendorRequestCategoryId);

        vendorRequestCategoryService.deleteVendorRequestCategoryLogical(vendorRequestCategoryId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商注册单品类")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorRequestCategoryVO>> findVendorRequestCategory(VendorRequestCategoryFilterDTO vendorRequestCategoryFilterDTO) {

        log.info("findVendorRequestCategory {}", vendorRequestCategoryFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorRequestCategoryFilterDTO);

        return ResponseVO.success(vendorRequestCategoryService.queryVendorRequestCategory(vendorRequestCategoryFilterDTO));
    }



}

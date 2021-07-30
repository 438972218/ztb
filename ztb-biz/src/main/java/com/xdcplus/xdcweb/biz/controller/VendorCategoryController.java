package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCategoryVO;
import com.xdcplus.xdcweb.biz.service.VendorCategoryService;
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
 * 供应商品类(VendorCategory)表服务控制层
 *
 * @author makejava
 * @since 2021-07-15 10:01:11
 */
@Api(tags = "供应商品类(VendorCategory)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorCategory")
public class VendorCategoryController extends AbstractController {

    @Autowired
    private final VendorCategoryService vendorCategoryService;

    @ApiOperation("新增供应商品类")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorCategory(@RequestBody @Valid VendorCategoryDTO vendorCategoryDTO) {

        log.info("saveVendorCategory {}", vendorCategoryDTO.toString());

        vendorCategoryDTO.setCreatedUser(getAccount());
        vendorCategoryService.saveVendorCategory(vendorCategoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商品类")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorCategory(@RequestBody @Valid VendorCategoryDTO vendorCategoryDTO) {

        log.info("updateVendorCategory {}", vendorCategoryDTO.toString());

        vendorCategoryDTO.setUpdatedUser(getAccount());
        vendorCategoryService.updateVendorCategory(vendorCategoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商品类")
    @DeleteMapping(value = "/{vendorCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorCategoryId", dataType = "Long", value = "供应商品类ID", required = true),
    })
    public ResponseVO deleteVendorCategoryLogical(@PathVariable("vendorCategoryId")
                                                  @NotNull(message = "供应商品类ID不能为空") Long vendorCategoryId) {

        log.info("deleteVendorCategoryLogical {}", vendorCategoryId);

        vendorCategoryService.deleteVendorCategoryLogical(vendorCategoryId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商品类")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorCategoryVO>> findVendorCategory(VendorCategoryFilterDTO vendorCategoryFilterDTO) {

        log.info("findVendorCategory {}", vendorCategoryFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorCategoryFilterDTO);

        return ResponseVO.success(vendorCategoryService.queryVendorCategory(vendorCategoryFilterDTO));
    }


}

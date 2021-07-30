package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorVO;
import com.xdcplus.xdcweb.biz.service.VendorService;
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
 * 供应商(Vendor)表服务控制层
 *
 * @author makejava
 * @since 2021-07-12 20:52:48
 */
@Api(tags = "供应商(Vendor)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendor")
public class VendorController extends AbstractController {

    @Autowired
    private final VendorService vendorService;

    @ApiOperation("新增供应商")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendor(@RequestBody @Valid VendorDTO vendorDTO) {

        log.info("saveVendor {}", vendorDTO.toString());

        vendorDTO.setCreatedUser(getAccount());
        vendorService.saveVendor(vendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendor(@RequestBody @Valid VendorDTO vendorDTO) {

        log.info("updateVendor {}", vendorDTO.toString());

        vendorDTO.setUpdatedUser(getAccount());
        vendorService.updateVendor(vendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商")
    @DeleteMapping(value = "/{vendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorId", dataType = "Long", value = "供应商ID", required = true),
    })
    public ResponseVO deleteVendorLogical(@PathVariable("vendorId")
                                          @NotNull(message = "供应商ID不能为空") Long vendorId) {

        log.info("deleteVendorLogical {}", vendorId);

        vendorService.deleteVendorLogical(vendorId);

        return ResponseVO.success();
    }

    @ApiOperation("show供应商")
    @GetMapping(value = "/show/{vendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorId", dataType = "Long", value = "供应商", required = true),
    })

    public ResponseVO<VendorVO> showVendorById(@PathVariable("vendorId")
                                                             @NotNull(message = "供应商ID不能为空") Long vendorId) {
        log.info("showVendorById {}", vendorId);
        return ResponseVO.success(vendorService.showVendorById(vendorId));
    }

    @ApiOperation("修改供应商WithDetail")
    @PutMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQuestionWithDetail(@RequestBody @Valid VendorDTO vendorDTO) {

        log.info("updateVendorWithDetail {}", vendorDTO.toString());

        vendorDTO.setUpdatedUser(getAccount());

        vendorService.updateVendorWithDetail(vendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorVO>> findVendor(VendorFilterDTO vendorFilterDTO) {

        log.info("findVendor {}", vendorFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorFilterDTO);

        return ResponseVO.success(vendorService.queryVendor(vendorFilterDTO));
    }


}

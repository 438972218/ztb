package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserVendorVO;
import com.xdcplus.xdcweb.biz.service.UserVendorService;
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
 * 用户供应商中间表(UserVendor)表服务控制层
 *
 * @author makejava
 * @since 2021-07-20 11:06:59
 */
@Api(tags = "用户供应商中间表(UserVendor)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("userVendor")
public class UserVendorController extends AbstractController {

    @Autowired
    private final UserVendorService userVendorService;

    @ApiOperation("新增用户供应商中间表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveUserVendor(@RequestBody @Valid UserVendorDTO userVendorDTO) {

        log.info("saveUserVendor {}", userVendorDTO.toString());

        userVendorDTO.setCreatedUser(getAccount());
        userVendorService.saveUserVendor(userVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改用户供应商中间表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateUserVendor(@RequestBody @Valid UserVendorDTO userVendorDTO) {

        log.info("updateUserVendor {}", userVendorDTO.toString());

        userVendorDTO.setUpdatedUser(getAccount());
        userVendorService.updateUserVendor(userVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除用户供应商中间表")
    @DeleteMapping(value = "/{userVendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userVendorId", dataType = "Long", value = "用户供应商中间表ID", required = true),
    })
    public ResponseVO deleteUserVendorLogical(@PathVariable("userVendorId")
                                              @NotNull(message = "用户供应商中间表ID不能为空") Long userVendorId) {

        log.info("deleteUserVendorLogical {}", userVendorId);

        userVendorService.deleteUserVendorLogical(userVendorId);

        return ResponseVO.success();
    }

    @ApiOperation("查询用户供应商中间表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<UserVendorVO>> findUserVendor(UserVendorFilterDTO userVendorFilterDTO) {

        log.info("findUserVendor {}", userVendorFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(userVendorFilterDTO);

        return ResponseVO.success(userVendorService.queryUserVendor(userVendorFilterDTO));
    }


}

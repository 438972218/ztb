package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserPurchaseOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserPurchaseOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserPurchaseOrzVO;
import com.xdcplus.xdcweb.biz.service.UserPurchaseOrzService;
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
 * 用户采购组织中间表(UserPurchaseOrz)表服务控制层
 *
 * @author makejava
 * @since 2021-07-20 11:06:56
 */
@Api(tags = "用户采购组织中间表(UserPurchaseOrz)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("userPurchaseOrz")
public class UserPurchaseOrzController extends AbstractController {

    @Autowired
    private final UserPurchaseOrzService userPurchaseOrzService;

    @ApiOperation("新增用户采购组织中间表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveUserPurchaseOrz(@RequestBody @Valid UserPurchaseOrzDTO userPurchaseOrzDTO) {

        log.info("saveUserPurchaseOrz {}", userPurchaseOrzDTO.toString());

        userPurchaseOrzDTO.setCreatedUser(getAccount());
        userPurchaseOrzService.saveUserPurchaseOrz(userPurchaseOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改用户采购组织中间表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateUserPurchaseOrz(@RequestBody @Valid UserPurchaseOrzDTO userPurchaseOrzDTO) {

        log.info("updateUserPurchaseOrz {}", userPurchaseOrzDTO.toString());

        userPurchaseOrzDTO.setUpdatedUser(getAccount());
        userPurchaseOrzService.updateUserPurchaseOrz(userPurchaseOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除用户采购组织中间表")
    @DeleteMapping(value = "/{userPurchaseOrzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userPurchaseOrzId", dataType = "Long", value = "用户采购组织中间表ID", required = true),
    })
    public ResponseVO deleteUserPurchaseOrzLogical(@PathVariable("userPurchaseOrzId")
                                                   @NotNull(message = "用户采购组织中间表ID不能为空") Long userPurchaseOrzId) {

        log.info("deleteUserPurchaseOrzLogical {}", userPurchaseOrzId);

        userPurchaseOrzService.deleteUserPurchaseOrzLogical(userPurchaseOrzId);

        return ResponseVO.success();
    }

    @ApiOperation("查询用户采购组织中间表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<UserPurchaseOrzVO>> findUserPurchaseOrz(UserPurchaseOrzFilterDTO userPurchaseOrzFilterDTO) {

        log.info("findUserPurchaseOrz {}", userPurchaseOrzFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(userPurchaseOrzFilterDTO);

        return ResponseVO.success(userPurchaseOrzService.queryUserPurchaseOrz(userPurchaseOrzFilterDTO));
    }


}

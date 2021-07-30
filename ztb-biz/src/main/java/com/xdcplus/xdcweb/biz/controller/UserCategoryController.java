package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCategoryVO;
import com.xdcplus.xdcweb.biz.service.UserCategoryService;
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
 * 用户品类中间表(UserCategory)表服务控制层
 *
 * @author makejava
 * @since 2021-07-20 11:06:54
 */
@Api(tags = "用户品类中间表(UserCategory)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("userCategory")
public class UserCategoryController extends AbstractController {

    @Autowired
    private final UserCategoryService userCategoryService;

    @ApiOperation("新增用户品类中间表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveUserCategory(@RequestBody @Valid UserCategoryDTO userCategoryDTO) {

        log.info("saveUserCategory {}", userCategoryDTO.toString());

        userCategoryDTO.setCreatedUser(getAccount());
        userCategoryService.saveUserCategory(userCategoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改用户品类中间表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateUserCategory(@RequestBody @Valid UserCategoryDTO userCategoryDTO) {

        log.info("updateUserCategory {}", userCategoryDTO.toString());

        userCategoryDTO.setUpdatedUser(getAccount());
        userCategoryService.updateUserCategory(userCategoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除用户品类中间表")
    @DeleteMapping(value = "/{userCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userCategoryId", dataType = "Long", value = "用户品类中间表ID", required = true),
    })
    public ResponseVO deleteUserCategoryLogical(@PathVariable("userCategoryId")
                                                @NotNull(message = "用户品类中间表ID不能为空") Long userCategoryId) {

        log.info("deleteUserCategoryLogical {}", userCategoryId);

        userCategoryService.deleteUserCategoryLogical(userCategoryId);

        return ResponseVO.success();
    }

    @ApiOperation("查询用户品类中间表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<UserCategoryVO>> findUserCategory(UserCategoryFilterDTO userCategoryFilterDTO) {

        log.info("findUserCategory {}", userCategoryFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(userCategoryFilterDTO);

        return ResponseVO.success(userCategoryService.queryUserCategory(userCategoryFilterDTO));
    }


}

package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCompanyDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCompanyFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCompanyVO;
import com.xdcplus.xdcweb.biz.service.UserCompanyService;
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
 * 用户公司中间表(UserCompany)表服务控制层
 *
 * @author makejava
 * @since 2021-07-22 15:38:41
 */
@Api(tags = "用户公司中间表(UserCompany)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("userCompany")
public class UserCompanyController extends AbstractController {

    @Autowired
    private final UserCompanyService userCompanyService;

    @ApiOperation("新增用户公司中间表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveUserCompany(@RequestBody UserCompanyDTO userCompanyDTO) {

        log.info("saveUserCompany {}", userCompanyDTO.toString());

        userCompanyDTO.setCreatedUser(getAccount());
        userCompanyService.saveUserCompany(userCompanyDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改用户公司中间表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateUserCompany(@RequestBody UserCompanyDTO userCompanyDTO) {

        log.info("updateUserCompany {}", userCompanyDTO.toString());

        userCompanyDTO.setUpdatedUser(getAccount());
        userCompanyService.updateUserCompany(userCompanyDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除用户公司中间表")
    @DeleteMapping(value = "/{userCompanyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userCompanyId", dataType = "Long", value = "用户公司中间表ID", required = true),
    })
    public ResponseVO deleteUserCompanyLogical(@PathVariable("userCompanyId")
                                               @NotNull(message = "用户公司中间表ID不能为空") Long userCompanyId) {

        log.info("deleteUserCompanyLogical {}", userCompanyId);

        userCompanyService.deleteUserCompanyLogical(userCompanyId);

        return ResponseVO.success();
    }

    @ApiOperation("查询用户公司中间表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<UserCompanyVO>> findUserCompany(UserCompanyFilterDTO userCompanyFilterDTO) {

        log.info("findUserCompany {}", userCompanyFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(userCompanyFilterDTO);

        return ResponseVO.success(userCompanyService.queryUserCompany(userCompanyFilterDTO));
    }


}

package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionBankDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionBankFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionBankVO;
import com.xdcplus.xdcweb.biz.service.VendorQuestionBankService;
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
import java.util.List;
import java.util.Map;


/**
 * 模板题库表(VendorQuestionBank)表服务控制层
 *
 * @author makejava
 * @since 2021-07-15 10:53:31
 */
@Api(tags = "模板题库表(VendorQuestionBank)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorQuestionBank")
public class VendorQuestionBankController extends AbstractController {

    @Autowired
    private final VendorQuestionBankService vendorQuestionBankService;

    @ApiOperation("新增模板题库表")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorQuestionBank(@RequestBody @Valid VendorQuestionBankDTO vendorQuestionBankDTO) {

        log.info("saveVendorQuestionBank {}", vendorQuestionBankDTO.toString());

        vendorQuestionBankDTO.setCreatedUser(getAccount());
        vendorQuestionBankService.saveVendorQuestionBank(vendorQuestionBankDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改模板题库表")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorQuestionBank(@RequestBody @Valid VendorQuestionBankDTO vendorQuestionBankDTO) {

        log.info("updateVendorQuestionBank {}", vendorQuestionBankDTO.toString());

        vendorQuestionBankDTO.setUpdatedUser(getAccount());
        vendorQuestionBankService.updateVendorQuestionBank(vendorQuestionBankDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除模板题库表")
    @DeleteMapping(value = "/{vendorQuestionBankId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorQuestionBankId", dataType = "Long", value = "模板题库表ID", required = true),
    })
    public ResponseVO deleteVendorQuestionBankLogical(@PathVariable("vendorQuestionBankId")
                                                      @NotNull(message = "模板题库表ID不能为空") Long vendorQuestionBankId) {

        log.info("deleteVendorQuestionBankLogical {}", vendorQuestionBankId);

        vendorQuestionBankService.deleteVendorQuestionBankLogical(vendorQuestionBankId);

        return ResponseVO.success();
    }

    @ApiOperation("查询模板题库表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorQuestionBankVO>> findVendorQuestionBank(VendorQuestionBankFilterDTO vendorQuestionBankFilterDTO) {

        log.info("findVendorQuestionBank {}", vendorQuestionBankFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQuestionBankFilterDTO);

        return ResponseVO.success(vendorQuestionBankService.queryVendorQuestionBank(vendorQuestionBankFilterDTO));
    }

    @ApiOperation("根据模板查询模板题库")
    @GetMapping(value = "/template", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Map<String, List<VendorQuestionBankVO>>> queryVendorQuestionBankVOByTemplate(VendorQuestionBankFilterDTO vendorQuestionBankFilterDTO) {

        log.info("queryVendorQuestionBankVOByTemplate {}", vendorQuestionBankFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorQuestionBankFilterDTO);

        return ResponseVO.success(vendorQuestionBankService.queryVendorQuestionBankVOByTemplate(vendorQuestionBankFilterDTO));
    }


}

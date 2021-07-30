package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryMaterialVO;
import com.xdcplus.xdcweb.biz.service.InquiryMaterialService;
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
 * 询价物品(InquiryMaterial)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:27:42
 */
@Api(tags = "询价物品(InquiryMaterial)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("inquiryMaterial")
public class InquiryMaterialController extends AbstractController {

    @Autowired
    private InquiryMaterialService inquiryMaterialService;

    @ApiOperation("新增询价物品")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquiryMaterial(@RequestBody InquiryMaterialDTO inquiryMaterialDTO) {

        log.info("saveInquiryMaterial {}", inquiryMaterialDTO.toString());

        inquiryMaterialDTO.setCreatedUser(getAccount());
        inquiryMaterialService.saveInquiryMaterial(inquiryMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改询价物品")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInquiryMaterial(@RequestBody InquiryMaterialDTO inquiryMaterialDTO) {

        log.info("updateInquiryMaterial {}", inquiryMaterialDTO.toString());

        inquiryMaterialDTO.setUpdatedUser(getAccount());
        inquiryMaterialService.updateInquiryMaterial(inquiryMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除询价物品")
    @DeleteMapping(value = "/{inquiryMaterialId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inquiryMaterialId", dataType = "Long", value = "询价物品ID", required = true),
    })
    public ResponseVO deleteInquiryMaterialLogical(@PathVariable("inquiryMaterialId")
                                                   @NotNull(message = "询价物品ID不能为空") Long inquiryMaterialId) {

        log.info("deleteInquiryMaterialLogical {}", inquiryMaterialId);

        inquiryMaterialService.deleteInquiryMaterialLogical(inquiryMaterialId);

        return ResponseVO.success();
    }

    @ApiOperation("查询询价物品")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquiryMaterialVO>> findInquiryMaterial(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {

        log.info("findInquiryMaterial {}", inquiryMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryMaterialFilterDTO);

        return ResponseVO.success(inquiryMaterialService.queryInquiryMaterial(inquiryMaterialFilterDTO));
    }

    @ApiOperation("查询询价物品(还比价)")
    @GetMapping(value = "/withDicker", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryInquiryMaterialsWithDicker(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {

        log.info("queryInquiryMaterialsWithDicker {}", inquiryMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryMaterialFilterDTO);

        return ResponseVO.success(inquiryMaterialService.queryInquiryMaterialsWithDicker(inquiryMaterialFilterDTO));
    }

    @ApiOperation("查询询价物品(核价)")
    @GetMapping(value = "/withPricing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryInquiryMaterialsWithPricing(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {

        log.info("queryInquiryMaterialsWithPricing {}", inquiryMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryMaterialFilterDTO);

        return ResponseVO.success(inquiryMaterialService.queryInquiryMaterialsWithPricing(inquiryMaterialFilterDTO));
    }

    @ApiOperation("查询询价物品(供应商)")
    @GetMapping(value = "/withVendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryInquiryMaterialsWithVendor(InquiryMaterialFilterDTO inquiryMaterialFilterDTO) {

        log.info("queryInquiryMaterialsWithVendor {}", inquiryMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryMaterialFilterDTO);

        return ResponseVO.success(inquiryMaterialService.queryInquiryMaterialsWithVendor(inquiryMaterialFilterDTO));
    }




}

package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;
import com.xdcplus.xdcweb.biz.service.BidMaterialService;
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
 * 招标物品(BidMaterial)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:28
 */
@Api(tags = "招标物品(BidMaterial)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidMaterial")
public class BidMaterialController extends AbstractController {

    @Autowired
    private BidMaterialService bidMaterialService;

    @ApiOperation("新增招标物品")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidMaterial(@RequestBody BidMaterialDTO bidMaterialDTO) {

        log.info("saveBidMaterial {}", bidMaterialDTO.toString());

        bidMaterialDTO.setCreatedUser(getAccount());
        bidMaterialService.saveBidMaterial(bidMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标物品")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidMaterial(@RequestBody BidMaterialDTO bidMaterialDTO) {

        log.info("updateBidMaterial {}", bidMaterialDTO.toString());

        bidMaterialDTO.setUpdatedUser(getAccount());
        bidMaterialService.updateBidMaterial(bidMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标物品")
    @DeleteMapping(value = "/{bidMaterialId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidMaterialId", dataType = "Long", value = "招标物品ID", required = true),
    })
    public ResponseVO deleteBidMaterialLogical(@PathVariable("bidMaterialId")
                                               @NotNull(message = "招标物品ID不能为空") Long bidMaterialId) {

        log.info("deleteBidMaterialLogical {}", bidMaterialId);

        bidMaterialService.deleteBidMaterialLogical(bidMaterialId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标物品")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidMaterialVO>> findBidMaterial(BidMaterialFilterDTO bidMaterialFilterDTO) {

        log.info("findBidMaterial {}", bidMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidMaterialFilterDTO);

        return ResponseVO.success(bidMaterialService.queryBidMaterial(bidMaterialFilterDTO));
    }

    @ApiOperation("查询招标物品(评标与定标)")
    @GetMapping(value = "/withPricing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryBidMaterialsWithPricing(BidMaterialFilterDTO bidMaterialFilterDTO) {

        log.info("queryBidMaterialsWithPricing {}", bidMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidMaterialFilterDTO);

        return ResponseVO.success(bidMaterialService.queryBidMaterialsWithPricing(bidMaterialFilterDTO));
    }

    @ApiOperation("查询招标物品(供应商)")
    @GetMapping(value = "/withVendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryBidMaterialsWithVendor(BidMaterialFilterDTO bidMaterialFilterDTO) {

        log.info("queryBidMaterialsWithVendor {}", bidMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidMaterialFilterDTO);

        return ResponseVO.success(bidMaterialService.queryBidMaterialsWithVendor(bidMaterialFilterDTO));
    }


}

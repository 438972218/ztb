package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorPreTrialItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorPreTrialItemVO;
import com.xdcplus.xdcweb.biz.service.BidVendorPreTrialItemService;
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
 * 招标投标方预审项(BidVendorPreTrialItem)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:36
 */
@Api(tags = "招标投标方预审项(BidVendorPreTrialItem)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidVendorPreTrialItem")
public class BidVendorPreTrialItemController extends AbstractController {

    @Autowired
    private BidVendorPreTrialItemService bidVendorPreTrialItemService;

    @ApiOperation("新增招标投标方预审项")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendorPreTrialItem(@RequestBody BidVendorPreTrialItemDTO bidVendorPreTrialItemDTO) {

        log.info("saveBidVendorPreTrialItem {}", bidVendorPreTrialItemDTO.toString());

        bidVendorPreTrialItemDTO.setCreatedUser(getAccount());
        bidVendorPreTrialItemService.saveBidVendorPreTrialItem(bidVendorPreTrialItemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标投标方预审项")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorPreTrialItem(@RequestBody BidVendorPreTrialItemDTO bidVendorPreTrialItemDTO) {

        log.info("updateBidVendorPreTrialItem {}", bidVendorPreTrialItemDTO.toString());

        bidVendorPreTrialItemDTO.setUpdatedUser(getAccount());
        bidVendorPreTrialItemService.updateBidVendorPreTrialItem(bidVendorPreTrialItemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标投标方预审项")
    @DeleteMapping(value = "/{bidVendorPreTrialItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidVendorPreTrialItemId", dataType = "Long", value = "招标投标方预审项ID", required = true),
    })
    public ResponseVO deleteBidVendorPreTrialItemLogical(@PathVariable("bidVendorPreTrialItemId")
                                                         @NotNull(message = "招标投标方预审项ID不能为空") Long bidVendorPreTrialItemId) {

        log.info("deleteBidVendorPreTrialItemLogical {}", bidVendorPreTrialItemId);

        bidVendorPreTrialItemService.deleteBidVendorPreTrialItemLogical(bidVendorPreTrialItemId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标投标方预审项")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidVendorPreTrialItemVO>> findBidVendorPreTrialItem(BidVendorPreTrialItemFilterDTO bidVendorPreTrialItemFilterDTO) {

        log.info("findBidVendorPreTrialItem {}", bidVendorPreTrialItemFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorPreTrialItemFilterDTO);

        return ResponseVO.success(bidVendorPreTrialItemService.queryBidVendorPreTrialItem(bidVendorPreTrialItemFilterDTO));
    }


}

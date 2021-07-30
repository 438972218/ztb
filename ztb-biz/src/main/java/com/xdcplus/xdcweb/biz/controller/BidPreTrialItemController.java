package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidPreTrialItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidPreTrialItemVO;
import com.xdcplus.xdcweb.biz.service.BidPreTrialItemService;
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
import java.util.List;


/**
 * 预审项(BidPreTrialItem)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:30
 */
@Api(tags = "预审项(BidPreTrialItem)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidPreTrialItem")
public class BidPreTrialItemController extends AbstractController {

    @Autowired
    private BidPreTrialItemService bidPreTrialItemService;

    @ApiOperation("新增预审项")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidPreTrialItem(@RequestBody BidPreTrialItemDTO bidPreTrialItemDTO) {

        log.info("saveBidPreTrialItem {}", bidPreTrialItemDTO.toString());

        bidPreTrialItemDTO.setCreatedUser(getAccount());
        bidPreTrialItemService.saveBidPreTrialItem(bidPreTrialItemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改预审项")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidPreTrialItem(@RequestBody BidPreTrialItemDTO bidPreTrialItemDTO) {

        log.info("updateBidPreTrialItem {}", bidPreTrialItemDTO.toString());

        bidPreTrialItemDTO.setUpdatedUser(getAccount());
        bidPreTrialItemService.updateBidPreTrialItem(bidPreTrialItemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除预审项")
    @DeleteMapping(value = "/{bidPreTrialItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidPreTrialItemId", dataType = "Long", value = "预审项ID", required = true),
    })
    public ResponseVO deleteBidPreTrialItemLogical(@PathVariable("bidPreTrialItemId")
                                                   @NotNull(message = "预审项ID不能为空") Long bidPreTrialItemId) {

        log.info("deleteBidPreTrialItemLogical {}", bidPreTrialItemId);

        bidPreTrialItemService.deleteBidPreTrialItemLogical(bidPreTrialItemId);

        return ResponseVO.success();
    }

    @ApiOperation("查询预审项")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidPreTrialItemVO>> findBidPreTrialItem(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO) {

        log.info("findBidPreTrialItem {}", bidPreTrialItemFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidPreTrialItemFilterDTO);

        return ResponseVO.success(bidPreTrialItemService.queryBidPreTrialItem(bidPreTrialItemFilterDTO));
    }

    @ApiOperation("查询预审项(供应商)")
    @GetMapping(value = "/withVendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<BidPreTrialItemVO>> findBidPreTrialItemByVendor(BidPreTrialItemFilterDTO bidPreTrialItemFilterDTO) {

        log.info("findBidPreTrialItemByVendor {}", bidPreTrialItemFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidPreTrialItemFilterDTO);

        return ResponseVO.success(bidPreTrialItemService.queryBidPreTrialItemsByVendor(bidPreTrialItemFilterDTO));
    }


}

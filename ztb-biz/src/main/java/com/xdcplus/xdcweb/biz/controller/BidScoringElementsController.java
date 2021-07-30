package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidScoringElementsDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidScoringElementsFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidScoringElementsVO;
import com.xdcplus.xdcweb.biz.service.BidScoringElementsService;
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
 * 评分要素(BidScoringElements)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:31
 */
@Api(tags = "评分要素(BidScoringElements)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidScoringElements")
public class BidScoringElementsController extends AbstractController {

    @Autowired
    private BidScoringElementsService bidScoringElementsService;

    @ApiOperation("新增评分要素")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidScoringElements(@RequestBody BidScoringElementsDTO bidScoringElementsDTO) {

        log.info("saveBidScoringElements {}", bidScoringElementsDTO.toString());

        bidScoringElementsDTO.setCreatedUser(getAccount());
        bidScoringElementsService.saveBidScoringElements(bidScoringElementsDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改评分要素")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidScoringElements(@RequestBody BidScoringElementsDTO bidScoringElementsDTO) {

        log.info("updateBidScoringElements {}", bidScoringElementsDTO.toString());

        bidScoringElementsDTO.setUpdatedUser(getAccount());
        bidScoringElementsService.updateBidScoringElements(bidScoringElementsDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除评分要素")
    @DeleteMapping(value = "/{bidScoringElementsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidScoringElementsId", dataType = "Long", value = "评分要素ID", required = true),
    })
    public ResponseVO deleteBidScoringElementsLogical(@PathVariable("bidScoringElementsId")
                                                      @NotNull(message = "评分要素ID不能为空") Long bidScoringElementsId) {

        log.info("deleteBidScoringElementsLogical {}", bidScoringElementsId);

        bidScoringElementsService.deleteBidScoringElementsLogical(bidScoringElementsId);

        return ResponseVO.success();
    }

    @ApiOperation("查询评分要素")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidScoringElementsVO>> findBidScoringElements(BidScoringElementsFilterDTO bidScoringElementsFilterDTO) {

        log.info("findBidScoringElements {}", bidScoringElementsFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidScoringElementsFilterDTO);

        return ResponseVO.success(bidScoringElementsService.queryBidScoringElements(bidScoringElementsFilterDTO));
    }


}

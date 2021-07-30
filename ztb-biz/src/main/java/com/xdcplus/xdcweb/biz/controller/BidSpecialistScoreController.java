package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistScoreVO;
import com.xdcplus.xdcweb.biz.service.BidSpecialistScoreService;
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
 * 招标专家评分(BidSpecialistScore)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:33
 */
@Api(tags = "招标专家评分(BidSpecialistScore)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidSpecialistScore")
public class BidSpecialistScoreController extends AbstractController {

    @Autowired
    private BidSpecialistScoreService bidSpecialistScoreService;

    @ApiOperation("新增招标专家评分")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidSpecialistScore(@RequestBody BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        log.info("saveBidSpecialistScore {}", bidSpecialistScoreDTO.toString());

        bidSpecialistScoreDTO.setCreatedUser(getAccount());
        bidSpecialistScoreService.saveBidSpecialistScore(bidSpecialistScoreDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标专家评分")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidSpecialistScore(@RequestBody BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        log.info("updateBidSpecialistScore {}", bidSpecialistScoreDTO.toString());

        bidSpecialistScoreDTO.setUpdatedUser(getAccount());
        bidSpecialistScoreService.updateBidSpecialistScore(bidSpecialistScoreDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标专家评分")
    @DeleteMapping(value = "/{bidSpecialistScoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidSpecialistScoreId", dataType = "Long", value = "招标专家评分ID", required = true),
    })
    public ResponseVO deleteBidSpecialistScoreLogical(@PathVariable("bidSpecialistScoreId")
                                                      @NotNull(message = "招标专家评分ID不能为空") Long bidSpecialistScoreId) {

        log.info("deleteBidSpecialistScoreLogical {}", bidSpecialistScoreId);

        bidSpecialistScoreService.deleteBidSpecialistScoreLogical(bidSpecialistScoreId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标专家评分")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSpecialistScoreVO>> findBidSpecialistScore(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {

        log.info("findBidSpecialistScore {}", bidSpecialistScoreFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSpecialistScoreFilterDTO);

        return ResponseVO.success(bidSpecialistScoreService.queryBidSpecialistScore(bidSpecialistScoreFilterDTO));
    }

    @ApiOperation("查询招标专家评分(专家)")
    @GetMapping(value = "/withSpecialist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSpecialistScoreVO>> findBidSpecialistScoreWithSpecialist(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {

        log.info("findBidSpecialistScoreWithSpecialist {}", bidSpecialistScoreFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSpecialistScoreFilterDTO);

        return ResponseVO.success(bidSpecialistScoreService.queryBidSpecialistScoreWithSpecialist(bidSpecialistScoreFilterDTO));
    }



    @ApiOperation("批量修改招标专家评分")
    @PutMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchUpdateBidSpecialistScore(@RequestBody List<BidSpecialistScoreDTO> bidSpecialistScoreDTOS) {

        log.info("batchUpdateBidSpecialistScore {}", bidSpecialistScoreDTOS.toString());
        for (BidSpecialistScoreDTO bidSpecialistScoreDTO : bidSpecialistScoreDTOS) {
            bidSpecialistScoreDTO.setUpdatedUser(getAccount());
            bidSpecialistScoreService.updateBidSpecialistScore(bidSpecialistScoreDTO);
        }

        return ResponseVO.success();
    }

}

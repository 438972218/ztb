package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.xdcweb.biz.service.BidSpecialistService;
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
 * 专家(BidSpecialist)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:31
 */
@Api(tags = "专家(BidSpecialist)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidSpecialist")
public class BidSpecialistController extends AbstractController {

    @Autowired
    private BidSpecialistService bidSpecialistService;

    @ApiOperation("新增专家")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidSpecialist(@RequestBody BidSpecialistDTO bidSpecialistDTO) {

        log.info("saveBidSpecialist {}", bidSpecialistDTO.toString());

        bidSpecialistDTO.setCreatedUser(getAccount());
        bidSpecialistService.saveBidSpecialist(bidSpecialistDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改专家")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidSpecialist(@RequestBody BidSpecialistDTO bidSpecialistDTO) {

        log.info("updateBidSpecialist {}", bidSpecialistDTO.toString());

        bidSpecialistDTO.setUpdatedUser(getAccount());
        bidSpecialistService.updateBidSpecialist(bidSpecialistDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除专家")
    @DeleteMapping(value = "/{bidSpecialistId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidSpecialistId", dataType = "Long", value = "专家ID", required = true),
    })
    public ResponseVO deleteBidSpecialistLogical(@PathVariable("bidSpecialistId")
                                                 @NotNull(message = "专家ID不能为空") Long bidSpecialistId) {

        log.info("deleteBidSpecialistLogical {}", bidSpecialistId);

        bidSpecialistService.deleteBidSpecialistLogical(bidSpecialistId);

        return ResponseVO.success();
    }

    @ApiOperation("查询专家")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSpecialistVO>> findBidSpecialist(BidSpecialistFilterDTO bidSpecialistFilterDTO) {

        log.info("findBidSpecialist {}", bidSpecialistFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSpecialistFilterDTO);

        return ResponseVO.success(bidSpecialistService.queryBidSpecialist(bidSpecialistFilterDTO));
    }


}

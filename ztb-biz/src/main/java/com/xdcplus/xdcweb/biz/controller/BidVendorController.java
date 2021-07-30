package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.xdcweb.biz.service.BidVendorService;
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
 * 招标投标方(BidVendor)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:34
 */
@Api(tags = "招标投标方(BidVendor)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidVendor")
public class BidVendorController extends AbstractController {

    @Autowired
    private BidVendorService bidVendorService;

    @ApiOperation("新增招标投标方")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendor(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("saveBidVendor {}", bidVendorDTO.toString());

        bidVendorDTO.setCreatedUser(getAccount());
        bidVendorService.saveBidVendor(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标投标方")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendor(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("updateBidVendor {}", bidVendorDTO.toString());

        bidVendorDTO.setUpdatedUser(getAccount());
        bidVendorService.updateBidVendor(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标投标方")
    @DeleteMapping(value = "/{bidVendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidVendorId", dataType = "Long", value = "招标投标方ID", required = true),
    })
    public ResponseVO deleteBidVendorLogical(@PathVariable("bidVendorId")
                                             @NotNull(message = "招标投标方ID不能为空") Long bidVendorId) {

        log.info("deleteBidVendorLogical {}", bidVendorId);

        bidVendorService.deleteBidVendorLogical(bidVendorId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标投标方")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidVendorVO>> findBidVendor(BidVendorFilterDTO bidVendorFilterDTO) {

        log.info("findBidVendor {}", bidVendorFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorFilterDTO);

        return ResponseVO.success(bidVendorService.queryBidVendor(bidVendorFilterDTO));
    }

    @ApiOperation("(用户)批量修改招标投标方")
    @PutMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchUpdateBidVendorByUser(@RequestBody List<BidVendorDTO> bidVendorDTOS) {

        log.info("batchUpdateBidVendorByUser {}", bidVendorDTOS.toString());

        for (BidVendorDTO bidVendorDTO : bidVendorDTOS) {
            bidVendorDTO.setUpdatedUser(getAccount());
            if (bidVendorDTO.getInformationPass() == null) {
                bidVendorDTO.setVendorStatus("未通过");
            } else {
                bidVendorDTO.setVendorStatus("待投标");
            }
            bidVendorService.updateBidVendor(bidVendorDTO);

        }

        return ResponseVO.success();
    }

    @ApiOperation("查询招标投标方(专家组长)")
    @GetMapping(value = "/withScore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<BidVendorVO>> findBidVendorWithTotalPrice(BidVendorFilterDTO bidVendorFilterDTO) {

        log.info("findBidVendor {}", bidVendorFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorFilterDTO);

        return ResponseVO.success(bidVendorService.queryBidVendorWithTotalScore(bidVendorFilterDTO));
    }

    @ApiOperation("供应商修改招标供应商（状态）")
    @PutMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorStatus(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("updateBidVendorStatus {}", bidVendorDTO.toString());

        bidVendorDTO.setUpdatedUser(getAccount());
        bidVendorService.updateBidVendorStatus(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("更新招标投标方总价")
    @PutMapping(value = "/totalPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorTotalPrice(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("updateBidVendorTotalPrice {}", bidVendorDTO.toString());

        bidVendorDTO.setUpdatedUser(getAccount());
        bidVendorService.updateBidVendorTotalPrice(bidVendorDTO);

        return ResponseVO.success();
    }


}

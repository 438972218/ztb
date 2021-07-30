package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.bean.BeanUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.BidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.BidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.service.BidMaterialService;
import com.xdcplus.xdcweb.biz.service.BidVendorMaterialService;
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
 * 招标投标方物品(BidVendorMaterial)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:19:36
 */
@Api(tags = "招标投标方物品(BidVendorMaterial)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidVendorMaterial")
public class BidVendorMaterialController extends AbstractController {

    @Autowired
    private BidVendorMaterialService bidVendorMaterialService;

    @Autowired
    private BidMaterialService bidMaterialService;

    @Autowired
    private BidVendorService bidVendorService;

    @ApiOperation("新增招标投标方物品")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendorMaterial(@RequestBody BidVendorMaterialDTO bidVendorMaterialDTO) {

        log.info("saveBidVendorMaterial {}", bidVendorMaterialDTO.toString());

        bidVendorMaterialDTO.setCreatedUser(getAccount());
        bidVendorMaterialService.saveBidVendorMaterial(bidVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标投标方物品")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorMaterial(@RequestBody BidVendorMaterialDTO bidVendorMaterialDTO) {

        log.info("updateBidVendorMaterial {}", bidVendorMaterialDTO.toString());

        bidVendorMaterialDTO.setUpdatedUser(getAccount());
        bidVendorMaterialService.updateBidVendorMaterial(bidVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标投标方物品")
    @DeleteMapping(value = "/{bidVendorMaterialId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidVendorMaterialId", dataType = "Long", value = "招标投标方物品ID", required = true),
    })
    public ResponseVO deleteBidVendorMaterialLogical(@PathVariable("bidVendorMaterialId")
                                                     @NotNull(message = "招标投标方物品ID不能为空") Long bidVendorMaterialId) {

        log.info("deleteBidVendorMaterialLogical {}", bidVendorMaterialId);

        bidVendorMaterialService.deleteBidVendorMaterialLogical(bidVendorMaterialId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标投标方物品")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidVendorMaterialVO>> findBidVendorMaterial(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {

        log.info("findBidVendorMaterial {}", bidVendorMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorMaterialFilterDTO);

        return ResponseVO.success(bidVendorMaterialService.queryBidVendorMaterial(bidVendorMaterialFilterDTO));
    }

    @ApiOperation("批量修改招标投标方报价")
    @PutMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchUpdateBidVendorMaterial(@RequestBody List<BidVendorMaterialDTO> bidVendorMaterialDTOS) {

        log.info("batchUpdateBidVendorMaterial {}", bidVendorMaterialDTOS.toString());
        for (BidVendorMaterialDTO bidVendorMaterialDTO : bidVendorMaterialDTOS) {
            bidVendorMaterialDTO.setUpdatedUser(getAccount());
            bidVendorMaterialService.updateBidVendorMaterial(bidVendorMaterialDTO);
        }

        return ResponseVO.success();
    }

    @ApiOperation("查询询价供应商报价(报价次数)")
    @GetMapping(value = "/offeringNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidVendorMaterialVO>> findBidVendorMaterialWithOfferingNumber(BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO) {

        log.info("findBidVendorMaterialWithOfferingNumber {}", bidVendorMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorMaterialFilterDTO);

        return ResponseVO.success(bidVendorMaterialService.queryPageVODesc(bidVendorMaterialFilterDTO));
    }

    @ApiOperation("新增询价供应商保存报价(报价次数)")
    @PostMapping(value = "/offeringNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendorMaterialWithOfferingNumber(@RequestBody BidVendorMaterialDTO bidVendorMaterialDTO) {

        log.info("saveBidVendorMaterialWithOfferingNumber {}", bidVendorMaterialDTO.toString());

        bidVendorMaterialDTO.setCreatedUser(getAccount());
        BidVendorMaterialFilterDTO bidVendorMaterialFilterDTO = new BidVendorMaterialFilterDTO();
        BeanUtil.copyProperties(bidVendorMaterialDTO, bidVendorMaterialFilterDTO);
        Integer offeringNumber = bidVendorMaterialService.getOfferingNumber(bidVendorMaterialFilterDTO);
        bidVendorMaterialDTO.setOfferingNumber(offeringNumber);
        BidMaterialVO bidMaterialVO = bidMaterialService.queryBidMaterialById(bidVendorMaterialDTO.getMaterialId());
        bidVendorMaterialDTO.setUnit(bidMaterialVO.getUnit());

        bidVendorMaterialService.saveBidVendorMaterial(bidVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("招标供应商提交报价")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO submitOffer(@RequestBody BidVendorMaterialDTO bidVendorMaterialDTO) {

        log.info("submitOffer {}", bidVendorMaterialDTO.toString());

        bidVendorMaterialDTO.setCreatedUser(getAccount());
        bidVendorMaterialService.submitOffer(bidVendorMaterialDTO);
        BidVendorDTO bidVendorDTO =new BidVendorDTO();
        bidVendorDTO.setId(bidVendorMaterialDTO.getBidVendorId());
        bidVendorService.updateBidVendorTotalPrice(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("根据物品id/招标供应商id查询供应商最新报价")
    @PostMapping(value = "/newPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryNewBidVendorMaterialByMaterialId(@RequestBody BidVendorMaterialDTO bidVendorMaterialDTO) {

        log.info("queryNewBidVendorMaterialByMaterialId {}", bidVendorMaterialDTO.toString());

        bidVendorMaterialDTO.setCreatedUser(getAccount());
        BidVendorMaterialQuery bidVendorMaterialQuery = new BidVendorMaterialQuery();
        BeanUtil.copyProperties(bidVendorMaterialDTO, bidVendorMaterialQuery);

        List<BidVendorMaterialVO> bidVendorMaterialVOS = bidVendorMaterialService.queryNewBidVendorMaterialByMaterialId(bidVendorMaterialQuery);

        return ResponseVO.success(bidVendorMaterialVOS);
    }

    @ApiOperation("查询已分配的报价")
    @PostMapping(value = "/allocated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryAllocatedRanking(@RequestBody BidVendorMaterialDTO bidVendorMaterialDTO) {

        log.info("queryAllocatedRanking {}", bidVendorMaterialDTO.toString());

        return ResponseVO.success(bidVendorMaterialService.queryAllocatedRanking(bidVendorMaterialDTO));
    }


}

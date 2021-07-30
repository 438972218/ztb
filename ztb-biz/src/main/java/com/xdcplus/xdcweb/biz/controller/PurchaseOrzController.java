package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzVO;
import com.xdcplus.xdcweb.biz.service.PurchaseOrzService;
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


/**
 * 采购组织(PurchaseOrz)表服务控制层
 *
 * @author makejava
 * @since 2021-07-08 11:35:44
 */
@Api(tags = "采购组织(PurchaseOrz)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("purchaseOrz")
public class PurchaseOrzController extends AbstractController {

    @Autowired
    private final PurchaseOrzService purchaseOrzService;

    @ApiOperation("新增采购组织")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePurchaseOrz(@RequestBody @Valid PurchaseOrzDTO purchaseOrzDTO) {

        log.info("savePurchaseOrz {}", purchaseOrzDTO.toString());

        purchaseOrzDTO.setCreatedUser(getAccount());
        purchaseOrzService.savePurchaseOrz(purchaseOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改采购组织")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePurchaseOrz(@RequestBody @Valid PurchaseOrzDTO purchaseOrzDTO) {

        log.info("updatePurchaseOrz {}", purchaseOrzDTO.toString());

        purchaseOrzDTO.setUpdatedUser(getAccount());
        purchaseOrzService.updatePurchaseOrz(purchaseOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除采购组织")
    @DeleteMapping(value = "/{purchaseOrzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "purchaseOrzId", dataType = "Long", value = "采购组织ID", required = true),
    })
    public ResponseVO deletePurchaseOrz(@PathVariable("purchaseOrzId")
                                        @NotNull(message = "采购组织ID不能为空") Long purchaseOrzId) {

        log.info("deletePurchaseOrz {}", purchaseOrzId);

        purchaseOrzService.deletePurchaseOrzLogical(purchaseOrzId);

        return ResponseVO.success();
    }

    @ApiOperation("查询采购组织")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PurchaseOrzVO>> findPurchaseOrz(PurchaseOrzFilterDTO purchaseOrzFilterDTO) {

        log.info("findPurchaseOrz {}", purchaseOrzFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(purchaseOrzFilterDTO);

        return ResponseVO.success(purchaseOrzService.queryPurchaseOrz(purchaseOrzFilterDTO));
    }

    @ApiOperation("查询采购组织树状")
    @GetMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<CompanyVO>> findPurchaseOrz() {
        return ResponseVO.success(purchaseOrzService.queryPurchaseOrzsTree());
    }

}

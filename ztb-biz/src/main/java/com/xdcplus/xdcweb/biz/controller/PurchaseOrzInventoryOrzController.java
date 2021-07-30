package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzInventoryOrzVO;
import com.xdcplus.xdcweb.biz.service.PurchaseOrzInventoryOrzService;
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
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表服务控制层
 *
 * @author makejava
 * @since 2021-07-14 10:43:28
 */
@Api(tags = "采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("purchaseOrzInventoryOrz")
public class PurchaseOrzInventoryOrzController extends AbstractController {

    @Autowired
    private final PurchaseOrzInventoryOrzService purchaseOrzInventoryOrzService;

    @ApiOperation("新增采购组织 工厂(库存组织)对应关系")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePurchaseOrzInventoryOrz(@RequestBody PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO) {

        log.info("savePurchaseOrzInventoryOrz {}", purchaseOrzInventoryOrzDTO.toString());

        purchaseOrzInventoryOrzDTO.setCreatedUser(getAccount());
        purchaseOrzInventoryOrzService.savePurchaseOrzInventoryOrz(purchaseOrzInventoryOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改采购组织 工厂(库存组织)对应关系")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePurchaseOrzInventoryOrz(@RequestBody PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO) {

        log.info("updatePurchaseOrzInventoryOrz {}", purchaseOrzInventoryOrzDTO.toString());

        purchaseOrzInventoryOrzDTO.setUpdatedUser(getAccount());
        purchaseOrzInventoryOrzService.updatePurchaseOrzInventoryOrz(purchaseOrzInventoryOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除采购组织 工厂(库存组织)对应关系")
    @DeleteMapping(value = "/{purchaseOrzInventoryOrzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "purchaseOrzInventoryOrzId", dataType = "Long", value = "采购组织 工厂(库存组织)对应关系ID", required = true),
    })
    public ResponseVO deletePurchaseOrzInventoryOrzLogical(@PathVariable("purchaseOrzInventoryOrzId")
                                                           @NotNull(message = "采购组织 工厂(库存组织)对应关系ID不能为空") Long purchaseOrzInventoryOrzId) {

        log.info("deletePurchaseOrzInventoryOrzLogical {}", purchaseOrzInventoryOrzId);

        purchaseOrzInventoryOrzService.deletePurchaseOrzInventoryOrzLogical(purchaseOrzInventoryOrzId);

        return ResponseVO.success();
    }

    @ApiOperation("查询采购组织 工厂(库存组织)对应关系")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PurchaseOrzInventoryOrzVO>> findPurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzFilterDTO purchaseOrzInventoryOrzFilterDTO) {

        log.info("findPurchaseOrzInventoryOrz {}", purchaseOrzInventoryOrzFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(purchaseOrzInventoryOrzFilterDTO);

        return ResponseVO.success(purchaseOrzInventoryOrzService.queryPurchaseOrzInventoryOrz(purchaseOrzInventoryOrzFilterDTO));
    }

    @ApiOperation("维护采购组织 工厂(库存组织)对应关系")
    @PostMapping(value = "/maintenance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO maintenance(@RequestBody PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO) {
        log.info("findPurchaseOrzInventoryOrz {}", purchaseOrzInventoryOrzDTO);
        return ResponseVO.success(purchaseOrzInventoryOrzService.maintenance(purchaseOrzInventoryOrzDTO));
    }

    @ApiOperation("查询多个库存组织")
    @GetMapping(value = "/getMaintenance/{purchaseOrzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "purchaseOrzId", dataType = "Long", value = "采购组织 工厂对应关系表", required = true),
    })
    public ResponseVO<PurchaseOrzInventoryOrzVO> getMaintenance(@PathVariable("purchaseOrzId")
                                         @NotNull(message = "采购组织ID不能为空") Long purchaseOrzId) {
        log.info("findPurchaseOrzInventoryOrz {}", purchaseOrzId);
        return ResponseVO.success(purchaseOrzInventoryOrzService.getMaintenance(purchaseOrzId));
    }

}

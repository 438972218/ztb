package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.permission.PceCompanyResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InventoryOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InventoryOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CompanyVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InventoryOrzVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.SysRoleVO;
import com.xdcplus.xdcweb.biz.service.InventoryOrzService;
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
import java.util.Map;


/**
 * 库存组织(InventoryOrz)表服务控制层
 *
 * @author makejava
 * @since 2021-07-08 11:35:42
 */
@Api(tags = "库存组织(InventoryOrz)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("inventoryOrz")
public class InventoryOrzController extends AbstractController {

    @Autowired
    private final InventoryOrzService inventoryOrzService;

    @ApiOperation("新增库存组织")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInventoryOrz(@RequestBody @Valid InventoryOrzDTO inventoryOrzDTO) {

        log.info("saveInventoryOrz {}", inventoryOrzDTO.toString());

        inventoryOrzDTO.setCreatedUser(getAccount());
        inventoryOrzService.saveInventoryOrz(inventoryOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改库存组织")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInventoryOrz(@RequestBody @Valid InventoryOrzDTO inventoryOrzDTO) {

        log.info("updateInventoryOrz {}", inventoryOrzDTO.toString());

        inventoryOrzDTO.setUpdatedUser(getAccount());
        inventoryOrzService.updateInventoryOrz(inventoryOrzDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除库存组织")
    @DeleteMapping(value = "/{inventoryOrzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inventoryOrzId", dataType = "Long", value = "库存组织ID", required = true),
    })
    public ResponseVO deleteInventoryOrz(@PathVariable("inventoryOrzId")
                                         @NotNull(message = "库存组织ID不能为空") Long inventoryOrzId) {

        log.info("deleteInventoryOrz {}", inventoryOrzId);

        inventoryOrzService.deleteInventoryOrzLogical(inventoryOrzId);

        return ResponseVO.success();
    }

    @ApiOperation("查询库存组织")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InventoryOrzVO>> findInventoryOrz(InventoryOrzFilterDTO inventoryOrzFilterDTO) {

        log.info("findInventoryOrz {}", inventoryOrzFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inventoryOrzFilterDTO);

        return ResponseVO.success(inventoryOrzService.queryInventoryOrz(inventoryOrzFilterDTO));
    }

    @ApiOperation("查询库存组织树状")
    @GetMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<CompanyVO>> findInventoryOrzTree() {

        return ResponseVO.success(inventoryOrzService.queryInventoryOrzTree());
    }

    @ApiOperation("查询工厂树状")
    @GetMapping(value = "/factoryTree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<CompanyVO>> findFactoryTree() {
        return ResponseVO.success(inventoryOrzService.findFactoryTree());
    }

    @ApiOperation("查询工厂树状(筛选)")
    @GetMapping(value = "/factoryTreeBySelect/{purchaseOrzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "purchaseOrzId", dataType = "Long", value = "采购组织ID", required = true),
    })
    public ResponseVO<List<CompanyVO>> findFactoryTreeBySelect(@PathVariable("purchaseOrzId")  Long purchaseOrzId) {
        return ResponseVO.success(inventoryOrzService.findFactoryTreeBySelect(purchaseOrzId));
    }

    @ApiOperation("查询是否是集团公司")
    @GetMapping(value = "/judgeGroupCompany/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SysRoleVO judgeGroupCompany(@PathVariable("id") @NotNull(message = "ID不能为空") Long id) {
        return PceCompanyResponseUtils.judgeGroupCompany(id);
    }

}

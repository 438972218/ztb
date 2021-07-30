package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.ItemDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.ItemFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ItemVO;
import com.xdcplus.xdcweb.biz.service.ItemService;
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


import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;


/**
 * 物料管理(Item)表服务控制层
 *
 * @author makejava
 * @since 2021-07-02 10:28:14
 */
@Api(tags = "物料管理(Item)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("item")
public class ItemController extends AbstractController {

    @Autowired
    private final ItemService itemService;

    @ApiOperation("新增物料管理")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveItem(@RequestBody @Valid ItemDTO itemDTO) {

        log.info("saveItem {}", itemDTO.toString());

        itemDTO.setCreatedUser(getAccount());
        itemService.saveItem(itemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改物料管理")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateItem(@RequestBody @Valid ItemDTO itemDTO) {

        log.info("updateItem {}", itemDTO.toString());

        itemDTO.setUpdatedUser(getAccount());
        itemService.updateItem(itemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除物料管理")
    @DeleteMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "itemId", dataType = "Long", value = "物料管理ID", required = true),
    })
    public ResponseVO deleteItem(@PathVariable("itemId")
                                 @NotNull(message = "物料管理ID不能为空") Long itemId) {

        log.info("deleteItem {}", itemId);

        itemService.deleteItemLogical(itemId);

        return ResponseVO.success();
    }

    @ApiOperation("查询物料管理")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ItemVO>> findItem(ItemFilterDTO itemFilterDTO) {

        log.info("findItem {}", itemFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(itemFilterDTO);

        return ResponseVO.success(itemService.queryItem(itemFilterDTO));
    }

}

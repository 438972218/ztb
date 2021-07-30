package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.xdcweb.biz.service.PaidMaterialService;
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
 * 竞价物品(PaidMaterial)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:35:13
 */
@Api(tags = "竞价物品(PaidMaterial)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("paidMaterial")
public class PaidMaterialController extends AbstractController {

    @Autowired
    private PaidMaterialService paidMaterialService;

    @ApiOperation("新增竞价物品")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidMaterial(@RequestBody PaidMaterialDTO paidMaterialDTO) {

        log.info("savePaidMaterial {}", paidMaterialDTO.toString());

        paidMaterialDTO.setCreatedUser(getAccount());
        paidMaterialService.savePaidMaterial(paidMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改竞价物品")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePaidMaterial(@RequestBody PaidMaterialDTO paidMaterialDTO) {

        log.info("updatePaidMaterial {}", paidMaterialDTO.toString());

        paidMaterialDTO.setUpdatedUser(getAccount());
        paidMaterialService.updatePaidMaterial(paidMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除竞价物品")
    @DeleteMapping(value = "/{paidMaterialId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidMaterialId", dataType = "Long", value = "竞价物品ID", required = true),
    })
    public ResponseVO deletePaidMaterialLogical(@PathVariable("paidMaterialId")
                                                @NotNull(message = "竞价物品ID不能为空") Long paidMaterialId) {

        log.info("deletePaidMaterialLogical {}", paidMaterialId);

        paidMaterialService.deletePaidMaterialLogical(paidMaterialId);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价物品")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidMaterialVO>> findPaidMaterial(PaidMaterialFilterDTO paidMaterialFilterDTO) {

        log.info("findPaidMaterial {}", paidMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidMaterialFilterDTO);

        return ResponseVO.success(paidMaterialService.queryPaidMaterial(paidMaterialFilterDTO));
    }

    @ApiOperation("查询询价物品(核价)")
    @GetMapping(value = "/withPricing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryPaidMaterialsWithPricing(PaidMaterialFilterDTO paidMaterialFilterDTO) {

        log.info("queryPaidMaterialsWithPricing {}", paidMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidMaterialFilterDTO);

        return ResponseVO.success(paidMaterialService.queryPaidMaterialsWithPricing(paidMaterialFilterDTO));
    }


}

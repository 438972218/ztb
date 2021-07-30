package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.service.PaidVendorMaterialService;
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
 * 竞价供应商物品(PaidVendorMaterial)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:35:16
 */
@Api(tags = "竞价供应商物品(PaidVendorMaterial)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("paidVendorMaterial")
public class PaidVendorMaterialController extends AbstractController {

    @Autowired
    private PaidVendorMaterialService paidVendorMaterialService;

    @ApiOperation("新增竞价供应商物品")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidVendorMaterial(@RequestBody PaidVendorMaterialDTO paidVendorMaterialDTO) {

        log.info("savePaidVendorMaterial {}", paidVendorMaterialDTO.toString());

        paidVendorMaterialDTO.setCreatedUser(getAccount());
        paidVendorMaterialService.savePaidVendorMaterial(paidVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改竞价供应商物品")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePaidVendorMaterial(@RequestBody PaidVendorMaterialDTO paidVendorMaterialDTO) {

        log.info("updatePaidVendorMaterial {}", paidVendorMaterialDTO.toString());

        paidVendorMaterialDTO.setUpdatedUser(getAccount());
        paidVendorMaterialService.updatePaidVendorMaterial(paidVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除竞价供应商物品")
    @DeleteMapping(value = "/{paidVendorMaterialId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidVendorMaterialId", dataType = "Long", value = "竞价供应商物品ID", required = true),
    })
    public ResponseVO deletePaidVendorMaterialLogical(@PathVariable("paidVendorMaterialId")
                                                      @NotNull(message = "竞价供应商物品ID不能为空") Long paidVendorMaterialId) {

        log.info("deletePaidVendorMaterialLogical {}", paidVendorMaterialId);

        paidVendorMaterialService.deletePaidVendorMaterialLogical(paidVendorMaterialId);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价供应商物品")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidVendorMaterialVO>> findPaidVendorMaterial(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {

        log.info("findPaidVendorMaterial {}", paidVendorMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidVendorMaterialFilterDTO);

        return ResponseVO.success(paidVendorMaterialService.queryPaidVendorMaterial(paidVendorMaterialFilterDTO));
    }

    @ApiOperation("批量修改竞价供应商报价")
    @PutMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchUpdatePaidVendorMaterial(@RequestBody List<PaidVendorMaterialDTO> paidVendorMaterialDTOS) {

        log.info("batchUpdatePaidVendorMaterial {}", paidVendorMaterialDTOS.toString());

        for (PaidVendorMaterialDTO paidVendorMaterialDTO : paidVendorMaterialDTOS) {
            paidVendorMaterialDTO.setUpdatedUser(getAccount());
            paidVendorMaterialService.updatePaidVendorMaterial(paidVendorMaterialDTO);
        }

        return ResponseVO.success();
    }

    @ApiOperation("竞价")
    @PostMapping(value = "/offering", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO offeringPaidVendorMaterial(@RequestBody PaidVendorMaterialDTO paidVendorMaterialDTO) {

        log.info("offeringPaidVendorMaterial {}", paidVendorMaterialDTO.toString());

        paidVendorMaterialDTO.setCreatedUser(getAccount());
        paidVendorMaterialService.offeringPaidVendorMaterial(paidVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价排名")
    @GetMapping(value = "/queryRanking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryRanking(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {

        log.info("queryRanking {}", paidVendorMaterialFilterDTO.toString());

        paidVendorMaterialFilterDTO.setCreatedUser(getAccount());
        PaidMaterialVO paidMaterialVO = paidVendorMaterialService.queryRanking(paidVendorMaterialFilterDTO);

        return ResponseVO.success(paidMaterialVO);
    }

    @ApiOperation("查询物品竞价排名明细")
    @PostMapping(value = "/rankingDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryRankingDetail(@RequestBody PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {

        log.info("queryRankingDetail {}", paidVendorMaterialFilterDTO.toString());

        return ResponseVO.success(paidVendorMaterialService.queryPaidVendorMaterialsDesc(paidVendorMaterialFilterDTO));
    }

    @ApiOperation("查询已分配的报价(审批)")
    @PostMapping(value = "/allocated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryAllocatedRanking(@RequestBody PaidVendorMaterialDTO paidVendorMaterialDTO) {

        log.info("queryAllocatedRanking {}", paidVendorMaterialDTO.toString());

        return ResponseVO.success(paidVendorMaterialService.queryAllocatedRanking(paidVendorMaterialDTO));
    }


}

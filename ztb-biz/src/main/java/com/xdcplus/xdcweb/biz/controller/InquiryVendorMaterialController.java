package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.bean.BeanUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InquiryVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InquiryVendorMaterialVO;
import com.xdcplus.xdcweb.biz.service.InquiryVendorMaterialService;
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
 * 询价供应商物品(InquiryVendorMaterial)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-26 15:27:51
 */
@Api(tags = "询价供应商物品(InquiryVendorMaterial)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("inquiryVendorMaterial")
public class InquiryVendorMaterialController extends AbstractController {

    @Autowired
    private InquiryVendorMaterialService inquiryVendorMaterialService;

    @ApiOperation("新增询价供应商物品")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquiryVendorMaterial(@RequestBody InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("saveInquiryVendorMaterial {}", inquiryVendorMaterialDTO.toString());

        inquiryVendorMaterialDTO.setCreatedUser(getAccount());
        inquiryVendorMaterialService.saveInquiryVendorMaterial(inquiryVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改询价供应商物品")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateInquiryVendorMaterial(@RequestBody InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("updateInquiryVendorMaterial {}", inquiryVendorMaterialDTO.toString());

        inquiryVendorMaterialDTO.setUpdatedUser(getAccount());
        inquiryVendorMaterialService.updateInquiryVendorMaterial(inquiryVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除询价供应商物品")
    @DeleteMapping(value = "/{inquiryVendorMaterialId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "inquiryVendorMaterialId", dataType = "Long", value = "询价供应商物品ID", required = true),
    })
    public ResponseVO deleteInquiryVendorMaterialLogical(@PathVariable("inquiryVendorMaterialId")
                                                         @NotNull(message = "询价供应商物品ID不能为空") Long inquiryVendorMaterialId) {

        log.info("deleteInquiryVendorMaterialLogical {}", inquiryVendorMaterialId);

        inquiryVendorMaterialService.deleteInquiryVendorMaterialLogical(inquiryVendorMaterialId);

        return ResponseVO.success();
    }

    @ApiOperation("查询询价供应商物品")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquiryVendorMaterialVO>> findInquiryVendorMaterial(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {

        log.info("findInquiryVendorMaterial {}", inquiryVendorMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryVendorMaterialFilterDTO);

        return ResponseVO.success(inquiryVendorMaterialService.queryInquiryVendorMaterial(inquiryVendorMaterialFilterDTO));
    }

    @ApiOperation("查询询价供应商报价(报价次数)")
    @GetMapping(value = "/offeringNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<InquiryVendorMaterialVO>> findInquiryVendorMaterialWithOfferingNumber(InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO) {

        log.info("findInquiryVendorMaterialWithOfferingNumber {}", inquiryVendorMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(inquiryVendorMaterialFilterDTO);

        return ResponseVO.success(inquiryVendorMaterialService.queryPageVODesc(inquiryVendorMaterialFilterDTO));
    }

    @ApiOperation("询价供应商保存报价(报价次数)")
    @PostMapping(value = "/offeringNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveInquiryVendorMaterialWithOfferingNumber(@RequestBody InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("saveInquiryVendorMaterialWithOfferingNumber {}", inquiryVendorMaterialDTO.toString());

        inquiryVendorMaterialDTO.setCreatedUser(getAccount());
        InquiryVendorMaterialFilterDTO inquiryVendorMaterialFilterDTO = new InquiryVendorMaterialFilterDTO();
        BeanUtil.copyProperties(inquiryVendorMaterialDTO, inquiryVendorMaterialFilterDTO);
        Integer offeringNumber = inquiryVendorMaterialService.getOfferingNumber(inquiryVendorMaterialFilterDTO);
        inquiryVendorMaterialDTO.setOfferingNumber(offeringNumber);

        inquiryVendorMaterialService.saveInquiryVendorMaterial(inquiryVendorMaterialDTO);

//        InquiryVendor inquiryVendor = new InquiryVendor();
//        inquiryVendor.setId(inquiryVendorMaterialDTO.getInquiryVendorId());
//        inquiryVendor.setVendorStatus("已报价");
//        inquiryVendorService.updateById(inquiryVendor);

        return ResponseVO.success();
    }

    @ApiOperation("询价单用户提交还价")
    @PutMapping(value = "/dicker", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO dicker(@RequestBody InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("dicker {}", inquiryVendorMaterialDTO.toString());

        inquiryVendorMaterialDTO.setUpdatedUser(getAccount());
        inquiryVendorMaterialService.dicker(inquiryVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("询价供应商提交报价")
    @PostMapping(value = "submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO submitOffer(@RequestBody InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("submitOffer {}", inquiryVendorMaterialDTO.toString());

        inquiryVendorMaterialDTO.setCreatedUser(getAccount());
        inquiryVendorMaterialService.submitOffer(inquiryVendorMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("根据物品id/询价供应商id查询供应商最新报价")
    @PostMapping(value = "/newPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryNewInquiryVendorMaterialByMaterialId(InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("queryNewInquiryVendorMaterialByMaterialId {}", inquiryVendorMaterialDTO.toString());

        inquiryVendorMaterialDTO.setCreatedUser(getAccount());
        InquiryVendorMaterialQuery inquiryVendorMaterialQuery = new InquiryVendorMaterialQuery();
        BeanUtil.copyProperties(inquiryVendorMaterialDTO, inquiryVendorMaterialQuery);

//        List<String> statusList = new ArrayList<>();
//        statusList.add("提交");
//        statusList.add("还价保存");
//        inquiryVendorMaterialQuery.setStatusList(statusList);
        List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS = inquiryVendorMaterialService.queryNewInquiryVendorMaterialByMaterialId(inquiryVendorMaterialQuery);

        return ResponseVO.success(inquiryVendorMaterialVOS);
    }

    @ApiOperation("根据物品id/询价供应商id查询供应商最新报价(核价)")
    @PostMapping(value = "/newPriceWithPricing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryNewInquiryVendorMaterialByMaterialIdWithPricing(InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("queryNewInquiryVendorMaterialByMaterialIdWithPricing {}", inquiryVendorMaterialDTO.toString());

        inquiryVendorMaterialDTO.setCreatedUser(getAccount());
        InquiryVendorMaterialQuery inquiryVendorMaterialQuery = new InquiryVendorMaterialQuery();
        BeanUtil.copyProperties(inquiryVendorMaterialDTO, inquiryVendorMaterialQuery);

        List<InquiryVendorMaterialVO> inquiryVendorMaterialVOS = inquiryVendorMaterialService.queryNewInquiryVendorMaterialByMaterialIdWithPricing(inquiryVendorMaterialQuery);

        return ResponseVO.success(inquiryVendorMaterialVOS);
    }

    @ApiOperation("查询已分配的报价")
    @PostMapping(value = "/allocated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO queryAllocatedRanking(@RequestBody InquiryVendorMaterialDTO inquiryVendorMaterialDTO) {

        log.info("queryAllocatedRanking {}", inquiryVendorMaterialDTO.toString());

        return ResponseVO.success(inquiryVendorMaterialService.queryAllocatedRanking(inquiryVendorMaterialDTO));
    }


}

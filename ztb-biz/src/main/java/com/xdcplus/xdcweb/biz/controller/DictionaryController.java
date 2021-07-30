package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.DictionaryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.DictionaryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.DictionaryVO;
import com.xdcplus.xdcweb.biz.service.DictionaryService;
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
 * 字典管理(Dictionary)表服务控制层
 *
 * @author makejava
 * @since 2021-07-02 10:28:08
 */
@Api(tags = "字典管理(Dictionary)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("dictionary")
public class DictionaryController extends AbstractController {

    @Autowired
    private final DictionaryService dictionaryService;

    @ApiOperation("新增字典管理")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveDictionary(@RequestBody DictionaryDTO dictionaryDTO) {

        log.info("saveDictionary {}", dictionaryDTO.toString());

        dictionaryDTO.setCreatedUser(getAccount());
        dictionaryService.saveDictionary(dictionaryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改字典管理")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateDictionary(@RequestBody DictionaryDTO dictionaryDTO) {

        log.info("updateDictionary {}", dictionaryDTO.toString());

        dictionaryDTO.setUpdatedUser(getAccount());
        dictionaryService.updateDictionary(dictionaryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除字典管理")
    @DeleteMapping(value = "/{dictionaryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "dictionaryId", dataType = "Long", value = "字典管理ID", required = true),
    })
    public ResponseVO deleteDictionary(@PathVariable("dictionaryId")
                                       @NotNull(message = "字典管理ID不能为空") Long dictionaryId) {

        log.info("deleteDictionary {}", dictionaryId);

        dictionaryService.deleteDictionaryLogical(dictionaryId);

        return ResponseVO.success();
    }

    @ApiOperation("查询字典管理")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<DictionaryVO>> findDictionary(DictionaryFilterDTO dictionaryFilterDTO) {

        log.info("findDictionary {}", dictionaryFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(dictionaryFilterDTO);

        return ResponseVO.success(dictionaryService.queryDictionary(dictionaryFilterDTO));
    }

}

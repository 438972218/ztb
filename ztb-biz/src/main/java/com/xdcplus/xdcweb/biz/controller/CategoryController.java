package com.xdcplus.xdcweb.biz.controller;


import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.CategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.ResponseVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.CategoryVO;
import com.xdcplus.xdcweb.biz.service.CategoryService;
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
 * 品类管理(Category)表服务控制层
 *
 * @author makejava
 * @since 2021-07-02 10:28:12
 */
@Api(tags = "品类管理(Category)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("category")
public class CategoryController extends AbstractController {

    @Autowired
    private final CategoryService categoryService;

    @ApiOperation("新增品类管理")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveCategory(@RequestBody @Valid CategoryDTO categoryDTO) {

        log.info("saveCategory {}", categoryDTO.toString());

        categoryDTO.setCreatedUser(getAccount());
        categoryDTO.setId(null);
        categoryDTO.setDeleted(0);
        categoryService.saveCategory(categoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改品类管理")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateCategory(@RequestBody @Valid CategoryDTO categoryDTO) {

        log.info("updateCategory {}", categoryDTO.toString());

        categoryDTO.setUpdatedUser(getAccount());
        categoryService.updateCategory(categoryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除品类管理")
    @DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "categoryId", dataType = "Long", value = "品类管理ID", required = true),
    })
    public ResponseVO deleteCategory(@PathVariable("categoryId")
                                     @NotNull(message = "品类管理ID不能为空") Long categoryId) {

        log.info("deleteCategory {}", categoryId);

        categoryService.deleteCategoryLogical(categoryId);

        return ResponseVO.success();
    }

    @ApiOperation("查询品类管理")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<CategoryVO>> findCategory(CategoryFilterDTO categoryFilterDTO) {

        log.info("findCategory {}", categoryFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(categoryFilterDTO);

        return ResponseVO.success(categoryService.queryCategory(categoryFilterDTO));
    }

    @ApiOperation("查询品类管理树状结构")
    @GetMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<CategoryVO>> findTreeCategory(CategoryFilterDTO categoryFilterDTO) {

        log.info("findCategory {}", categoryFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(categoryFilterDTO);

        return ResponseVO.success(categoryService.queryTreeCategory(categoryFilterDTO));
    }

}

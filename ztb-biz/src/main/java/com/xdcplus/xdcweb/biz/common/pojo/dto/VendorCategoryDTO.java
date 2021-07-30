package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 供应商品类(VendorCategory)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-15 10:01:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorCategoryDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -59367705363638803L;

    @ApiModelProperty("信息主键")
    private Long id;

    @NotNull(message = "供应商ID不能为空")
    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @NotNull(message = "品类ID不能为空")
    @ApiModelProperty("品类ID")
    private Long categoryId;

    @ApiModelProperty("品类名称")
    private String categoryName;

    @ApiModelProperty("供应组织")
    private String
            supplyOrganization;

    @ApiModelProperty("有效状态")
    private String validStatus;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("修改人")
    private String updatedUser;

    @ApiModelProperty("修改时间")
    private Long updatedTime;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;

}

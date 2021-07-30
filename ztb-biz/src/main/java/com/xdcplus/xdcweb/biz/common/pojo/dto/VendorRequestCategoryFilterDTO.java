package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商注册单品类(VendorRequestCategory)表查询入参DTO类
 *
 * @author makejava
 * @since 2021-07-26 15:47:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorRequestCategoryFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -24590579815841438L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("供应商ID")
    private Long vendorRequestId;

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

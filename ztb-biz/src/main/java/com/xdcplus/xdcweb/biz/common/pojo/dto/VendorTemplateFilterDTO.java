package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 模板表(VendorTemplate)表查询入参DTO类
 *
 * @author makejava
 * @since 2021-07-15 10:54:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorTemplateFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 566657259169254035L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("模板类型")
    private String type;

    @ApiModelProperty("模板名称")
    private String name;

    @ApiModelProperty("模板描述")
    private String insDesc;

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

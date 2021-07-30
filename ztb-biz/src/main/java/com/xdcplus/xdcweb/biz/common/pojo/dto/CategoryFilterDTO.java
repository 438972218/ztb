package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 品类管理(Category)表查询入参DTO类
 *
 * @author makejava
 * @since 2021-07-02 10:28:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class CategoryFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -99817223245137766L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("父ID")
    private Long pId;

    @ApiModelProperty("品类代码")
    private String code;

    @ApiModelProperty("品类名称")
    private String name;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("备注")
    private String remark;

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

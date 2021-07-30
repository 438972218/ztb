package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 模板题库中间表(VendorTemplateQbank)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-15 10:16:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorTemplateQbankDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 705575431298514687L;

    @ApiModelProperty("信息主键")
    private Long id;

    @NotNull(message = "模板ID不能为空")
    @ApiModelProperty("模板ID")
    private Long templateId;

    @NotNull(message = "问题ID不能为空")
    @ApiModelProperty("问题ID")
    private Long questionBankId;

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

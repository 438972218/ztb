package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 采购组织(PurchaseOrz)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-08 11:35:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PurchaseOrzDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -74673862419636554L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("父级采购组织")
    private Long pId;

    @ApiModelProperty("公司ID")
    private Long companyId;

    @NotNull(message="采购组织代码不能为空")
    @ApiModelProperty("采购组织代码")
    private String code;

    @NotNull(message="采购组织名称不能为空")
    @ApiModelProperty("采购组织名称")
    private String name;

    @ApiModelProperty("采购组织描述")
    private String description;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("修改人")
    private String updatedUser;

    @ApiModelProperty("修改时间")
    private Long updatedTime;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;

}

package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 竞价物品(PaidMaterial)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:19:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidMaterialDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 628974777072312332L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("竞价单id")
    private Long paidSheetId;

    @NotNull(message = "库存组织不能为空")
    @ApiModelProperty("库存组织")
    private String inventoryOrganization;

    @NotNull(message = "物品编码不能为空")
    @ApiModelProperty("物品编码")
    private String materialCode;

    @NotNull(message = "物品描述不能为空")
    @ApiModelProperty("物品描述")
    private String materialDescription;

    @ApiModelProperty("参考品牌")
    private String referToBrand;

    @NotNull(message = "物品分类不能为空")
    @ApiModelProperty("物品分类")
    private String materialClassify;

    @ApiModelProperty("需求日期")
    private Long demandedDate;

    @NotNull(message = "需求数量不能为空")
    @ApiModelProperty("需求数量")
    private Integer demandedQuantity;

    @NotNull(message = "计量单位不能为空")
    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("含税")
    private Integer taxInclusive;

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

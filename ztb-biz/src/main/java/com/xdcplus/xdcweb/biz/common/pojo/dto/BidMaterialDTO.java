package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 招标物品(BidMaterial)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidMaterialDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 591232345659300926L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @NotNull(message = "库存组织不能为空")
    @ApiModelProperty("库存组织")
    private String inventoryOrganization;

    @NotNull(message = "物品编码不能为空")
    @ApiModelProperty("物品编码")
    private String materialCode;

    @ApiModelProperty("物品描述")
    private String materialDescription;

    @ApiModelProperty("参考品牌")
    private String referToBrand;

    @ApiModelProperty("物品分类")
    private String materialClassify;

    @NotNull(message = "需求日期不能为空")
    @ApiModelProperty("需求日期")
    private Long demandedDate;

    @NotNull(message = "需求数量不能为空")
    @ApiModelProperty("需求数量")
    private Integer demandedQuantity;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("含税")
    private String taxInclusive;

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

package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 招标物品(BidMaterial)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:35
 */
@Data
@SuppressWarnings("serial")
public class BidMaterialQuery implements Serializable {
    private static final long serialVersionUID = 767487937900595342L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("库存组织")
    private String inventoryOrganization;

    @ApiModelProperty("物品编码")
    private String materialCode;

    @ApiModelProperty("物品描述")
    private String materialDescription;

    @ApiModelProperty("参考品牌")
    private String referToBrand;

    @ApiModelProperty("物品分类")
    private String materialClassify;

    @ApiModelProperty("需求日期")
    private Long demandedDate;

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

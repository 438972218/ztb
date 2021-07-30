package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标物品(BidMaterial)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidMaterialVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 467365706537159519L;

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

    @ApiModelProperty("成本单价")
    private Double costPrice;

    @ApiModelProperty("成本合价")
    private Double costTotalPrice;

    @ApiModelProperty("最新报价")
    private Double newPrice;

    @ApiModelProperty("上一次报价")
    private Double lastPrice;

}

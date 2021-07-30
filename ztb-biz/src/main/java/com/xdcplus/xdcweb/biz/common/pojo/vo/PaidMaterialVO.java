package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 竞价物品(PaidMaterial)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:19:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidMaterialVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -40045814891100355L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("竞价单id")
    private Long paidSheetId;

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

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("含税")
    private Integer taxInclusive;

    @ApiModelProperty("竞价人数")
    private Integer paidNumber;

    @ApiModelProperty("当前排名")
    private Integer currentRanking;

    @ApiModelProperty("成本单价")
    private Double costPrice;

    @ApiModelProperty("成本合价")
    private Double costTotalPrice;

    private List<PaidVendorMaterialVO> paidVendorMaterialVOS;

}

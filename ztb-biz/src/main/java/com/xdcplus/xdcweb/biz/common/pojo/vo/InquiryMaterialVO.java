package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 询价物品(InquiryMaterial)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:17:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InquiryMaterialVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 956878407328373375L;

    @ApiModelProperty("询价单id")
    private Long inquirySheetId;

    @ApiModelProperty("库存组织")
    private String inventoryOrganization;

    @ApiModelProperty("物品编码")
    private String materialCode;

    @ApiModelProperty("物品描述")
    private String materialDescription;

    @ApiModelProperty("物品说明")
    private String materialExplain;

    @ApiModelProperty("报价明细")
    private String quoteDetails;

    @ApiModelProperty("数量")
    private Integer quantity;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("还价信息")
    private String offerInformation;

    @ApiModelProperty("成本单价")
    private Double costPrice;

    @ApiModelProperty("成本合价")
    private Double costTotalPrice;

    @ApiModelProperty("最新报价")
    private Double newPrice;

    @ApiModelProperty("最新还价")
    private Double newDickerPrice;

    @ApiModelProperty("最新还价理由")
    private String newDickerReason;

    @ApiModelProperty("上一次报价")
    private Double lastPrice;

}

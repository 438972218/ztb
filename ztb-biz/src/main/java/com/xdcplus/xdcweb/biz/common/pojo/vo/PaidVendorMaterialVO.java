package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价供应商物品(PaidVendorMaterial)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidVendorMaterialVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 916346095479150223L;

    @ApiModelProperty("物品id")
    private Long materialId;

    @ApiModelProperty("供应商id")
    private Long paidVendorId;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("出价次数")
    private Integer offeringNumber;

    @ApiModelProperty("出价类型")
    private String offeringType;

    @ApiModelProperty("放弃询价")
    private String abandonInquiry;

    @ApiModelProperty("再报价")
    private String repeatOffer;

    @ApiModelProperty("单价")
    private Double price;

    @ApiModelProperty("报价有效期从")
    private Long offeringPeriodFrom;

    @ApiModelProperty("报价有效期至")
    private Long offeringPeriodTo;

    @ApiModelProperty("承诺交付日期")
    private Long deliveryDate;

    @ApiModelProperty("供货周期")
    private String deliveryCycle;

    @ApiModelProperty("报价说明")
    private String offeringClarification;

    @ApiModelProperty("最小供货数量")
    private String minSupplyQuantity;

    @ApiModelProperty("还价-单价")
    private Double dickerPrice;

    @ApiModelProperty("还价理由")
    private String dickerReason;

    @ApiModelProperty("分配数量")
    private String allottedQuantity;

    @ApiModelProperty("分配比例")
    private String allottedProp;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("物品数量")
    private Integer materialQuantity;

    @ApiModelProperty("竞价供应商")
    private PaidVendorVO paidVendorVO;

}

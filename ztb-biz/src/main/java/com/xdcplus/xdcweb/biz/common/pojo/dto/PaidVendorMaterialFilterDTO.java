package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价供应商物品(PaidVendorMaterial)表查询入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidVendorMaterialFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 128256273897512439L;

    @ApiModelProperty("$column.comment")
    private Long id;

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

    @ApiModelProperty("sheetId")
    private Long sheetId;

}

package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 询价供应商物品(InquiryVendorMaterial)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:09:00
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_inquiry_vendor_material")
public class InquiryVendorMaterial implements Serializable {
    private static final long serialVersionUID = 455931638848756734L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("物品id")
    private Long materialId;

    @ApiModelProperty("供应商id")
    private Long inquiryVendorId;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("报价次数")
    private Integer offeringNumber;

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
}

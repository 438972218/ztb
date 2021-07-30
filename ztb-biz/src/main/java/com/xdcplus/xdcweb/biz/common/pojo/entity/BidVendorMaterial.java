package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标投标方物品(BidVendorMaterial)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:32
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_vendor_material")
public class BidVendorMaterial implements Serializable {
    private static final long serialVersionUID = -95356992872622342L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("物品id")
    private Long materialId;

    @ApiModelProperty("供应商id")
    private Long bidVendorId;

    @ApiModelProperty("报价次数")
    private Integer offeringNumber;

    @ApiModelProperty("投标人")
    private String bidder;

    @ApiModelProperty("投标数量")
    private Integer bidQuantity;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("单价")
    private Double price;

    @ApiModelProperty("总价")
    private Double totalPrice;

    @ApiModelProperty("承诺交付日期")
    private String deliveryDate;

    @ApiModelProperty("中标数量")
    private Integer allottedQuantity;

    @ApiModelProperty("中标比例")
    private String allottedProp;

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

    @ApiModelProperty("状态")
    private String status;
}

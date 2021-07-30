package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 招标投标方物品(BidVendorMaterial)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:06
 */
@Data
@SuppressWarnings("serial")
public class BidVendorMaterialQuery implements Serializable {
    private static final long serialVersionUID = -59242451054940293L;

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

    @ApiModelProperty("sheetId")
    private Long bidSheetId;

}

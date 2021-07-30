package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标投标方物品(BidVendorMaterial)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidVendorMaterialVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 360411814894382885L;

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

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("物品数量")
    private Integer materialQuantity;

    @ApiModelProperty("招标供应商")
    private BidVendorVO bidVendorVO;

}

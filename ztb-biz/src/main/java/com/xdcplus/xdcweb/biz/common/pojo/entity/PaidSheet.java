package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价单(PaidSheet)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:09:01
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_paid_sheet")
public class PaidSheet implements Serializable {
    private static final long serialVersionUID = -83937208576833934L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("竞价单号")
    private String paidNum;

    @ApiModelProperty("采购组织")
    private String purchasingOrganization;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("竞价单标题")
    private String title;

    @ApiModelProperty("竞价单类型")
    private String paidType;

    @ApiModelProperty("竞价方向")
    private String paidDirection;

    @ApiModelProperty("竞价方式")
    private String paidMode;

    @ApiModelProperty("付款方式")
    private String paymentMode;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("汇率")
    private String exchangeRate;

    @ApiModelProperty("报价开始时间")
    private Long offerStartTime;

    @ApiModelProperty("报价截止时间")
    private Long offerEndTime;

    @ApiModelProperty("标的类型")
    private String bidType;

    @ApiModelProperty("含税")
    private Integer offerTax;

    @ApiModelProperty("报价税码")
    private String offerTaxCode;

    @ApiModelProperty("税率")
    private String taxRate;

    @ApiModelProperty("密封报价")
    private Integer sealedBid;

    @ApiModelProperty("报价幅度")
    private String priceRange;

    @ApiModelProperty("幅度类型")
    private String rangeType;

    @ApiModelProperty("寻源类型")
    private String sourcingType;

    @ApiModelProperty("公开规则")
    private String publicRules;

    @ApiModelProperty("竞价规则")
    private String biddingRules;

    @ApiModelProperty("价格类型")
    private String priceType;

    @ApiModelProperty("审批流程")
    private String approvalProcess;

    @ApiModelProperty("交货地点")
    private String deliveryPlace;

    @ApiModelProperty("竞价次数")
    private Integer bidNumber;

    @ApiModelProperty("自动延期")
    private String autoExtension;

    @ApiModelProperty("竞价排名")
    private String bidRanking;

    @ApiModelProperty("说明")
    private String explaination;

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

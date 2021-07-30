package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 询价单(InquirySheet)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:59
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_inquiry_sheet")
public class InquirySheet implements Serializable {
    private static final long serialVersionUID = -38832848788455323L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("询价单号")
    private String inquirySheetNum;

    @ApiModelProperty("采购组织名称")
    private String purchasingOrganization;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("询价单标签")
    private String title;

    @ApiModelProperty("询价单类型")
    private String inquirySheetType;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("询价方式")
    private String inquiryMode;

    @ApiModelProperty("付款方式")
    private String paymentMode;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("汇率")
    private String exchangeRate;

    @ApiModelProperty("报价截止时间")
    private Long quotationDeadline;

    @ApiModelProperty("标的类型")
    private String signType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("含税")
    private Integer offerTax;

    @ApiModelProperty("报价税码")
    private String offerTaxCode;

    @ApiModelProperty("税率")
    private String taxRate;

    @ApiModelProperty("交货地点")
    private String deliveryPoints;

    @ApiModelProperty("密封报价")
    private Integer sealedBid;

    @ApiModelProperty("轮次")
    private String round;

    @ApiModelProperty("价格类型")
    private String priceType;

    @ApiModelProperty("审批流程")
    private String approvalProcess;

    @ApiModelProperty("含运费")
    private Integer icash;

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

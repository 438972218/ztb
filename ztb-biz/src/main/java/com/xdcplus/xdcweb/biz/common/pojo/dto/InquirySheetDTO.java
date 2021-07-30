package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 询价单(InquirySheet)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:07:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InquirySheetDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 546401653684637905L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("询价单号")
    private String inquirySheetNum;

    @NotNull(message = "采购组织名称不能为空")
    @ApiModelProperty("采购组织名称")
    private String purchasingOrganization;

    @NotNull(message = "公司代码不能为空")
    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("询价单标签")
    private String title;

    @NotNull(message = "询价单类型不能为空")
    @ApiModelProperty("询价单类型")
    private String inquirySheetType;

    @ApiModelProperty("状态")
    private String status;

    @NotNull(message = "询价方式不能为空")
    @ApiModelProperty("询价方式")
    private String inquiryMode;

    @NotNull(message = "付款方式不能为空")
    @ApiModelProperty("付款方式")
    private String paymentMode;

    @NotNull(message = "币种不能为空")
    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("汇率")
    private String exchangeRate;

    @NotNull(message = "报价截止时间不能为空")
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

    @NotNull(message = "交货地点不能为空")
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

    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

    @ApiModelProperty("userId")
    private Long userId;

}

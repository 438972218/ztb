package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 竞价单(PaidSheet)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-02 11:13:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidSheetDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -91167096272246573L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("竞价单号")
    private String paidNum;

    @NotNull(message = "采购组织不能为空")
    @ApiModelProperty("采购组织")
    private String purchasingOrganization;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @NotNull(message = "竞价单标题不能为空")
    @ApiModelProperty("竞价单标题")
    private String title;

    @NotNull(message = "竞价单类型不能为空")
    @ApiModelProperty("竞价单类型")
    private String paidType;

    @NotNull(message = "竞价方向不能为空")
    @ApiModelProperty("竞价方向")
    private String paidDirection;

    @NotNull(message = "竞价方式不能为空")
    @ApiModelProperty("竞价方式")
    private String paidMode;

    @NotNull(message = "付款方式不能为空")
    @ApiModelProperty("付款方式")
    private String paymentMode;

    @NotNull(message = "币种不能为空")
    @ApiModelProperty("币种")
    private String currency;

    @NotNull(message = "汇率不能为空")
    @ApiModelProperty("汇率")
    private String exchangeRate;

    @NotNull(message = "报价开始时间不能为空")
    @ApiModelProperty("报价开始时间")
    private Long offerStartTime;

    @NotNull(message = "报价截止时间不能为空")
    @ApiModelProperty("报价截止时间")
    private Long offerEndTime;

    @NotNull(message = "标的类型不能为空")
    @ApiModelProperty("标的类型")
    private String bidType;

    @ApiModelProperty("含税")
    private Integer offerTax;

    @NotNull(message = "报价税码不能为空")
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

    @NotNull(message = "寻源类型不能为空")
    @ApiModelProperty("寻源类型")
    private String sourcingType;

    @NotNull(message = "公开规则不能为空")
    @ApiModelProperty("公开规则")
    private String publicRules;

    @NotNull(message = "竞价规则不能为空")
    @ApiModelProperty("竞价规则")
    private String biddingRules;

    @NotNull(message = "价格类型不能为空")
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

    @NotNull(message = "竞价排名不能为空")
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

    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

    @ApiModelProperty("userId")
    private Long userId;

}

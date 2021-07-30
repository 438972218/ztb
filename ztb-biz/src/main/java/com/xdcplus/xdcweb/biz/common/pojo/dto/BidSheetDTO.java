package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * bid招标单(BidSheet)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSheetDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 520796219014857512L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("招标单号")
    private String bidSheetNum;

    @ApiModelProperty("标题")
    private String title;

    @NotNull(message = "采购组织不能为空")
    @ApiModelProperty("采购组织")
    private String purchasingOrganization;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @NotNull(message = "招标单类型不能为空")
    @ApiModelProperty("招标单类型")
    private String SheetType;

    @NotNull(message = "轮次不能为空")
    @ApiModelProperty("轮次")
    private String round;

    @NotNull(message = "版本不能为空")
    @ApiModelProperty("版本")
    private String versions;

    @NotNull(message = "投标类型不能为空")
    @ApiModelProperty("投标类型")
    private String tenderType;

    @NotNull(message = "招标方式不能为空")
    @ApiModelProperty("招标方式")
    private String tenderMode;

    @NotNull(message = "资格预审不能为空")
    @ApiModelProperty("资格预审")
    private Integer preQualification;

    @ApiModelProperty("申请截止时间")
    private Long applicationDeadline;

    @ApiModelProperty("资格预审截止时间")
    private Long prequalificationEndTime;

    @NotNull(message = "投标截止时间不能为空")
    @ApiModelProperty("投标截止时间")
    private Long bidEndTime;

    @NotNull(message = "开标时间不能为空")
    @ApiModelProperty("开标时间")
    private Long openBidTime;

    @NotNull(message = "开标地点不能为空")
    @ApiModelProperty("开标地点")
    private String openBidPlace;

    @NotNull(message = "标的类型不能为空")
    @ApiModelProperty("标的类型")
    private String signType;

    @NotNull(message = "项目地点不能为空")
    @ApiModelProperty("项目地点")
    private String projectLocation;

    @NotNull(message = "招标员不能为空")
    @ApiModelProperty("招标员")
    private String tenderUser;

    @NotNull(message = "报价含税不能为空")
    @ApiModelProperty("报价含税")
    private Integer offerTax;

    @NotNull(message = "币种不能为空")
    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("招标文件免费")
    private Integer tenderDocumentFree;

    @NotNull(message = "招标文件费不能为空")
    @ApiModelProperty("招标文件费")
    private Double tenderDocumentCost;

    @NotNull(message = "价格类型不能为空")
    @ApiModelProperty("价格类型")
    private String priceType;

    @ApiModelProperty("审批流程")
    private String approvalProcess;

    @ApiModelProperty("项目交付日期")
    private Long projectDeliveryDate;

    @ApiModelProperty("要求保证金")
    private Integer marginCall;

    @ApiModelProperty("保证金")
    private Double earnestMoney;

    @ApiModelProperty("本币金额")
    private Double locCurrencyAmount;

    @ApiModelProperty("原币金额")
    private Double oriCurrencyAmount;

    @NotNull(message = "招标事项不能为空")
    @ApiModelProperty("招标事项")
    private String tenderMatters;

    @ApiModelProperty("说明")
    private String explanation;

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

package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * bid招标单(BidSheet)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:28
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_sheet")
public class BidSheet implements Serializable {
    private static final long serialVersionUID = -89992084618067593L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("招标单号")
    private String bidSheetNum;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("采购组织")
    private String purchasingOrganization;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("招标单类型")
    private String sheetType;

    @ApiModelProperty("轮次")
    private String round;

    @ApiModelProperty("版本")
    private String versions;

    @ApiModelProperty("投标类型")
    private String tenderType;

    @ApiModelProperty("招标方式")
    private String tenderMode;

    @ApiModelProperty("资格预审")
    private Integer preQualification;

    @ApiModelProperty("申请截止时间")
    private Long applicationDeadline;

    @ApiModelProperty("资格预审截止时间")
    private Long prequalificationEndTime;

    @ApiModelProperty("投标截止时间")
    private Long bidEndTime;

    @ApiModelProperty("开标时间")
    private Long openBidTime;

    @ApiModelProperty("开标地点")
    private String openBidPlace;

    @ApiModelProperty("标的类型")
    private String signType;

    @ApiModelProperty("项目地点")
    private String projectLocation;

    @ApiModelProperty("招标员")
    private String tenderUser;

    @ApiModelProperty("报价含税")
    private Integer offerTax;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("招标文件免费")
    private Integer tenderDocumentFree;

    @ApiModelProperty("招标文件费")
    private Double tenderDocumentCost;

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
}

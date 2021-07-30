package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * bid招标单(BidSheet)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSheetVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 546617811595131104L;

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
    private String SheetType;

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

    @ApiModelProperty("预审项")
    private List<BidPreTrialItemVO> bidPreTrialItemVOS;

    @ApiModelProperty("专家")
    private List<BidSpecialistVO> bidSpecialistVOS;

    @ApiModelProperty("评分要素")
    private List<BidScoringElementsVO> bidScoringElementsVOS;

    @ApiModelProperty("招标物品")
    private List<BidMaterialVO> bidMaterialVOS;

    @ApiModelProperty("招标供应商")
    private List<BidVendorVO> bidVendorVOS;

    @ApiModelProperty("招标附件")
    private List<BidAttachmentVO> bidAttachmentVOS;

    @ApiModelProperty("单号")
    private String oddNumber;

    @ApiModelProperty("request状态")
    private String requestStatusName;

    @ApiModelProperty("request状态")
    private String vendorStatus;

}

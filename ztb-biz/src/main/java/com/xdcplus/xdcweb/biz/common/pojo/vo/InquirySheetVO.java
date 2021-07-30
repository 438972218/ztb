package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 询价单(InquirySheet)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:07:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InquirySheetVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 303200443582278177L;

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

    @ApiModelProperty("询价物品")
    private List<InquiryMaterialVO> inquiryMaterialVOS;

    @ApiModelProperty("询价供应商")
    private List<InquiryVendorVO> inquiryVendorVOS;

    @ApiModelProperty("询价附件")
    private List<InquiryAttachmentVO> inquiryAttachmentVOS;

    @ApiModelProperty("单号")
    private String oddNumber;

    @ApiModelProperty("request状态")
    private String requestStatusName;

    @ApiModelProperty("request状态")
    private String vendorStatus;

}

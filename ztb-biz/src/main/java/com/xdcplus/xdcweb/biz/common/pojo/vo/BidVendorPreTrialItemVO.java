package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标投标方预审项(BidVendorPreTrialItem)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidVendorPreTrialItemVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -20923308713576279L;

    @ApiModelProperty("供应商id")
    private Long bidVendorId;

    @ApiModelProperty("预审项id")
    private Long trialItemId;

    @ApiModelProperty("代码")
    private String code;

    @ApiModelProperty("资格预审")
    private String prequalification;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("是否需要附件")
    private Integer ifNeedAttachment;

    @ApiModelProperty("说明")
    private String explanation;

    @ApiModelProperty("申请说明")
    private String applyExplanation;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

}

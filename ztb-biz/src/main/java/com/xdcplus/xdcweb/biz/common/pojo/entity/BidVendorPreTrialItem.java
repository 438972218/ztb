package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标投标方预审项(BidVendorPreTrialItem)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:32
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_vendor_pre_trial_item")
public class BidVendorPreTrialItem implements Serializable {
    private static final long serialVersionUID = 561949276532020025L;

    @ApiModelProperty("信息主键")
    private Long id;

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

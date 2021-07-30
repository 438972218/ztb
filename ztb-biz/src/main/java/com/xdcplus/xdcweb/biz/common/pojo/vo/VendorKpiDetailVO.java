package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商绩效考核表明细(VendorKpiDetail)表VO类
 *
 * @author makejava
 * @since 2021-07-15 10:53:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorKpiDetailVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -14556200004472058L;

    @ApiModelProperty("绩效考核表ID")
    private Long vendorKpiId;

    @ApiModelProperty("考核指标")
    private String type;

    @ApiModelProperty("考核子指标")
    private String name;

    @ApiModelProperty("权重")
    private String balance;

    @ApiModelProperty("考察情况")
    private String content;

    @ApiModelProperty("得分范围")
    private String scoreScope;

    @ApiModelProperty("评分")
    private String score;

    @ApiModelProperty("最终得分")
    private String finalScore;

    @ApiModelProperty("等级")
    private String level;

    @ApiModelProperty("考察描述")
    private String insDesc;

    @ApiModelProperty("强制")
    private Integer ifForce;

    @ApiModelProperty("是否需要添加附件")
    private Integer ifAttachment;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

}

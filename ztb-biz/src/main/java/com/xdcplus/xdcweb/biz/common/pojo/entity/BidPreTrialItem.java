package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 预审项(BidPreTrialItem)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:24
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_pre_trial_item")
public class BidPreTrialItem implements Serializable {
    private static final long serialVersionUID = 472738914866494178L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

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

package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商现场考察表明细(VendorSiteInsDetail)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:09:24
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_vendor_site_ins_detail")
public class VendorSiteInsDetail implements Serializable {
    private static final long serialVersionUID = -59404581110827204L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("现场评审表ID")
    private Long vendorSiteInsId;

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

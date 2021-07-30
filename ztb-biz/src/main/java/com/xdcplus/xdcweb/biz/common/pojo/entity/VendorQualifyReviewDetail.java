package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:09:22
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_vendor_qualify_review_detail")
public class VendorQualifyReviewDetail implements Serializable {
    private static final long serialVersionUID = -13517833882007017L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("合格评审表ID")
    private Long vendorQualifyReviewId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("结果")
    private String result;

    @ApiModelProperty("强制")
    private Integer ifForce;

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

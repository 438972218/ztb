package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorQualifyReviewDetailDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 658551025096467059L;

    @ApiModelProperty("信息主键")
    private Long id;

    @NotNull(message = "绩效考核表ID不能为空")
    @ApiModelProperty("合格评审表ID")
    private Long vendorQualifyReviewId;

    @NotNull(message = "名称不能为空")
    @ApiModelProperty("名称")
    private String name;

    @NotNull(message = "内容不能为空")
    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("结果")
    private String result;

    @ApiModelProperty("强制")
    private Integer ifForce;

    @ApiModelProperty("是否需要添加附件")
    private Integer ifAttachment;

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

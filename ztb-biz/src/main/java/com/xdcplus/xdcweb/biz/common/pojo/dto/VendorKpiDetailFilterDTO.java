package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商绩效考核表明细(VendorKpiDetail)表查询入参DTO类
 *
 * @author makejava
 * @since 2021-07-15 10:53:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorKpiDetailFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 974067156026564458L;

    @ApiModelProperty("信息主键")
    private Long id;

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

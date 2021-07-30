package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商电子调查表明细(VendorQuestionDetail)表查询入参DTO类
 *
 * @author makejava
 * @since 2021-07-14 14:51:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorQuestionDetailFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -82781114875082744L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("电子调查表ID")
    private Long vendorQuestionId;

    @ApiModelProperty("调查指标")
    private String type;

    @ApiModelProperty("调查子指标")
    private String name;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("答复")
    private String response;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

    @ApiModelProperty("是否强制")
    private Integer ifForce;

    @ApiModelProperty("是否通过")
    private Integer ifPass;

    @ApiModelProperty("是否需要上传附件")
    private Integer ifAttachment;

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

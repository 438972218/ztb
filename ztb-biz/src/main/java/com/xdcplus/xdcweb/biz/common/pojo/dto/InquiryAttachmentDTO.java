package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 询价单附件(InquiryAttachment)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-05 09:59:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InquiryAttachmentDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 374407205967367858L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("询价单id")
    private Long inquirySheetId;

    @ApiModelProperty("附件类型")
    private String attachmentType;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

    @ApiModelProperty("上传人")
    private String uploadingUser;

    @ApiModelProperty("上传时间")
    private Long uploadingDate;

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

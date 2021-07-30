package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 询价供应商附件(InquiryVendorAttachment)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InquiryVendorAttachmentVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 599586443364901493L;

    @ApiModelProperty("供应商id")
    private Long inquiryVendorId;

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

}

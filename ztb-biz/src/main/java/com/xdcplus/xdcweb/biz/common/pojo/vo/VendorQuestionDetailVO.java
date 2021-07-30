package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商电子调查表明细(VendorQuestionDetail)表VO类
 *
 * @author makejava
 * @since 2021-07-14 14:51:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorQuestionDetailVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -26926002226688908L;

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

}

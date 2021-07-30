package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表VO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorQualifyReviewDetailVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 779403501006827079L;

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

    @ApiModelProperty("是否需要添加附件")
    private Integer ifAttachment;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

}

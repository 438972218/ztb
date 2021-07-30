package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商合格评审表明细(VendorQualifyReviewDetail)表查询条件类
 *
 * @author makejava
 * @since 2021-07-12 20:52:52
 */
@Data
@SuppressWarnings("serial")
public class VendorQualifyReviewDetailQuery implements Serializable {
    private static final long serialVersionUID = -76667303626901228L;

    private Long id;

    private Long vendorQualifyReviewId;

    private String name;

    private String content;

    private String result;

    private Integer ifForce;

    private Integer ifAttachment;

    private String attachmentName;

    private String attachmentUrl;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

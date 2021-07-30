package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商绩效考核表明细(VendorKpiDetail)表查询条件类
 *
 * @author makejava
 * @since 2021-07-15 10:53:33
 */
@Data
@SuppressWarnings("serial")
public class VendorKpiDetailQuery implements Serializable {
    private static final long serialVersionUID = -82459424734472886L;

    private Long id;

    private Long vendorKpiId;

    private String type;

    private String name;

    private String balance;

    private String content;

    private String scoreScope;

    private String score;

    private String finalScore;

    private String level;

    private String insDesc;

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

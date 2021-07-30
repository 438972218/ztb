package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商电子调查表明细(VendorQuestionDetail)表查询条件类
 *
 * @author makejava
 * @since 2021-07-14 14:51:21
 */
@Data
@SuppressWarnings("serial")
public class VendorQuestionDetailQuery implements Serializable {
    private static final long serialVersionUID = -63507199897514471L;

    private Long id;

    private Long vendorQuestionId;

    private String type;

    private String name;

    private String content;

    private String response;

    private String attachmentName;

    private String attachmentUrl;

    private Integer ifForce;

    private Integer ifPass;

    private Integer ifAttachment;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

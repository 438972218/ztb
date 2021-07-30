package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商电子调查表(VendorQuestion)表查询条件类
 *
 * @author makejava
 * @since 2021-07-15 10:53:34
 */
@Data
@SuppressWarnings("serial")
public class VendorQuestionQuery implements Serializable {
    private static final long serialVersionUID = -53793096897579889L;

    private Long id;

    private Long requestId;

    private Long vendorId;

    private String vendorDesc;

    private Long responseExpireTime;

    private String responsiblePerson;

    private String name;

    private String result;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

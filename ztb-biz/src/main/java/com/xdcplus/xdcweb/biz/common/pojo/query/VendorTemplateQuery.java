package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 模板表(VendorTemplate)表查询条件类
 *
 * @author makejava
 * @since 2021-07-15 10:54:09
 */
@Data
@SuppressWarnings("serial")
public class VendorTemplateQuery implements Serializable {
    private static final long serialVersionUID = 718275254361559399L;

    private Long id;

    private String type;

    private String name;

    private String insDesc;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

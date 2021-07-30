package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户供应商中间表(UserVendor)表查询条件类
 *
 * @author makejava
 * @since 2021-07-20 11:06:57
 */
@Data
@SuppressWarnings("serial")
public class UserVendorQuery implements Serializable {
    private static final long serialVersionUID = 942731386385712888L;

    private Long id;

    private Long userId;

    private Long vendorId;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

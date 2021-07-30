package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户品类中间表(UserCategory)表查询条件类
 *
 * @author makejava
 * @since 2021-07-20 11:06:51
 */
@Data
@SuppressWarnings("serial")
public class UserCategoryQuery implements Serializable {
    private static final long serialVersionUID = -67283652896169243L;

    private Long id;

    private Long userId;

    private Long categoryId;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户公司中间表(UserCompany)表查询条件类
 *
 * @author makejava
 * @since 2021-07-22 15:38:39
 */
@Data
@SuppressWarnings("serial")
public class UserCompanyQuery implements Serializable {
    private static final long serialVersionUID = 102926811572626936L;

    private Long id;

    private Long userId;

    private Long companyId;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商绩效人员表(VendorKpiUser)表查询条件类
 *
 * @author makejava
 * @since 2021-07-22 09:48:10
 */
@Data
@SuppressWarnings("serial")
public class VendorKpiUserQuery implements Serializable {
    private static final long serialVersionUID = -94758467579079297L;

    private Long id;

    private Long vendorKpiId;

    private Long userId;

    private String name;

    private String userName;

    private String userDept;

    private String siteInsKpi;

    private Integer ifLeader;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

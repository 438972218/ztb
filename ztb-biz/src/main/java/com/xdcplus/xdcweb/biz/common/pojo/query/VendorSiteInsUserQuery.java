package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商现场考察人员表(VendorSiteInsUser)表查询条件类
 *
 * @author makejava
 * @since 2021-07-20 15:47:31
 */
@Data
@SuppressWarnings("serial")
public class VendorSiteInsUserQuery implements Serializable {
    private static final long serialVersionUID = 284213059311813573L;

    private Long id;

    private Long vendorSiteInsId;

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

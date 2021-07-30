package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商绩效人员评价表(VendorKpiDetailUser)表查询条件类
 *
 * @author makejava
 * @since 2021-07-22 10:18:21
 */
@Data
@SuppressWarnings("serial")
public class VendorKpiDetailUserQuery implements Serializable {
    private static final long serialVersionUID = -47375070218575199L;

    private Long id;

    private Long vendorKpiDetailId;

    private Long vendorKpiUserId;

    private String score;

    private String insDesc;

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

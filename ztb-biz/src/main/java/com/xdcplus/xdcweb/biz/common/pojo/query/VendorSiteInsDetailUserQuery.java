package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表查询条件类
 *
 * @author makejava
 * @since 2021-07-20 15:47:28
 */
@Data
@SuppressWarnings("serial")
public class VendorSiteInsDetailUserQuery implements Serializable {
    private static final long serialVersionUID = -62095229772639984L;

    private Long id;

    private Long vendorSiteInsDetailId;

    private Long vendorSiteInsUserId;

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

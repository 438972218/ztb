package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商证书(VendorCertificate)表查询条件类
 *
 * @author makejava
 * @since 2021-07-12 20:52:49
 */
@Data
@SuppressWarnings("serial")
public class VendorCertificateQuery implements Serializable {
    private static final long serialVersionUID = -13852914654763309L;

    private Long id;

    private Long vendorId;

    private String name;

    private String type;

    private String attachmentName;

    private String attachmentUrl;

    private Long expireTime;

    private Long uploadTime;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

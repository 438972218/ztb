package com.xdcplus.xdcweb.biz.common.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 供应商注册单证书(VendorRequestCertificate)表查询条件类
 *
 * @author makejava
 * @since 2021-07-26 15:47:59
 */
@Data
@SuppressWarnings("serial")
public class VendorRequestCertificateQuery implements Serializable {
    private static final long serialVersionUID = 723456755657175747L;

    private Long id;

    private Long vendorRequestId;

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

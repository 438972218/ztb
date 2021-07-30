package com.xdcplus.xdcweb.biz.common.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 供应商注册单品类(VendorRequestCategory)表查询条件类
 *
 * @author makejava
 * @since 2021-07-26 15:47:58
 */
@Data
@SuppressWarnings("serial")
public class VendorRequestCategoryQuery implements Serializable {
    private static final long serialVersionUID = 126261424406266379L;

    private Long id;

    private Long vendorRequestId;

    private Long categoryId;

    private String categoryName;

    private String
            supplyOrganization;

    private String validStatus;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商品类(VendorCategory)表查询条件类
 *
 * @author makejava
 * @since 2021-07-15 10:01:10
 */
@Data
@SuppressWarnings("serial")
public class VendorCategoryQuery implements Serializable {
    private static final long serialVersionUID = 776905029467856948L;

    private Long id;

    private Long vendorId;

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

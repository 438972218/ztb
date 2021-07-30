package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商合格评审表(VendorQualifyReview)表查询条件类
 *
 * @author makejava
 * @since 2021-07-12 20:52:52
 */
@Data
@SuppressWarnings("serial")
public class VendorQualifyReviewQuery implements Serializable {
    private static final long serialVersionUID = 370990012775506304L;

    private Long id;

    private Long requestId;

    private Long vendorId;

    private String scoringFormula;

    private String responsiblePerson;

    private String vendorDesc;

    private String name;

    private String result;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

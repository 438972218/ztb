package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商绩效考核表(VendorKpi)表查询条件类
 *
 * @author makejava
 * @since 2021-07-12 20:52:50
 */
@Data
@SuppressWarnings("serial")
public class VendorKpiQuery implements Serializable {
    private static final long serialVersionUID = -17650826086687767L;

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

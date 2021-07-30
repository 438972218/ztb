package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 模板题库中间表(VendorTemplateQbank)表查询条件类
 *
 * @author makejava
 * @since 2021-07-15 10:16:45
 */
@Data
@SuppressWarnings("serial")
public class VendorTemplateQbankQuery implements Serializable {
    private static final long serialVersionUID = -10714836116765026L;

    private Long id;

    private Long templateId;

    private Long questionBankId;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

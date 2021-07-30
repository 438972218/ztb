package com.xdcplus.xdcweb.biz.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class Circulation implements Serializable {

    private static final long serialVersionUID = -91167096272246573L;

    private String description;

    /**
     * 流转条件,
     */
    @ApiModelProperty(value = "流转条件")
    private Object flowConditions;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

}

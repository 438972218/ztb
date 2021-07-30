package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class RequestDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -91167096272246572L;

    @ApiModelProperty("id")
    private Long requestId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("添加人")
    private String createdUser;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

    @ApiModelProperty(value = "父级表单")
    private Set<Long> parentIds;

    /**
     * 流转条件
     */
    @ApiModelProperty(value = "流转条件")
    private Circulation circulation;


}

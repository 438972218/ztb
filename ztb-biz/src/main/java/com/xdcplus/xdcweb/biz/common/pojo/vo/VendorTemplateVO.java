package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 模板表(VendorTemplate)表VO类
 *
 * @author makejava
 * @since 2021-07-15 10:54:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorTemplateVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 793719607843480906L;

    @ApiModelProperty("模板类型")
    private String type;

    @ApiModelProperty("模板名称")
    private String name;

    @ApiModelProperty("模板描述")
    private String insDesc;

}

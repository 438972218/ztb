package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典管理(Dictionary)表查询条件类
 *
 * @author makejava
 * @since 2021-07-02 10:28:06
 */
@Data
@SuppressWarnings("serial")
public class DictionaryQuery implements Serializable {
    private static final long serialVersionUID = -33350771700425249L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("字典名称")
    private String dictionaryChinese;

    @ApiModelProperty("字典类名")
    private String dictionaryClass;

    @ApiModelProperty("下拉中文名")
    private String meaning;

    @ApiModelProperty("下拉ID")
    private Integer numerical;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("修改人")
    private String updatedUser;

    @ApiModelProperty("修改时间")
    private Long updatedTime;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;

}

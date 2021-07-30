package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 模板题库中间表(VendorTemplateQbank)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:09:24
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_vendor_template_qbank")
public class VendorTemplateQbank implements Serializable {
    private static final long serialVersionUID = -54630706694506762L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("模板ID")
    private Long templateId;

    @ApiModelProperty("问题ID")
    private Long questionBankId;

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

package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商绩效人员表(VendorKpiUser)表实体类
 *
 * @author makejava
 * @since 2021-07-22 09:48:08
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_vendor_kpi_user")
public class VendorKpiUser implements Serializable {
    private static final long serialVersionUID = 702044070949635273L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("绩效表ID")
    private Long vendorKpiId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户账户")
    private String userName;

    @ApiModelProperty("用户部门")
    private String userDept;

    @ApiModelProperty("考核指标")
    private String siteInsKpi;

    @ApiModelProperty("考察组长")
    private Integer ifLeader;

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

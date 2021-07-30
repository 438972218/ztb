package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商绩效人员表(VendorKpiUser)表查询入参DTO类
 *
 * @author makejava
 * @since 2021-07-22 09:48:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorKpiUserFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 221900081301965478L;

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

package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 供应商现场考察人员表(VendorSiteInsUser)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-20 15:47:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorSiteInsUserDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -46275871814532580L;

    @ApiModelProperty("信息主键")
    private Long id;

    @NotNull(message = "现场考察ID不能为空")
    @ApiModelProperty("现场考察ID")
    private Long vendorSiteInsId;


    @ApiModelProperty("用户ID")
    private Long userId;

    @NotNull(message = "用户名称不能为空")
    @ApiModelProperty("用户名称")
    private String name;

    @NotNull(message = "用户账户不能为空")
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

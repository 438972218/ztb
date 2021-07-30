package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商绩效人员评价表(VendorKpiDetailUser)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-22 10:18:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorKpiDetailUserDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -51548276756702801L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("绩效考核明细表ID")
    private Long vendorKpiDetailId;

    @ApiModelProperty("考核人员表ID")
    private Long vendorKpiUserId;

    @ApiModelProperty("评分")
    private String score;

    @ApiModelProperty("考察描述")
    private String insDesc;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

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

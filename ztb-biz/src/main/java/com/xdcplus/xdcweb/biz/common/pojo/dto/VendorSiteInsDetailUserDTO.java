package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-20 15:47:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorSiteInsDetailUserDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -59123104451569563L;

    @ApiModelProperty("信息主键")
    private Long id;

    @NotNull(message = "现场评审明细表ID不能为空")
    @ApiModelProperty("现场评审明细表ID")
    private Long vendorSiteInsDetailId;

    @NotNull(message = "考察人员表ID不能为空")
    @ApiModelProperty("考察人员表ID")
    private Long vendorSiteInsUserId;

    @NotNull(message = "评分不能为空")
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

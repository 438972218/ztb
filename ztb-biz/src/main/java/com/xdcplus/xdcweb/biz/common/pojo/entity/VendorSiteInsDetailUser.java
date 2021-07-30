package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表实体类
 *
 * @author makejava
 * @since 2021-07-20 15:47:27
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_vendor_site_ins_detail_user")
public class VendorSiteInsDetailUser implements Serializable {
    private static final long serialVersionUID = -75800145881073392L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("现场评审明细表ID")
    private Long vendorSiteInsDetailId;

    @ApiModelProperty("考察人员表ID")
    private Long vendorSiteInsUserId;

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

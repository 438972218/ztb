package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 询价供应商(InquiryVendor)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-07-08 10:48:03
 */
@Data
@SuppressWarnings("serial")
public class InquiryVendorQuery implements Serializable {
    private static final long serialVersionUID = -27584325696756552L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("询价单id")
    private Long inquirySheetId;

    @ApiModelProperty("供应商代码")
    private String vendorCode;

    @ApiModelProperty("供应商名称")
    private String vendorName;

    @ApiModelProperty("供应商描述")
    private String vendorDescription;

    @ApiModelProperty("供应商状态")
    private String vendorStatus;

    @ApiModelProperty("是否黑名单")
    private Integer ifBlacklist;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("联系电话")
    private String phone;

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

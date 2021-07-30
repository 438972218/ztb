package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 竞价供应商(PaidVendor)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:43:45
 */
@Data
@SuppressWarnings("serial")
public class PaidVendorQuery implements Serializable {
    private static final long serialVersionUID = 165864262488494917L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("序号")
    private Long paidSheetId;

    @ApiModelProperty("供应商代码")
    private String vendorCode;

    @ApiModelProperty("供应商名称")
    private String vendorName;

    @ApiModelProperty("供应商状态")
    private String vendorStatus;

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

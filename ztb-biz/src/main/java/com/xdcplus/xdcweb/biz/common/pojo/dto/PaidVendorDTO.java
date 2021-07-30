package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价供应商(PaidVendor)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:43:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidVendorDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -23714872774885993L;

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

    @ApiModelProperty("物品id")
    private Long materialId;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;

}

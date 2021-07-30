package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商注册单证书(VendorRequestCertificate)表实体类
 *
 * @author makejava
 * @since 2021-07-26 15:47:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_vendor_request_certificate")
public class VendorRequestCertificate extends Model<VendorRequestCertificate> implements Serializable {
    private static final long serialVersionUID = -23060656541215711L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("供应商ID")
    private Long vendorRequestId;

    @ApiModelProperty("证件名称")
    private String name;

    @ApiModelProperty("证件类型")
    private String type;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

    @ApiModelProperty("有效期")
    private Long expireTime;

    @ApiModelProperty("上传时间")
    private Long uploadTime;

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

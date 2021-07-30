package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 供应商证书(VendorCertificate)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorCertificateDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 407119934759476200L;

    @ApiModelProperty("信息主键")
    private Long id;

    @NotNull(message="供应商ID不能为空")
    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @NotNull(message="证件名称不能为空")
    @ApiModelProperty("证件名称")
    private String name;

    @NotNull(message="证件类型不能为空")
    @ApiModelProperty("证件类型")
    private String type;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

    @NotNull(message="有效期不能为空")
    @ApiModelProperty("有效期")
    private Long expireTime;

    @NotNull(message="上传时间不能为空")
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

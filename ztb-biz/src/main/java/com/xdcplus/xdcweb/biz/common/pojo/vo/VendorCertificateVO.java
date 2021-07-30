package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商证书(VendorCertificate)表VO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorCertificateVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -23111719489140996L;

    @ApiModelProperty("供应商ID")
    private Long vendorId;

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

}

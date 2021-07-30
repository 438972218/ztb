package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 询价供应商(InquiryVendor)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-08 10:48:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InquiryVendorVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 887161805973017286L;

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

}

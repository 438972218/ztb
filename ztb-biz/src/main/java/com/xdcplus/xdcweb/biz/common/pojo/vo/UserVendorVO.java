package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户供应商中间表(UserVendor)表VO类
 *
 * @author makejava
 * @since 2021-07-20 11:06:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class UserVendorVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -97216416590764747L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @ApiModelProperty("用户")
    private UserVO userVO;

    @ApiModelProperty("供应商")
    private VendorVO vendorVO;

}

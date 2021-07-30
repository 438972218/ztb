package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户采购组织中间表(UserPurchaseOrz)表VO类
 *
 * @author makejava
 * @since 2021-07-20 11:06:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class UserPurchaseOrzVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 916994528106692409L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("采购组织ID")
    private Long purchaseOrzId;

    @ApiModelProperty("用户")
    private UserVO userVO;

    @ApiModelProperty("采购组织")
    private PurchaseOrzVO purchaseOrzVO;

}

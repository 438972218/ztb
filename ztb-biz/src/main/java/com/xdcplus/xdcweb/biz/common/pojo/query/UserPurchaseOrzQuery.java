package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户采购组织中间表(UserPurchaseOrz)表查询条件类
 *
 * @author makejava
 * @since 2021-07-20 11:06:55
 */
@Data
@SuppressWarnings("serial")
public class UserPurchaseOrzQuery implements Serializable {
    private static final long serialVersionUID = -92104382884879067L;

    private Long id;

    private Long userId;

    private Long purchaseOrzId;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

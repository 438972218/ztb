package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户采购组织中间表(UserPurchaseOrz)表实体类
 *
 * @author makejava
 * @since 2021-07-20 11:06:54
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_user_purchase_orz")
public class UserPurchaseOrz implements Serializable {
    private static final long serialVersionUID = 303443677848539087L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("采购组织ID")
    private Long purchaseOrzId;

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

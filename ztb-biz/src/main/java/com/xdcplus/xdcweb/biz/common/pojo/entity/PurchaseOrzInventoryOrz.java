package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:09:20
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_purchase_orz_inventory_orz")
public class PurchaseOrzInventoryOrz implements Serializable {
    private static final long serialVersionUID = 903567888669518563L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("采购组织ID")
    private Long purchaseOrzId;

    @ApiModelProperty("库存组织表中的ID")
    private Long inventoryOrzId;

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

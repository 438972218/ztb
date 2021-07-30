package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表查询条件类
 *
 * @author makejava
 * @since 2021-07-14 10:43:28
 */
@Data
@SuppressWarnings("serial")
public class PurchaseOrzInventoryOrzQuery implements Serializable {
    private static final long serialVersionUID = 114367090142830146L;

    private Long id;

    private Long purchaseOrzId;

    private Long inventoryOrzId;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}

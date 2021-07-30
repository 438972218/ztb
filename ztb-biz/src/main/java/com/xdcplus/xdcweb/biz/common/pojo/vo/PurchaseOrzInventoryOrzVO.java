package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表VO类
 *
 * @author makejava
 * @since 2021-07-14 10:43:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PurchaseOrzInventoryOrzVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 839120430142978596L;

    @ApiModelProperty("采购组织ID")
    private Long purchaseOrzId;

    @ApiModelProperty("库存组织表中的ID")
    private Long inventoryOrzId;

    @ApiModelProperty("库存组织表中的IDs")
    private List<Long> inventoryOrzIds;

}

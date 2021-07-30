package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表查询入参DTO类
 *
 * @author makejava
 * @since 2021-07-14 10:43:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PurchaseOrzInventoryOrzFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 498742324871017840L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("采购组织ID")
    private Long purchaseOrzId;

    @ApiModelProperty("库存组织表中的ID")
    private Long inventoryOrzId;

    @ApiModelProperty("库存组织表中的IDS")
    private List<Long> inventoryOrzIds;

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

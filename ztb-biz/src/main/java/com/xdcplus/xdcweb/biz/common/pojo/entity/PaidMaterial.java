package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价物品(PaidMaterial)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:09:01
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_paid_material")
public class PaidMaterial implements Serializable {
    private static final long serialVersionUID = 865548005452832066L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("竞价单id")
    private Long paidSheetId;

    @ApiModelProperty("库存组织")
    private String inventoryOrganization;

    @ApiModelProperty("物品编码")
    private String materialCode;

    @ApiModelProperty("物品描述")
    private String materialDescription;

    @ApiModelProperty("参考品牌")
    private String referToBrand;

    @ApiModelProperty("物品分类")
    private String materialClassify;

    @ApiModelProperty("需求日期")
    private Long demandedDate;

    @ApiModelProperty("需求数量")
    private Integer demandedQuantity;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("含税")
    private Integer taxInclusive;

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

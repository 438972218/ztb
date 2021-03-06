package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 物料管理(Item)表查询条件类
 *
 * @author makejava
 * @since 2021-07-02 10:28:13
 */
@Data
@SuppressWarnings("serial")
public class ItemQuery implements Serializable {
    private static final long serialVersionUID = -84468202798875555L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("物品编码")
    private String itemCode;

    @ApiModelProperty("物品名称")
    private String itemName;

    @ApiModelProperty("通用名称")
    private String genName;

    @ApiModelProperty("物品分类")
    private String itemType;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("产地")
    private String originPlace;

    @ApiModelProperty("基本计量单位")
    private String basicMeasure;

    @ApiModelProperty("启用辅助计量")
    private Integer ifAssitMeasure;

    @ApiModelProperty("辅助计量单位")
    private String assitMeasure;

    @ApiModelProperty("是否进口")
    private Integer ifImport;

    @ApiModelProperty("代理商")
    private String agent;

    @ApiModelProperty("包装单位")
    private String packageUnit;

    @ApiModelProperty("毛重")
    private String roughWeight;

    @ApiModelProperty("净重")
    private String netWeight;

    @ApiModelProperty("重量单位")
    private String weightUnit;

    @ApiModelProperty("规格")
    private String specification;

    @ApiModelProperty("型号")
    private String model;

    @ApiModelProperty("制造商")
    private String manufacturer;

    @ApiModelProperty("体积")
    private String volume;

    @ApiModelProperty("体积单位")
    private String volumeUnit;

    @ApiModelProperty("商品流通码")
    private String circulationCode;

    @ApiModelProperty("是否计税")
    private Integer ifTax;

    @ApiModelProperty("默认税种")
    private String defaultTaxType;

    @ApiModelProperty("默认税率")
    private String defaultTaxRate;

    @ApiModelProperty("物品管理方式")
    private String itemManagementMethods;

    @ApiModelProperty("物品额度管理")
    private String itemQuotaManagement;

    @ApiModelProperty("批号规则")
    private String batchNumberRule;

    @ApiModelProperty("是否用于销售")
    private Integer ifUseSale;

    @ApiModelProperty("是否用于采购")
    private Integer ifUsePurchase;

    @ApiModelProperty("产品层次")
    private String productHierarchy;

    @ApiModelProperty("物品描述")
    private String itemDesc;

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

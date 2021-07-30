package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 询价物品(InquiryMaterial)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:59
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_inquiry_material")
public class InquiryMaterial implements Serializable {
    private static final long serialVersionUID = 366820661344643994L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("询价单id")
    private Long inquirySheetId;

    @ApiModelProperty("库存组织")
    private String inventoryOrganization;

    @ApiModelProperty("物品编码")
    private String materialCode;

    @ApiModelProperty("物品描述")
    private String materialDescription;

    @ApiModelProperty("物品说明")
    private String materialExplain;

    @ApiModelProperty("报价明细")
    private String quoteDetails;

    @ApiModelProperty("数量")
    private Integer quantity;

    @ApiModelProperty("单位")
    private String unit;

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

package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标专家评分(BidSpecialistScore)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:30
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_specialist_score")
public class BidSpecialistScore implements Serializable {
    private static final long serialVersionUID = -50530273419983990L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("供应商id")
    private Long bidVendorId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("投标总价")
    private Double bidTotalPrice;

    @ApiModelProperty("最低价比重")
    private String minPriceRatio;

    @ApiModelProperty("评分")
    private String score;

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

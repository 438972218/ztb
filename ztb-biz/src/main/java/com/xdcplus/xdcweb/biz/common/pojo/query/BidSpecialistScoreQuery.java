package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 招标专家评分(BidSpecialistScore)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:15
 */
@Data
@SuppressWarnings("serial")
public class BidSpecialistScoreQuery implements Serializable {
    private static final long serialVersionUID = -90239222931613400L;

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

    @ApiModelProperty("sheetId")
    private Long bidSheetId;

}

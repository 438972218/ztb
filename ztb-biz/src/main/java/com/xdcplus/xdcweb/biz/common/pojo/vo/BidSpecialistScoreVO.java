package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标专家评分(BidSpecialistScore)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:12:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSpecialistScoreVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -44790919504656861L;

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

    private BidVendorVO bidVendorVO;

}

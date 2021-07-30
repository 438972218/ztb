package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商绩效人员评价表(VendorKpiDetailUser)表VO类
 *
 * @author makejava
 * @since 2021-07-22 10:18:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorKpiDetailUserVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -91453094120513399L;

    @ApiModelProperty("绩效考核明细表ID")
    private Long vendorKpiDetailId;

    @ApiModelProperty("考核人员表ID")
    private Long vendorKpiUserId;

    @ApiModelProperty("评分")
    private String score;

    @ApiModelProperty("考察描述")
    private String insDesc;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

}

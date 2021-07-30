package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表VO类
 *
 * @author makejava
 * @since 2021-07-20 15:47:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorSiteInsDetailUserVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -83397949840052006L;

    @ApiModelProperty("现场评审明细表ID")
    private Long vendorSiteInsDetailId;

    @ApiModelProperty("考察人员表ID")
    private Long vendorSiteInsUserId;

    @ApiModelProperty("评分")
    private String score;

    @ApiModelProperty("考察描述")
    private String insDesc;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

}

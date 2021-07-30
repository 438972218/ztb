package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标投标方(BidVendor)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-09 10:01:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidVendorVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -48034251181205545L;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("投标方编码")
    private String vendorCode;

    @ApiModelProperty("投标方名称")
    private String vendorName;

    @ApiModelProperty("投标方描述")
    private String vendorDescription;

    @ApiModelProperty("投标方状态")
    private String vendorStatus;

    @ApiModelProperty("总价")
    private Double totalPrice;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("资审通过")
    private String informationPass;

    @ApiModelProperty("审批意见")
    private String approvalOpinion;

    @ApiModelProperty("总评分")
    private Double totalScore;

}

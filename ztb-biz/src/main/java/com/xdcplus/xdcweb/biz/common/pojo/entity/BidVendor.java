package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标投标方(BidVendor)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:31
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_vendor")
public class BidVendor implements Serializable {
    private static final long serialVersionUID = -72346775716750545L;

    @ApiModelProperty("信息主键")
    private Long id;

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

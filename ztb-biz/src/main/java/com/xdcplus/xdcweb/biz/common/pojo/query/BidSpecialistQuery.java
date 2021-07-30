package com.xdcplus.xdcweb.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 专家(BidSpecialist)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:10
 */
@Data
@SuppressWarnings("serial")
public class BidSpecialistQuery implements Serializable {
    private static final long serialVersionUID = 826734420590093331L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("组类型")
    private String groupType;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("专家子账户")
    private String specialistSubAccount;

    @ApiModelProperty("专家姓名")
    private String specialistName;

    @ApiModelProperty("专家来源")
    private String specialistSource;

    @ApiModelProperty("专家组长")
    private Integer specialistGroupLeader;

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

package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 专家(BidSpecialist)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSpecialistVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 418461950337592172L;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("组类型")
    private String groupType;

    @ApiModelProperty("专家子账户")
    private String specialistSubAccount;

    @ApiModelProperty("专家姓名")
    private String specialistName;

    @ApiModelProperty("专家来源")
    private String specialistSource;

    @ApiModelProperty("专家组长")
    private Integer specialistGroupLeader;

}

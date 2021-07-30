package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 预审项(BidPreTrialItem)表查询入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidPreTrialItemFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 184255436671974358L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("代码")
    private String code;

    @ApiModelProperty("资格预审")
    private String prequalification;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("是否需要附件")
    private Integer ifNeedAttachment;

    @ApiModelProperty("说明")
    private String explanation;

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

    @ApiModelProperty("供应商id")
    private Long bidVendorId;

}

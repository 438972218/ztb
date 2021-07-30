package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 预审项(BidPreTrialItem)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidPreTrialItemVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -16105617236688611L;

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

    @ApiModelProperty("供应商资格预审项")
    private BidVendorPreTrialItemVO bidVendorPreTrialItemVO;

}

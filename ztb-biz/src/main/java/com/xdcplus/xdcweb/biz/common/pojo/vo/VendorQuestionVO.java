package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 供应商电子调查表(VendorQuestion)表VO类
 *
 * @author makejava
 * @since 2021-07-15 10:53:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorQuestionVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 560388749165413176L;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @ApiModelProperty("供应商")
    private VendorVO vendorVO;


    @ApiModelProperty("供应商描述")
    private String vendorDesc;

    @ApiModelProperty("供应商响应时间")
    private Long responseExpireTime;

    @ApiModelProperty("负责人")
    private String responsiblePerson;

    @ApiModelProperty("调查表名称")
    private String name;

    @ApiModelProperty("调查结果")
    private String result;

    @ApiModelProperty("单号")
    private String oddNumber;

    @ApiModelProperty("request状态")
    private String requestStatusName;

    @ApiModelProperty("request状态")
    private String vendorStatus;

    @ApiModelProperty("调查表明细")
    private List<VendorQuestionDetailVO> vendorQuestionDetailVOS;

}

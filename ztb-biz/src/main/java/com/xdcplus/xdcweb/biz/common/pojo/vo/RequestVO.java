package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class RequestVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -60412008760522731L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("单号")
    private String oddNumber;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("request状态")
    private RequestStatusVO status;

    @ApiModelProperty("父级表单信息")
    private List<RequestVO> parent;

}

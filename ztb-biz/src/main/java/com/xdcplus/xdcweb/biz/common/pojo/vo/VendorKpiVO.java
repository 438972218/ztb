package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 供应商绩效考核表(VendorKpi)表VO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorKpiVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -33289872806867895L;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @ApiModelProperty("供应商")
    private VendorVO vendorVO;

    @ApiModelProperty("计分方式")
    private String scoringFormula;

    @ApiModelProperty("负责人")
    private String responsiblePerson;

    @ApiModelProperty("供应商描述")
    private String vendorDesc;

    @ApiModelProperty("绩效考核表名称")
    private String name;

    @ApiModelProperty("考察结果")
    private String result;

    @ApiModelProperty("绩效考核明细")
    private List<VendorKpiDetailVO> vendorKpiDetailVOS;

    @ApiModelProperty("绩效考核人员明细")
    private List<VendorKpiUserVO> vendorKpiUserVOS;

    @ApiModelProperty("单号")
    private String oddNumber;

    @ApiModelProperty("request状态")
    private String requestStatusName;

    @ApiModelProperty("request状态")
    private String vendorStatus;

}

package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 供应商现场考察表(VendorSiteIns)表VO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorSiteInsVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -88860664191782715L;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @ApiModelProperty("计分方式")
    private String scoringFormula;

    @ApiModelProperty("负责人")
    private String responsiblePerson;

    @ApiModelProperty("供应商描述")
    private String vendorDesc;

    @ApiModelProperty("考察表名称")
    private String name;

    @ApiModelProperty("考察结果")
    private String result;

    @ApiModelProperty("供应商")
    private VendorVO vendorVO;

    @ApiModelProperty("供应商现场考察表明细")
    private List<VendorSiteInsDetailVO> vendorSiteInsDetailVOS;

    @ApiModelProperty("供应商现场考察人员")
    private List<VendorSiteInsUserVO> vendorSiteInsUserVOS;

    @ApiModelProperty("单号")
    private String oddNumber;

    @ApiModelProperty("request状态")
    private String requestStatusName;


}

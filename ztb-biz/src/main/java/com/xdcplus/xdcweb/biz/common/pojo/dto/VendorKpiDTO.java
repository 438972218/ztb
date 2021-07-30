package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 供应商绩效考核表(VendorKpi)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorKpiDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -56527947274167881L;

    @ApiModelProperty("信息主键")
    private Long id;


    @ApiModelProperty("requestId")
    private Long requestId;

    @NotNull(message = "供应商ID不能为空")
    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @ApiModelProperty("计分方式")
    private String scoringFormula;

    @ApiModelProperty("负责人")
    private String responsiblePerson;

    @ApiModelProperty("供应商描述")
    private String vendorDesc;

    @NotNull(message = "绩效考核表名称不能为空")
    @ApiModelProperty("绩效考核表名称")
    private String name;

    @ApiModelProperty("考察结果")
    private String result;

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

    @ApiModelProperty("绩效考核明细")
    private List<VendorKpiDetailDTO> vendorKpiDetailDTOS;

    @ApiModelProperty("绩效考核人员")
    private List<VendorKpiUserDTO> vendorKpiUserDTOS;

    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

}

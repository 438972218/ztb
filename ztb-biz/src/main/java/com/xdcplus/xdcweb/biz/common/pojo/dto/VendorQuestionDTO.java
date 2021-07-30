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
 * 供应商电子调查表(VendorQuestion)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-15 10:53:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorQuestionDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 270761235498859678L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @NotNull(message = "供应商ID不能为空")
    @ApiModelProperty("供应商ID")
    private Long vendorId;

    @ApiModelProperty("供应商描述")
    private String vendorDesc;

    @ApiModelProperty("供应商响应时间")
    private Long responseExpireTime;

    @ApiModelProperty("负责人")
    private String responsiblePerson;

    @NotNull(message = "调查表名称不能为空")
    @ApiModelProperty("调查表名称")
    private String name;

    @ApiModelProperty("调查结果")
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

    @ApiModelProperty("调查明细")
    private List<VendorQuestionDetailDTO> vendorQuestionDetailDTOS;

    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

}

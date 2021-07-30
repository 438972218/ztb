package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewDetailVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 供应商合格评审表(VendorQualifyReview)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorQualifyReviewDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -35761239668822964L;

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

    @NotNull(message = "合格评审表名称不能为空")
    @ApiModelProperty("合格评审表名称")
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

    @ApiModelProperty("供应商合格评审表明细")
    private List<VendorQualifyReviewDetailDTO> vendorQualifyReviewDetailDTOS;

    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

}

package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 询价物品(InquiryMaterial)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:17:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InquiryMaterialDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 603263543756412123L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("询价单id")
    private Long inquirySheetId;

    @NotNull(message = "库存组织不能为空")
    @ApiModelProperty("库存组织")
    private String inventoryOrganization;

    @NotNull(message = "物品编码不能为空")
    @ApiModelProperty("物品编码")
    private String materialCode;

    @ApiModelProperty("物品描述")
    private String materialDescription;

    @ApiModelProperty("物品说明")
    private String materialExplain;

    @ApiModelProperty("报价明细")
    private String quoteDetails;

    @NotNull(message = "数量不能为空")
    @ApiModelProperty("数量")
    private Integer quantity;

    @NotNull(message = "单位不能为空")
    @ApiModelProperty("单位")
    private String unit;

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

}

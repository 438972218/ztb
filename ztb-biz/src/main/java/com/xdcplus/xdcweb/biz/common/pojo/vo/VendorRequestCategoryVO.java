package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商注册单品类(VendorRequestCategory)表VO类
 *
 * @author makejava
 * @since 2021-07-26 15:47:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorRequestCategoryVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 744064883584209994L;

    @ApiModelProperty("供应商ID")
    private Long vendorRequestId;

    @ApiModelProperty("品类ID")
    private Long categoryId;

    @ApiModelProperty("品类名称")
    private String categoryName;

    @ApiModelProperty("供应组织")
    private String
            supplyOrganization;

    @ApiModelProperty("有效状态")
    private String validStatus;

}

package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商品类(VendorCategory)表VO类
 *
 * @author makejava
 * @since 2021-07-15 10:01:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorCategoryVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -41830889946694124L;

    @ApiModelProperty("供应商ID")
    private Long vendorId;

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

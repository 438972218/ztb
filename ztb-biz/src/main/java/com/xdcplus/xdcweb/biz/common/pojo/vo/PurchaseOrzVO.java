package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 采购组织(PurchaseOrz)表VO类
 *
 * @author makejava
 * @since 2021-07-08 11:35:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PurchaseOrzVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -77973912335449218L;

    @ApiModelProperty("父级采购组织ID")
    private Long pId;

    @ApiModelProperty("父级采购组织名称")
    private String pName;

    @ApiModelProperty("公司ID")
    private Long companyId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("采购组织代码")
    private String code;

    @ApiModelProperty("采购组织名称")
    private String name;

}

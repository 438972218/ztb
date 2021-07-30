package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 库存组织(InventoryOrz)表VO类
 *
 * @author makejava
 * @since 2021-07-08 11:35:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class InventoryOrzVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -22884368673065203L;

    @ApiModelProperty("父级库存组织")
    private Long pId;

    @ApiModelProperty("公司ID")
    private Long companyId;

    @ApiModelProperty("工厂/库存")
    private String type;

    @ApiModelProperty("父级名称")
    private String pName;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("库存组织代码")
    private String code;

    @ApiModelProperty("库存组织名称")
    private String name;

    @ApiModelProperty("库存组织名称")
    private List<InventoryOrzVO> children;

}

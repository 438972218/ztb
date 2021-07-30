package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 公司(Category)表VO类
 *
 * @author makejava
 * @since 2021-07-02 10:28:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class CompanyVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 479397595813259609L;

    @ApiModelProperty("父级ID")
    private Long parentId;

    @ApiModelProperty("父级ID")
    private Long pId;

    @ApiModelProperty("层级")
    private String level;

    @ApiModelProperty("公司简称")
    private String shortName;

    @ApiModelProperty("公司全称")
    private String fullName;

    @ApiModelProperty("父级名称")
    private String parentName;

    @ApiModelProperty("公司类型")
    private String type;

    @ApiModelProperty("禁用")
    private Boolean disabled;

    @ApiModelProperty("子品类列表")
    private List<CompanyVO> children;

}

package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 品类管理(Category)表VO类
 *
 * @author makejava
 * @since 2021-07-02 10:28:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class CategoryVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 479396595813259609L;

    @ApiModelProperty("父ID")
    private Long pId;

    @ApiModelProperty("品类代码")
    private String code;

    @ApiModelProperty("品类名称")
    private String name;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("子品类列表")
    private List<CategoryVO> children;

}

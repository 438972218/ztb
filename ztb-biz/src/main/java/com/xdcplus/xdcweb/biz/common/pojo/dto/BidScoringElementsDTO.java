package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 评分要素(BidScoringElements)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidScoringElementsDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 658946634013999691L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("组类型")
    private String groupType;

    @ApiModelProperty("评分要素代码")
    private String scoringElementsCode;

    @ApiModelProperty("评分要素名称")
    private String scoringElementsName;

    @ApiModelProperty("评分细则")
    private String scoringRubric;

    @ApiModelProperty("最高分")
    private String topScore;

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

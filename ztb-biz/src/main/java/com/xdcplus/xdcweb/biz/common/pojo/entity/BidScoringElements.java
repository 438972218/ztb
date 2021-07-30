package com.xdcplus.xdcweb.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 评分要素(BidScoringElements)表实体类
 *
 * @author Fish.Fei
 * @since 2021-07-26 17:08:27
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_scoring_elements")
public class BidScoringElements implements Serializable {
    private static final long serialVersionUID = -59481617470020152L;

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

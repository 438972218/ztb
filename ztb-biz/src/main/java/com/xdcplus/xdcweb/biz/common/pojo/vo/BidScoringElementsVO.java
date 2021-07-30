package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 评分要素(BidScoringElements)表VO类
 *
 * @author Fish.Fei
 * @since 2021-07-01 18:06:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidScoringElementsVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 516659620556435549L;

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

}

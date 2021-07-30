package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户品类中间表(UserCategory)表VO类
 *
 * @author makejava
 * @since 2021-07-20 11:06:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class UserCategoryVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 160414849944467793L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("品类ID")
    private Long categoryId;

    @ApiModelProperty("用户")
    private UserVO userVO;

    @ApiModelProperty("品类")
    private CategoryVO categoryVO;

}

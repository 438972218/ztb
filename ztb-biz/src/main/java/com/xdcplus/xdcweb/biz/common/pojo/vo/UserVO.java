package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户(User)表VO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class UserVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -45728239659209423L;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("账号")
    private String userName;


}

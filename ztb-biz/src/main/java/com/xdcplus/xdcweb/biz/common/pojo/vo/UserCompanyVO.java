package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户公司中间表(UserCompany)表VO类
 *
 * @author makejava
 * @since 2021-07-22 15:38:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class UserCompanyVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -97595325158926579L;

    @ApiModelProperty("用户ID")
    private Long userId;


    @ApiModelProperty("公司ID")
    private Long companyId;

    @ApiModelProperty("用户VO")
    private UserVO userVO;

    @ApiModelProperty("公司VO")
    private CompanyVO companyVO;
}

package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商现场考察人员表(VendorSiteInsUser)表VO类
 *
 * @author makejava
 * @since 2021-07-20 15:47:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorSiteInsUserVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -29780477852930013L;

    @ApiModelProperty("现场考察ID")
    private Long vendorSiteInsId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户账户")
    private String userName;

    @ApiModelProperty("用户部门")
    private String userDept;

    @ApiModelProperty("考核指标")
    private String siteInsKpi;

    @ApiModelProperty("考察组长")
    private Integer ifLeader;

}

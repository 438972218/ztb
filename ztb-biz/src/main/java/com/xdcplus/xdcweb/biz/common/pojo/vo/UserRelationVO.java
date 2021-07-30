package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 用户关联更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-22 15:38:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class UserRelationVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -10404947048412796L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("公司IDS")
    private List<Long> companyIds;

    @ApiModelProperty("品类IDS")
    private List<Long> categoryIds;

    @ApiModelProperty("采购组织IDS")
    private List<Long> purchaseOrzIds;

    @ApiModelProperty("供应商IDS")
    private List<Long> vendorIds;

    @ApiModelProperty("供应商名称数组")
    private List<String> vendorNames;

}

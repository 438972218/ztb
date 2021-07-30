package com.xdcplus.xdcweb.biz.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestionBank;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorTemplate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 模板题库中间表(VendorTemplateQbank)表VO类
 *
 * @author makejava
 * @since 2021-07-15 10:16:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorTemplateQbankVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 602067395427724828L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("模板ID")
    private Long templateId;

    @ApiModelProperty("模板")
    private VendorTemplate vendorTemplate;

    @ApiModelProperty("问题ID")
    private Long questionBankId;

    @ApiModelProperty("问题")
    private VendorQuestionBank vendorQuestionBank;

}

package com.xdcplus.xdcweb.biz.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 供应商(Vendor)表更新入参DTO类
 *
 * @author makejava
 * @since 2021-07-12 20:52:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 286636250127774736L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("状态")
    private String status;

    @NotNull(message="企业名称不能为空")
    @ApiModelProperty("企业名称")
    private String name;

    @NotNull(message="法定代表人不能为空")
    @ApiModelProperty("法定代表人")
    private String legalRepresentative;

    @NotNull(message="注册资本不能为空")
    @ApiModelProperty("注册资本")
    private String registeredCapital;

    @NotNull(message="工商注册号不能为空")
    @ApiModelProperty("工商注册号")
    private String icrn;

    @NotNull(message="企业类型不能为空")
    @ApiModelProperty("企业类型")
    private String type;

    @NotNull(message="统一社会信用代码不能为空")
    @ApiModelProperty("统一社会信用代码")
    private String uscc;

    @NotNull(message="登记状态不能为空")
    @ApiModelProperty("登记状态")
    private String registrationStatus;

    @NotNull(message="组织机构代码不能为空")
    @ApiModelProperty("组织机构代码")
    private String orzCode;

    @NotNull(message="纳税人识别号不能为空")
    @ApiModelProperty("纳税人识别号")
    private String taxpayNum;

    @NotNull(message="所属地区不能为空")
    @ApiModelProperty("所属地区")
    private String area;

    @NotNull(message="所属行业不能为空")
    @ApiModelProperty("所属行业")
    private String industry;

    @NotNull(message="注册地址不能为空")
    @ApiModelProperty("注册地址")
    private String registeredAddress;

    @NotNull(message="经营范围不能为空")
    @ApiModelProperty("经营范围")
    private String businessScope;

    @NotNull(message="联系人姓名不能为空")
    @ApiModelProperty("联系人姓名")
    private String contactName;

    @NotNull(message="联系人职位不能为空")
    @ApiModelProperty("联系人职位")
    private String contactTitle;

    @NotNull(message="联系人手机号不能为空")
    @ApiModelProperty("联系人手机号")
    private String contactMobile;

    @NotNull(message="联系人邮箱不能为空")
    @ApiModelProperty("联系人邮箱")
    private String contactEmail;

    @NotNull(message="联系人证件类型不能为空")
    @ApiModelProperty("联系人证件类型")
    private String contactIdentityType;

    @NotNull(message="联系人证件号码不能为空")
    @ApiModelProperty("联系人证件号码")
    private String contactIdentityNum;

    @NotNull(message="联系人证件正面照片不能为空")
    @ApiModelProperty("证件正面照片")
    private String identityFront;

    @NotNull(message="联系人证件反面照片不能为空")
    @ApiModelProperty("证件反面照片")
    private String identityReverse;

    @NotNull(message="商业模式不能为空")
    @ApiModelProperty("商业模式")
    private String businessModel;

    @ApiModelProperty("代理品牌")
    private String agentBrand;

    @ApiModelProperty("供货品类备注")
    private String suplyCategoryRemark;

    @NotNull(message="主营品类不能为空")
    @ApiModelProperty("主营品类")
    private String mainCategory;

    @NotNull(message="主要客户不能为空")
    @ApiModelProperty("主要客户")
    private String mainCustomer;

    @ApiModelProperty("厂房面积")
    private String factoryArea;

    @ApiModelProperty("厂房性质")
    private String factoryNature;

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

    @ApiModelProperty("证件明细")
    private List<VendorCertificateDTO> vendorCertificateDTOS;


}

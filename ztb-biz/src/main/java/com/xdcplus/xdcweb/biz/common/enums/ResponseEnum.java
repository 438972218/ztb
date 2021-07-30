package com.xdcplus.xdcweb.biz.common.enums;

/**
 * 数据信息状态枚举类
 *
 * @author Rong.Jia
 * @date 2019/4/2
 */
public enum ResponseEnum {

    /**
     * 枚举类code 开头使用规则：
     * 0: 成功；
     * -1: 失败；
     * 1：参数不正确
     * 5200：数据字典
     * 5300：组织模块
     * 5400: 单号规则模块
     */

    // 成功
    SUCCESS(0, "成功"),

    // 失败
    ERROR(-1, "失败"),

    // 1000：公共
    THE_ID_CANNOT_BE_EMPTY(1004, "id 不能为空"),
    DATA_QUOTE(1006, "数据被引用，无法执行操作"),

    //  5200：数据字典
    DATA_DICTIONARY_LIST_NULL(5201, "数据字典列表为空"),
    DATA_DICTIONARY_CATEGORY_NOT_NULL(5202, "数据字典的类别不能为空"),
    DATA_DICTIONARY_NUMERICAL_NOT_NULL(5203, "数据字典的数值不能为空"),
    DATA_DICTIONARY_MEANING_NOT_NULL(5204, "数据字典的含义不能为空"),

    // 5400：询价单
    INQUIRY_REQUEST_CREATE_FAIL(5401, "询价单创建失败"),
    INQUIRY_REQUEST_SELECT_FAIL(5402, "询价单查询失败"),
    INQUIRY_VENDOR_SELECT_FAIL(5403, "询价单供应商查询失败"),
    INQUIRY_VENDOR_MATERIAL_SELECT_FAIL(5404, "询价单供应商报价查询失败"),
    INQUIRY_VENDOR_MATERIAL_PRICE_NULL(5405, "询价单供应商报价为空"),

    // 5500：招标单
    BID_REQUEST_CREATE_FAIL(5501, "招标单创建失败"),
    BID_REQUEST_SELECT_FAIL(5502, "招标单查询失败"),
    BID_VENDOR_SELECT_FAIL(5503, "招标单供应商查询失败"),
    BID_SPECIALIST_SELECT_FAIL(5504, "招标单专家查询失败"),
    BID_SPECIALIST_GROUP_SELECT_FAIL(5504, "招标单专家组长查询失败"),

    // 5600：竞价单
    PAID_REQUEST_CREATE_FAIL(5601, "竞价单创建失败"),
    PAID_REQUEST_SELECT_FAIL(5602, "竞价单查询失败"),
    PAID_VENDOR_SELECT_FAIL(5603, "竞价单供应商查询失败"),
    PAID_MATERIAL_SELECT_FAIL(5605, "竞价单物品查询失败"),
    PAID_PRICE_ERROR(5604, "竞价价格异常"),

    // 5700：表单
    REQUEST_CREATE_FAIL(5701, "表单创建失败"),
    REQUEST_SELECT_FAIL(5702, "表单查询失败"),
    VALUE_CANNOT_BE_EMPTY(5704, "数据不能为空"),

    // 5800：现场调查
    SITELNS_SELECT_FAIL(5802, "现场调查查询失败"),


    // 6000：
    API_COMPANY_SELECT_FAIL(6001, "API获取公司为空"),
    API_JUDGE_GROUP_COMPANY_FAIL(6001, "API获取公司为空"),
    API_REQUEST_FLOWVO_FAIL(6001, "API获取表单流转失败"),
    API_ROLE_SELECT_FAIL(6004, "API查询角色失败"),
    API_USER_SELECT_FAIL(6005, "API查询用户失败"),


    ;

    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

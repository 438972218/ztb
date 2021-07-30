package com.xdcplus.gateway.common.enums;

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
     * 401： 登录相关  需跳登录
     * 404：未找到
     * 405：请求方式错误
     * 415：媒体类型不支持
     * 500: 服务调用失败
     * 1000：公共
     * 3000: 权限相关 不需跳登录
     * 5100: 用户，角色，权限资源相关
     * 5200：数据字典
     * 5300：组织模块
     * 5400: 单号规则模块
     */

    // 成功
    SUCCESS(0, "成功"),

    // 参数不正确
    PARAMETER_ERROR(1, "参数不正确"),

    // 失败
    ERROR(-1, "失败"),
    SYSTEM_ERROR(-1, "系统错误"),
    INT404_NOT_FOUND(-1, "找不到请求接口"),
    INT400_BAD_REQUEST(-1, "请求参数或方式错误"),
    FILE_WRITE_ERROR(-1, "文件写入失败"),
    FILE_LIMIT_EXCEEDED(-1, "文件超出限制, 请选择较小文件"),
    EXCEL_SPREADSHEET_GENERATION_FAILED(-1, "excel 表格生成失败"),
    ZIP_FILE_CREATE_FAILURE(-1, "压缩文件创建失败"),

    // 登录相关  需跳登录
    ACCESS_TOKEN_INVALID(401, "access_token无效"),
    REFRESH_TOKEN_INVALID(401, "refresh_token无效"),
    UNAUTHORIZED(401, "无权访问(未授权)"),
    AUTHORIZATION_EXPIRES(401, "授权过期, 请求重新登录"),
    NOT_LOGGED_IN(401, "未登录，或者授权过期"),
    ANONYMOUS_SUBJECT_UNAUTHORIZED(401, "无权访问:当前用户是匿名用户，请先登录"),
    AUTHENTICATION_FAILED(401, "身份验证未通过"),
    MISSING_TOKEN_AUTHENTICATION_FAILED(401, "缺失令牌,鉴权失败"),

    // 未找到
    NOT_FOUND(404, "请求接口不存在"),

    // 请求方式错误
    REQUEST_MODE_ERROR(405, "请求方式错误, 请检查"),

    //媒体类型不支持
    MEDIA_TYPE_NOT_SUPPORTED(415, "媒体类型不支持"),

    SERVICE_EXCEPTIONS(500, "服务异常, 请联系管理员"),
    THE_BUSINESS_SERVICE_INVOCATION_FAILED(501, "业务服务调用失败, 请重试"),


    // 1000：公共
    REQUEST_PARAMETER_FORMAT_IS_INCORRECT(1000, "请求参数格式不正确"),
    ENCRYPTION_OR_DECRYPTION_FAILED(1001, "加密/解密失败"),
    WEBSOCKET_CONNECTION_FAILED(1002, "websocket 连接失败， 请重试"),
    WEBSOCKET_CONNECTION_SUCCEEDED(SUCCESS.getCode(), "websocket 连接成功"),
    WEBSOCKET_HEART_KEEP(SUCCESS.getCode(), "heart_keep"),
    WEBSOCKET_USER_ID_NO_NULL(1003, "连接websocket 必须指定userId"),
    THE_ID_CANNOT_BE_EMPTY(1004, "id 不能为空"),
    THE_NAME_CANNOT_BE_EMPTY(1005, "名称不能为空"),
    DATA_QUOTE(1006, "数据被引用，无法执行操作"),
    TIME_IS_EMPTY(1007, "时间为空"),
    THE_PHONE_CANNOT_BE_EMPTY(1008, "联系电话不能为空"),
    THE_PHONE_ALREADY_EXISTS(1009, "联系电话已存在"),
    INVALID_SPECIFIED_STATE(1010, "指定状态无效"),
    THE_STARTING_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_CURRENT_TIME(1011, "开始时间不能小于等于当前时间"),
    THE_END_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_START_TIME(1012, "结束时间不能等于小于开始时间"),
    THE_END_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_CURRENT_TIME(1013, "结束时间不能小于等于当前时间"),
    NOT_A_DIRECTORY(1013, "不是目录"),
    FILE_MERGE_FAILED(1014, "文件合并失败"),
    SHARD_UPLOAD_FAILED(1015, "分片上传失败"),
    THE_PARAMETER_TYPE_IS_INCORRECT(1016, "参数类型不正确"),


    // 3000: 权限相关 不需跳登录
    SUBJECT_UNAUTHORIZED(3000, "无权访问:当前用户没有此请求所需权限"),
    USER_NAME_OR_PASSWORD_ERRORS_GREATER_THAN_5_TIMES(3002, "用户名或密码错误次数大于5次,账户已锁定, 请10分钟后再次访问"),
    ACCOUNT_AUTHORIZATION_EXPIRED(3003, "账号授权过期"),
    ACCOUNT_LOGIN_IS_PROHIBITED(3004, "账号禁止登陆"),
    THE_ACCOUNT_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN(3005, "账号不存在，请更换账号登录"),
    PROHIBIT_THE_LOGIN(3006, "禁止登录"),
    NO_PERMISSIONS(3007, "暂无权限， 请联系管理员"),

    // 5100: 用户，角色，权限资源相关
    USER_HAS_BEEN_AUTHORIZED(5100, "用户已经授权角色"),
    USER_INFORMATION_IS_EMPTY(5101, "用户信息为空"),
    THE_LIST_OF_USER_INFORMATION_IS_EMPTY(5102, "用户信息列表为空"),
    THE_PERMISSION_INFORMATION_IS_EMPTY(5102, "权限信息为空"),
    THE_PERMISSION_INFORMATION_LIST_IS_EMPTY(5103, "权限信息列表为空"),
    THE_LIST_OF_ROLE_IS_EMPTY(5104, "角色信息为空"),
    USER_INFO_INFO_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5105, "用户信息不存在或已被删除"),
    PERMISSION_INFO_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5106, "权限信息不存在或已被删除"),
    ACCOUNT_ALREADY_EXISTS(5107, "账号已存在"),
    INCORRECT_PASSWORD_FORMAT_OR_INCORRECT_PASSWORD_LENGTH(5108, "密码格式不正确,或者密码长度不足8位/长度超出限制"),
    SYSTEM_ADMINISTRATOR_CANNOT_DELETE(5109, "系统管理员不能删除"),
    CURRENT_USER_CANNOT_DELETE(5110, "当前用户不能删除, 该用户为当前登录用户"),
    PERMISSION_ALREADY_EXISTS(5111, "权限信息已存在"),
    ROLE_ALREADY_EXISTS(5112, "角色已存在"),
    ROLE_INFO_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5113, "角色信息不存在或已被删除"),
    ROLE_HAS_AUTHORIZED_MENU(5114, "角色已经授权菜单"),
    ACCOUNT_DOES_NOT_EXIST(5115, "账号不存在"),
    ACCOUNT_IS_EMPTY(5116, "账号为空"),
    WRONG_ACCOUNT_OR_PASSWORD(5117, "账号或密码错误"),
    ACCOUNT_AUTOMATIC_LOGOUT(5118, "账号已自动退出登录，无需再次退出登录"),
    PASSWORD_CHECK_FAILED(5119, "密码校验失败, 请检查原密码是否输入正确"),
    THE_OLD_PASSWORD_IS_THE_SAME_AS_THE_NEW_ONE(5120, "旧密码与新密码相同, 请重新输入新密码"),
    THE_VERIFICATION_CODE_IS_EMPTY(5121, "验证码为空, 请重新输入"),
    THE_VERIFICATION_CODE_IS_INCORRECT(5122, "验证码不正确 , 请重新输入"),
    VERIFICATION_CODE_GENERATION_FAILED(5123, "验证码生成失败, 请重新获取"),
    VERIFICATION_CODE_OUT_OF_DATE_PLEASE_RETRIEVE_IT_AGAIN(5124, "验证码过时, 请重新获取"),
    ACCOUNT_CANNOT_BE_CHANGED(5125, "账号不能更改"),
    CANNOT_ASSIGN_ADMINISTRATOR_USERS(5126, "不能分配管理员用户"),
    THE_LIST_OF_ROLE_TYPE_INFORMATION_IS_EMPTY(5127, "角色类型信息列表为空"),
    ROLE_TYPE_DOES_NOT_EXIST(5128, "角色类型不存在, 或者已删除"),
    THE_ACCOUNT_HAS_BEEN_ALLOCATED_AND_CANNOT_BE_RE_ALLOCATED(5129, "该账号已分配, 不能再次分配"),
    THE_LIST_OF_UNASSIGNED_ACCOUNTS_IS_EMPTY(5130, "未分配账号列表为空"),
    THE_TYPE_OF_ROLE_CANNOT_BE_MODIFIED(5131, "角色所属类型不可修改"),
    SYSTEM_ADMINISTRATOR_CANNOT_DISABLE(5132, "系统管理员不能禁用"),
    CURRENT_USER_CANNOT_DISABLE(5133, "当前用户不能禁用, 该用户为当前登录用户"),
    INVALID_SPECIFIED_USER_LEVEL(5134, "指定用户级别无效"),
    THE_ROLE_TYPE_ALREADY_EXISTS(5135, "角色类型已存在"),


    //  5200：数据字典
    DATA_DICTIONARY_LIST_NULL(5201, "数据字典列表为空"),
    DATA_DICTIONARY_CATEGORY_NOT_NULL(5202, "数据字典的类别不能为空"),
    DATA_DICTIONARY_NUMERICAL_NOT_NULL(5203, "数据字典的数值不能为空"),
    DATA_DICTIONARY_MEANING_NOT_NULL(5204, "数据字典的含义不能为空"),

    // 5300：组织模块
    ORGANIZATION_ALREADY_EXISTS(5301, "组织已存在"),
    THE_SUPERIOR_ORGANIZATION_DOES_NOT_EXIST(5302, "上级组织不存在"),
    THE_ORGANIZATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5303, "组织不存在, 或已删除"),
    THE_ORGANIZATION_INFORMATION_LIST_IS_EMPTY(5304, "组织信息列表为空"),
    THE_TISSUE_CODE_CANNOT_BE_EMPTY(5305, "组织编码不能为空"),
    ADMINISTRATIVE_CODE_CANNOT_BE_NULL(5306, "行政区划编码不能为空"),
    THE_ORGANIZATION_DOES_NOT_EXIST_IN_THE_ADMINISTRATIVE_DIVISIONS(5307, "该组织在行政区划中不存在"),
    CANNOT_CONTINUE_TO_AD_SUB_ORGANIZATIONS(5308, "不可继续新增子组织"),

    // 5400：单号规则模块
    THE_ORDER_NUMBER_RULE_ALREADY_EXISTS(5401, "单号规则已存在"),
    THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5402, "单号规则不存在, 或者已删除"),
    THE_ORDER_NUMBER_RULE_CANNOT_BE_MODIFIED(5403, "单号规则不能修改"),

    // 5500: 审批者类型模块
    THE_APPROVER_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5501, "审批者类型不存在, 或者已删除"),
    THE_APPROVER_TYPE_ALREADY_EXISTS(5502, "审批者类型已存在"),

    // 5600：流程相关模块
    THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5601, "流程信息不存在, 或者已删除"),
    THE_PROCESS_ALREADY_EXISTS(5602, "流程信息已存在"),
    THE_FLOW_OPTION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5603, "流操作不存在, 或者已删除"),
    THE_FLOW_OPTION_ALREADY_EXISTS(5604, "流操作信息已存在"),
    THE_PROCESS_STATUS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5605, "流程状态信息不存在, 或者已删除"),
    THE_PROCESS_STATUS_ALREADY_EXISTS(5606, "流程状态信息已存在"),
    THE_QUALIFIER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5607, "流程规则信息不存在, 或者已删除"),
    THE_QUALIFIER_ALREADY_EXISTS(5608, "流程规则信息已存在"),
    THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5609, "表单信息不存在, 或者已删除"),
    THE_REQUEST_ALREADY_EXISTS(5610, "表单信息已存在"),
    FORM_DUPLICATE_ASSOCIATION(5611, "表单重复关联"),
    THE_PROCESS_TO_WHICH_THE_FORM_BELONGS_DOES_NOT_CHANGE(5612, "表单所属流程不改变"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY(5613, "流程配置信息为空"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID(5614, "流程配置信息不合法"),
    PROCESS_CONFIG_DUPLICATE_ASSOCIATION(5615, "流程配置重复关联"),
    THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED(5616, "流程配置信息不存在, 或者已删除"),
    THE_FLOW_PARAMETER_CANNOT_BE_EMPTY(5617, "流转参数不能为空"),
    A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND(5618, "未找到有效的流程配置, 请先添加流程配置"),
    FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND(5619, "流转异常，找不到当前节点"),
    INCOMPLETE_FLOW_CONDITIONS(5620, "流转条件不完整"),
    THE_FLOW_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5621, "流转信息不存在, 或者已删除"),
    THERE_IS_NO_DEFAULT_FLOW(5622, "没有默认流程"),
    ABNORMAL_FLOW_SPECIFIED_RETURN_PERSON_DOES_NOT_EXIST(5623, "流转异常，指定退回人不存在"),
    THE_FLOW_HAS_ENDED_AND_CANNOT_BE_CONTINUED(5624, "流转已结束，不可继续"),
    ABNORMAL_FLOW_SIGNATURE_STATUS_DOES_NOT_EXIST(5625, "流转异常，加签状态不存在"),
    A_LIST_CAN_ONLY_BE_CANCELLED_BY_THE_CREATOR(5626, "单子只允许创建人取消"),
    PROCESS_CONFIGURATION_VERSIONS_ARE_DUPLICATED(5627, "流程配置版本重复"),


    // 5700： 表达式模块
    THE_EXPRESSION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5701, "表达式不存在, 或者已删除"),
    THE_EXPRESSION_ALREADY_EXISTS(5702, "表达式已存在"),


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

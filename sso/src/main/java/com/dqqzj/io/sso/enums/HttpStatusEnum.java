package com.dqqzj.io.sso.enums;

/**
 * Created by qzj on 2018/2/25
 */
public enum HttpStatusEnum {
    UNKNOW_ERROR(100, "未知错误"),
    CODE_ERRORT(101, "验证码校验失败"),
    SESSION_INVALID(104, "会话过期"),
    OK(200, "成功"),

    ACCOUNT_EXISTED(301, "账号已存在"),
    ACCOUNT_ERROR(302, "用户名或密码错误"),

    AUTHENTICATION_ERROR(511,"认证授权错误"),
    AUTHENTICATION_STARTING(512,"开始认证授权");
    private Integer code;

    private String message;

    HttpStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

}

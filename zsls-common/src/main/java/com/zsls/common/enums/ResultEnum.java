package com.zsls.common.enums;

public enum ResultEnum implements CodeMessageEnum{

    SUCCESS(1000, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    ERROR(-1, "未知错误");

    private Integer code;
    private String message;

    ResultEnum() {
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
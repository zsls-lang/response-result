package com.zsls.enums;

public enum APICodeEnum implements CodeMessageEnum {

    API_ERROR(110002, "api异常");


    private Integer code;
    private String message;

    private APICodeEnum() {
    }

    APICodeEnum(Integer code, String message) {
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

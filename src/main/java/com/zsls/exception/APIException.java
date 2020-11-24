package com.zsls.exception;

public class APIException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String message) {
        this(1001, message);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
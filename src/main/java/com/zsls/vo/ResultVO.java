package com.zsls.vo;

import com.zsls.enums.ResultEnum;

public class ResultVO<T> {
    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 信息描述
     */
    private String message;
    /**
     * 返回参数
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private ResultVO(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static ResultVO<Void> success() {
        return new ResultVO<Void>(ResultEnum.SUCCESS, null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>(ResultEnum.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResultVO<T> success(ResultEnum resultEnum, T data) {
        if (resultEnum == null) {
            return success(data);
        }
        return new ResultVO<T>(resultEnum, data);
    }

    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> ResultVO<T> failure() {
        return new ResultVO<T>(ResultEnum.ERROR, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResultVO<T> failure(ResultEnum resultEnum) {
        return failure(resultEnum, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResultVO<T> failure(ResultEnum resultEnum, T data) {
        if (resultEnum == null) {
            return new ResultVO<T>(resultEnum, null);
        }
        return new ResultVO<T>(resultEnum, data);
    }
}
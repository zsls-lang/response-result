package com.zsls.common.enums;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public enum ExceptionEnum implements CodeMessageEnum{
    NullPointer(-1001, "空指针异常", NullPointerException.class.getName(), "空指针异常。当应用试图在要求使用对象的地方使用了null时，抛出该异常。譬如：调用null对象的实例方法、访问null对象的属性、计算null对象的长度、使用throw语句抛出null等等。"),
    ClassCast(-1002, "强制类型转换异常", ClassCastException.class.getName(), "类造型异常。假设有类A和B（A不是B的父类或子类），O是A的实例，那么当强制将O构造为类B的实例时抛出该异常。该异常经常被称为强制类型转换异常。"),
    ArrayIndexOutOfBounds(-1003, "数组下标越界异常", ArrayIndexOutOfBoundsException.class.getName(), ""),
    Secturity(-1004, "违背安全原则异常", SecurityException.class.getName(), "安全异常。由安全管理器抛出，用于指示违反安全情况的异常。"),
    EOF(-1005, "文件已结束异常", EOFException.class.getName(), ""),
    FileNotFound(-1006, "文件未找到异常", FileNotFoundException.class.getName(), ""),
    NumberFormatException(-1007, "字符串转换为数字异常", NumberFormatException.class.getName(), "数字格式异常。当试图将一个String转换为指定的数字类型，而该字符串确不满足数字类型要求的格式时，抛出该异常。"),
    SQL(-1008, "操作数据库异常", SQLException.class.getName(), ""),
    IO(-1009, "输入输出异常", IOException.class.getName(), ""),
    NoSuchMethod(-1010, "方法不存在异常", NoSuchMethodException.class.getName(), "方法不存在异常。当访问某个类的不存在的方法时抛出该异常。"),
    StringIndexOutOfBounds(-1011, "字符串索引越界异常", StringIndexOutOfBoundsException.class.getName(), "字符串索引越界异常。当使用索引值访问某个字符串中的字符，而该索引值小于0或大于等于序列大小时，抛出该异常。"),
    Runtime(-2, "运行时异常", RuntimeException.class.getName(), "是所有Java虚拟机正常操作期间可以被抛出的异常的父类。"),
    Exception(-1, "未知的错误", Exception.class.getName(), "系统最大的exception父类");

    private Integer code;
    private String message;
    private String exceptionName;
    private String description;

    ExceptionEnum(Integer code, String message, String exceptionName, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.exceptionName = exceptionName;
    }

    @Override public Integer getCode() {
        return code;
    }

    @Override public String getMessage() {
        return message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExceptionName() {
        return this.exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public static ExceptionEnum getExceptionEnumByName(String exceptionName) {
        return Arrays.stream(values()).filter(x->x.getExceptionName().equals(exceptionName)).findAny().orElse(Exception);
    }
}
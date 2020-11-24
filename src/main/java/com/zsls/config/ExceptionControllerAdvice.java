package com.zsls.config;

import com.zsls.enums.CodeMessageEnum;
import com.zsls.enums.ExceptionEnum;
import com.zsls.enums.ResultEnum;
import com.zsls.exception.CustomException;
import com.zsls.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception ex) {
        Throwable throwable = null;
        ExceptionEnum exceptionEnum = null;
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            return ResultVO.failure(customException.getCodeMessageEnum());
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) ex;
            // 从异常对象中拿到ObjectError对象
            ObjectError objectError = argumentNotValidException.getBindingResult().getAllErrors().get(0);
            // 然后提取错误提示信息进行返回
            return ResultVO.failure(ResultEnum.VALIDATE_FAILED, objectError.getDefaultMessage());
        } else {
            if (ex instanceof UndeclaredThrowableException) {
                throwable = ex.getCause();
                exceptionEnum = this.getExceptionEnum(throwable);
                if (exceptionEnum != null) {
                    return ResultVO.failure(exceptionEnum.getErrCode(), exceptionEnum.getErrMsg());
                }
            } else {
                exceptionEnum = this.getExceptionEnum(ex);
                if (exceptionEnum != null) {
                    return ResultVO.failure(exceptionEnum.getErrCode(), exceptionEnum.getErrMsg());
                }
            }

            return ResultVO.failure(-1, "未知错误");
        }
    }

    private CodeMessageEnum getSystemException(Throwable ex) {
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            return customException.getCodeMessageEnum();
        } else {
            return null;
        }
    }

    private ExceptionEnum getExceptionEnum(Throwable ex) {
        String name = "";
        if (ex instanceof StringIndexOutOfBoundsException) {
            name = StringIndexOutOfBoundsException.class.getName();
        } else if (ex instanceof NullPointerException) {
            name = NullPointerException.class.getName();
        } else if (ex instanceof ClassCastException) {
            name = ClassCastException.class.getName();
        } else if (ex instanceof ArrayIndexOutOfBoundsException) {
            name = ArrayIndexOutOfBoundsException.class.getName();
        } else if (ex instanceof SecurityException) {
            name = SecurityException.class.getName();
        } else if (ex instanceof EOFException) {
            name = EOFException.class.getName();
        } else if (ex instanceof FileNotFoundException) {
            name = FileNotFoundException.class.getName();
        } else if (ex instanceof NumberFormatException) {
            name = NumberFormatException.class.getName();
        } else if (ex instanceof SQLException) {
            name = SQLException.class.getName();
        } else if (ex instanceof IOException) {
            name = IOException.class.getName();
        } else if (ex instanceof NoSuchMethodException) {
            name = NoSuchMethodException.class.getName();
        } else if (ex instanceof RuntimeException) {
            name = RuntimeException.class.getName();
        } else if (ex instanceof NoSuchMethodException) {
            name = Exception.class.getName();
        }

        return ExceptionEnum.getExceptionEnumByName(name);
    }

}
package com.zsls.common.config;

import com.zsls.common.enums.ExceptionEnum;
import com.zsls.common.enums.ResultEnum;
import com.zsls.common.exception.CustomException;
import com.zsls.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
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

    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception ex) {
        logger.error("记录异常",ex);
        Throwable throwable = null;
        ExceptionEnum exceptionEnum = null;
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            return ResultVO.failure(customException.getCodeMessageEnum(),ex.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) ex;
            // 从异常对象中拿到ObjectError对象
            ObjectError objectError = argumentNotValidException.getBindingResult().getAllErrors().get(0);
            // 然后提取错误提示信息进行返回
            return ResultVO.failure(ResultEnum.VALIDATE_FAILED, objectError.getDefaultMessage());
        } else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            // 从异常对象中拿到ObjectError对象
            ObjectError objectError = bindException.getBindingResult().getAllErrors().get(0);
            // 然后提取错误提示信息进行返回
            return ResultVO.failure(ResultEnum.VALIDATE_FAILED, objectError.getDefaultMessage());
        }else {
            if (ex instanceof UndeclaredThrowableException) {
                throwable = ex.getCause();
                exceptionEnum = this.getExceptionEnum(throwable);
                if (exceptionEnum != null) {
                    return ResultVO.failure(exceptionEnum, ex.getMessage());
                }
            } else {
                exceptionEnum = this.getExceptionEnum(ex);
                if (exceptionEnum != null) {
                    return ResultVO.failure(exceptionEnum, ex.getMessage());
                }
            }

            return ResultVO.failure(ExceptionEnum.Exception,ex.getMessage());
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
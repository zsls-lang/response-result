package com.zsls.framework.advice;

import com.zsls.common.enums.ExceptionEnum;
import com.zsls.common.enums.ResultEnum;
import com.zsls.common.exception.CustomException;
import com.zsls.common.vo.ResultVO;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception ex) {
        logger.error("记录异常",ex);
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
            ObjectError objectError = bindException.getBindingResult().getAllErrors().get(0);
            return ResultVO.failure(ResultEnum.VALIDATE_FAILED, objectError.getDefaultMessage());
        }else {
            String exName = ObjectUtils.isEmpty(ex.getCause()) ? ex.getClass().getName() : ex.getCause().getClass().getName();
            return ResultVO.failure(this.getExceptionEnum(exName), ex.getMessage());
        }
    }

    private ExceptionEnum getExceptionEnum(String exName) {
        return ExceptionEnum.getExceptionEnumByName(exName);
    }

}
/**
 * Company
 * Copyright (C) 2004-2020 All Rights Reserved.
 */
package com.zsls.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsls.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zsls
 * @version $Id ResponseControllerAdvice.java, v 0.1 2020-11-24 16:08  Exp $$
 * 指定扫描包下面的接口统一返回
 */
@RestControllerAdvice(basePackages = {"com.zsls.framework.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    Logger logger = LoggerFactory.getLogger(ResponseControllerAdvice.class);


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        // true 执行下面beforeBodyWrite方法的操作
        return !methodParameter.getParameterType().equals(ResultVO.class);
    }

    @Override public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
        Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
        // String类型转换错误，所以要进行些特别的处理
        if(methodParameter.getParameterType().equals(String.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(ResultVO.success(o));
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
        }

        return ResultVO.success(o);
    }
}

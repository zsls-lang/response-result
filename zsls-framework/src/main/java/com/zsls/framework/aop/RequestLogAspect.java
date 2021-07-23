package com.zsls.framework.aop;

import cn.hutool.json.JSONUtil;
import com.zsls.framework.dto.RequestErrorInfoDTO;
import com.zsls.framework.dto.RequestInfoDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class RequestLogAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut("execution(* com.zsls.framework.controller..*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = proceedingJoinPoint.proceed();
        RequestInfoDTO RequestInfoDTO = new RequestInfoDTO();
        RequestInfoDTO.setIp(request.getRemoteAddr());
        RequestInfoDTO.setUrl(request.getRequestURL().toString());
        RequestInfoDTO.setHttpMethod(request.getMethod());
        RequestInfoDTO.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName()));
        RequestInfoDTO.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
        RequestInfoDTO.setResult(result);
        RequestInfoDTO.setTimeCost(System.currentTimeMillis() - start);
        LOGGER.info("Request Info      : {}",JSONUtil.toJsonStr(RequestInfoDTO));

        return result;
    }


    @AfterThrowing(pointcut = "requestServer()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestErrorInfoDTO RequestErrorInfoDTO = new RequestErrorInfoDTO();
        RequestErrorInfoDTO.setIp(request.getRemoteAddr());
        RequestErrorInfoDTO.setUrl(request.getRequestURL().toString());
        RequestErrorInfoDTO.setHttpMethod(request.getMethod());
        RequestErrorInfoDTO.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        RequestErrorInfoDTO.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        RequestErrorInfoDTO.setException(e);
        LOGGER.info("Error Request Info      : {}", JSONUtil.toJsonStr(RequestErrorInfoDTO));
    }

    /**
     * 获取入参
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }
}
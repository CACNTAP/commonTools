package com.tools.log.aop;


import com.alibaba.fastjson.JSONObject;
import com.tools.log.RecordLogService;
import com.tools.log.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
@Order(1)
public class LogProcessor {

    @Resource
    RecordLogService recordLogService;

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("@annotation(annot)")
    public Object around(ProceedingJoinPoint joinPoint, Log annot) {

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        recordLogService.recordLog(JSONObject.toJSONString(result));
        return result;

    }
}

package com.tools.cache.aop;

import com.tools.cache.CacheService;
import com.tools.cache.annotation.UseEncache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class CacheProcessor {


    @Resource
    CacheService cacheService;

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("@annotation(annot)")
    public Object around(ProceedingJoinPoint joinPoint, UseEncache annot) {
        String key = generateKey(joinPoint);
        try {
            Object value = cacheService.getFromCache(key);
            System.out.println(key);
            System.out.println("get "+ value);
            if (value == null){
                Object result =  joinPoint.proceed();
                cacheService.putIntoCache(key, result);
                return result;
            }
            return value;

        } catch (Throwable e) {
            return null;
        }
    }


    private String generateKey(ProceedingJoinPoint joinPoint){

        StringBuilder key = new StringBuilder();

        String methodName = joinPoint.getSignature().getName();
        key.append(methodName);
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects){
            key.append(String.valueOf(obj));
        }
        return key.toString();
    }

}

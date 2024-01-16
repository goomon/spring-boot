package com.github.goomon.boot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect1 {

    @Around("execution(* com.github.goomon.boot..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[Aspect1] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}

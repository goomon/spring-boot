package com.github.goomon.boot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect3 {

    @Around("com.github.goomon.boot.aspect.pointcut.Pointcuts.allAndServiceOnly()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[Aspect3] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}

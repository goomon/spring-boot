package com.github.goomon.boot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect2 {

    // pointcut signature
    @Pointcut("execution(* com.github.goomon.boot..*(..))")
    private void all() {

    }

    @Around("all()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[Aspect1] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}

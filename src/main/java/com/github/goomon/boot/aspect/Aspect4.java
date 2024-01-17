package com.github.goomon.boot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class Aspect4 {

    @Around("com.github.goomon.boot.aspect.pointcut.Pointcuts.allAndServiceOnly()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[Aspect4] try {}", joinPoint.getSignature());
            return joinPoint.proceed();
        } catch (Exception e) {
            log.info("[Aspect4] catch {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[Aspect4] finally {}", joinPoint.getSignature());
        }
    }

    @Before("com.github.goomon.boot.aspect.pointcut.Pointcuts.allAndServiceOnly()")
    public void before(JoinPoint joinPoint) {
        log.info("[Aspect4] before {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.github.goomon.boot.aspect.pointcut.Pointcuts.allAndServiceOnly()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("[Aspect4] afterReturning {} | result {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.github.goomon.boot.aspect.pointcut.Pointcuts.allAndServiceOnly()",
            throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[Aspect4] afterThrowing {} | exception {}", joinPoint.getSignature(), ex);
    }

    @After("com.github.goomon.boot.aspect.pointcut.Pointcuts.allAndServiceOnly()")
    public void after(JoinPoint joinPoint) {
        log.info("[Aspect4] after {}", joinPoint.getSignature());
    }
}

package com.github.goomon.boot.trace.app.pointcut.designator;

import com.github.goomon.boot.app.SimpleService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    SimpleService service;

    @Test
    void success() {
        log.info("repository: {}", service.getClass());
        service.save("hihi");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* com.github.goomon.boot.app..*.*(..))")
        private void all() {}

        @Around("all() && args(arg, ..)")
        public Object around(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("args: {}", joinPoint.getArgs());
            log.info("args from arguments: {}", arg);
            return joinPoint.proceed();
        }

        @Around("all() && args(arg, ..)")
        public Object around2(ProceedingJoinPoint jointPoint, String arg) throws Throwable {
            log.info("args: {}", jointPoint.getArgs());
            log.info("args from arguments with static type: {}", arg);
            return jointPoint.proceed();
        }

        // 스프링 빈에 등록된 실제 프록시 객체를 obj에 넣어준다.
        @Around("all() && this(obj)")
        public Object around3(ProceedingJoinPoint proceedingJoinPoint, SimpleService obj) throws Throwable {
            log.info("this: {}", obj.getClass());
            return proceedingJoinPoint.proceed();
        }

        // 프록시 객체가 아닌 타겟 객체를 obj에 넣어준다.
        @Around("all() && target(obj)")
        public Object around4(ProceedingJoinPoint proceedingJoinPoint, SimpleService obj) throws Throwable {
            log.info("target: {}", obj.getClass());
            return proceedingJoinPoint.proceed();
        }
    }
}

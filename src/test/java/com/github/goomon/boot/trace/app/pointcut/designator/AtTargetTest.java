package com.github.goomon.boot.trace.app.pointcut.designator;

import com.github.goomon.boot.aspect.annotation.ClassTarget;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Import({AtTargetTest.Config.class})
public class AtTargetTest {

    @Autowired
    Parent parent;

    @Autowired
    Child child;

    @DisplayName("@target은 범위에 해당하는 모든 타겟을 프록시로 만든다.")
    @Test
    void proxyTest() {
        assertThat(AopUtils.isAopProxy(parent)).isTrue();
        assertThat(AopUtils.isAopProxy(child)).isTrue();
    }

    @DisplayName("@target은 애노테이션 기반으로 AOP를 적용한다.")
    @Test
    void atTargetExactMatchForChild() {
        log.info("child: {}", child.getClass());
        child.saveForChild();
    }

    @DisplayName("@target은 부모 메서드에 대해서도 경우도 AOP가 적용된다.")
    @Test
    void atWithExactMatchForParent() {
        log.info("parent: {}", child.getClass());
        child.saveForParent();
    }

    static class Config {

        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public AtTargetAspect atWithinAspect() {
            return new AtTargetAspect();
        }
    }

    @Slf4j
    @Aspect
    static class AtTargetAspect {
        @Around("execution(* com.github.goomon.boot.trace.app..*(..)) && @target(com.github.goomon.boot.aspect.annotation.ClassTarget))")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Slf4j
    static class Parent {
        public void saveForParent() {
            log.info("[parent] saveForParent()");
        }
    }

    @Slf4j
    @ClassTarget("class target")
    static class Child extends Parent {
        public void saveForChild() {
            log.info("[child] saveForChild");
        }
    }
}

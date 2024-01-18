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
@Import({AtWithinTest.Config.class})
public class AtWithinTest {

    @Autowired
    Parent parent;

    @Autowired
    Child child;

    @DisplayName("@within의 경우 해당하는 타입에 대해서만 프록시가 생성된다.")
    @Test
    void proxyTest() {
        assertThat(AopUtils.isAopProxy(parent)).isFalse();
        assertThat(AopUtils.isAopProxy(child)).isTrue();
    }

    @DisplayName("@within은 애노테이션 기반으로 AOP를 적용한다.")
    @Test
    void atWithinExactMatchForChild() {
        log.info("child: {}", child.getClass());
        child.saveForChild();
    }

    @DisplayName("@within은 부모 메서드의 경우 AOP가 적용되지 않는다.")
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
        public AtWithinAspect atWithinAspect() {
            return new AtWithinAspect();
        }
    }

    @Slf4j
    @Aspect
    static class AtWithinAspect {
        @Around("@within(com.github.goomon.boot.aspect.annotation.ClassTarget))")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
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

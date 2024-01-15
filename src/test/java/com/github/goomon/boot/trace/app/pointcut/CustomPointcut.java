package com.github.goomon.boot.trace.app.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

@Slf4j
public class CustomPointcut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return new CustomClassFilter();
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new CustomMethodMatcher();
    }

    static class CustomClassFilter implements ClassFilter {
        @Override
        public boolean matches(Class<?> clazz) {
            return true;
        }
    }

    static class CustomMethodMatcher implements MethodMatcher {

        private String matchName = "testMethod";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            log.info("method.getName() = {}", method.getName());
            return method.getName().equals(matchName);
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}

package com.github.goomon.boot.trace.app.pointcut.designator;

import com.github.goomon.boot.app.SimpleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    void init() throws NoSuchMethodException {
        method = SimpleRepository.class.getMethod("save", String.class);
    }

    @Test
    void printMethod() {
        // public void com.github.goomon.boot.app.SimpleRepository.save(java.lang.String)
        log.info("method: {}", method);
    }

    @DisplayName("정확한 표현식")
    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot.app.SimpleRepository.save(String))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @DisplayName("모든 대상과 매칭")
    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @DisplayName("패키지 정확한 표현식")
    @Test
    void packageExactMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot.app.*.*(..))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @DisplayName("패키지 정확한 표현식 실패")
    @Test
    void packageExactMatchFail() {
        pointcut.setExpression("execution(public void com.github.goomon.boot.*.*(..))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isFalse();
    }

    @DisplayName("하위 패키지까지 포괄하는 표현식")
    @Test
    void subPackageExactMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot..*.*(..))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot.app.SimpleRepository.*(..))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @Test
    void argsMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot..*.*(String))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @Test
    void noArgsMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot..*.*())");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isFalse();
    }

    @Test
    void oneArgsMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot..*.*(*))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @Test
    void allArgsMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot..*.*(..))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @Test
    void fixedArgsMatch() {
        pointcut.setExpression("execution(public void com.github.goomon.boot..*.*(String, ..))");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }
}

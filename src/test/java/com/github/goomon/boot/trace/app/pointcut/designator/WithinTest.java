package com.github.goomon.boot.trace.app.pointcut.designator;

import com.github.goomon.boot.app.SimpleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    void init() throws NoSuchMethodException {
        method = SimpleRepository.class.getMethod("save", String.class);
    }

    @DisplayName("정확한 표현식")
    @Test
    void withinExactMatch() {
        pointcut.setExpression("within(com.github.goomon.boot.app.SimpleRepository)");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @DisplayName("*를 포함한 정확한 표현식")
    @Test
    void withinExactMatchWithAsterisk() {
        pointcut.setExpression("within(com.github.goomon.boot.app.*Repository)");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isTrue();
    }

    @DisplayName("인터페이스 타입 매칭은 실패한다.")
    @Test
    void withinExactMatchForInterface() {
        pointcut.setExpression("within(com.github.goomon.boot.app.SimpleRepositoryInterface)");
        assertThat(pointcut.matches(method, SimpleRepository.class)).isFalse();
    }
}

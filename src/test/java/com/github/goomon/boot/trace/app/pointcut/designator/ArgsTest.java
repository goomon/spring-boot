package com.github.goomon.boot.trace.app.pointcut.designator;

import com.github.goomon.boot.app.SimpleRepository;
import org.junit.jupiter.api.*;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArgsTest {

    Method method;

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @BeforeEach
    void init() throws NoSuchMethodException {
        method = SimpleRepository.class.getMethod("save", String.class);
    }

    /**
     * execution(* *(java.io.Serializable)): 메서드 시그니처로 판단 (정적)
     * args(java.io.Serializable): 런타임에 전달된 인수로 판단 (동적)
     */
    @TestFactory
    List<DynamicTest> argsMatch() {
        return List.of(
                DynamicTest.dynamicTest("정확한 인자 타입", () -> {
                    assertThat(pointcut("args(String)")
                            .matches(method, SimpleRepository.class)).isTrue();
                }),
                DynamicTest.dynamicTest("상위 타입 인자 표현식1", () -> {
                    assertThat(pointcut("args(Object)")
                            .matches(method, SimpleRepository.class)).isTrue();
                }),
                DynamicTest.dynamicTest("상위 타입 인자 표현식2", () -> {
                    assertThat(pointcut("args(java.io.Serializable)")
                            .matches(method, SimpleRepository.class)).isTrue();
                })
        );
    }
}

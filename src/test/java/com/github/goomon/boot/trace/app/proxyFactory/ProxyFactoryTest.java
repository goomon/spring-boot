package com.github.goomon.boot.trace.app.proxyFactory;

import com.github.goomon.boot.trace.app.advice.LogAdvice;
import com.github.goomon.boot.trace.app.advice.TimeAdvice;
import com.github.goomon.boot.trace.app.target.ConcreteTargetService;
import com.github.goomon.boot.trace.app.target.TargetService;
import com.github.goomon.boot.trace.app.target.TargetServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class ProxyFactoryTest {

    @DisplayName("인터페이스 기반 ProxyFactory 기본 테스트")
    @Test
    void interfaceBasedProxyTest() {
        // given
        ProxyFactory proxyFactory = new ProxyFactory(new TargetServiceImpl());
        proxyFactory.addAdvice(new LogAdvice());
        proxyFactory.addAdvice(new TimeAdvice());

        // when
        TargetService service = (TargetService) proxyFactory.getProxy();
        service.testMethod();

        // then
        assertThat(AopUtils.isAopProxy(service)).isTrue();
        assertThat(AopUtils.isCglibProxy(service)).isFalse();
    }

    @DisplayName("클래스 기반 ProxyFactory 기본 테스트")
    @Test
    void classBasedProxyFactoryTest() {
        // given
        ProxyFactory proxyFactory = new ProxyFactory(new ConcreteTargetService());
        proxyFactory.addAdvice(new LogAdvice());
        proxyFactory.addAdvice(new TimeAdvice());

        // when
        ConcreteTargetService service = (ConcreteTargetService) proxyFactory.getProxy();
        service.testMethod();

        // then
        assertThat(AopUtils.isAopProxy(service)).isTrue();
        assertThat(AopUtils.isCglibProxy(service)).isTrue();
    }

    @DisplayName("인터페이스 기반 ProxyFactory 타겟 클래스 세팅")
    @Test
    void interfaceBasedProxyFactorySetTargetClassTest() {
        // given
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TargetServiceImpl());
        proxyFactory.addAdvice(new LogAdvice());
        proxyFactory.addAdvice(new TimeAdvice());
        proxyFactory.setProxyTargetClass(true);

        // when
        TargetService service = (TargetService) proxyFactory.getProxy();
        service.testMethod();

        // then
        assertThat(AopUtils.isAopProxy(service)).isTrue();
        assertThat(AopUtils.isCglibProxy(service)).isTrue();
    }
}

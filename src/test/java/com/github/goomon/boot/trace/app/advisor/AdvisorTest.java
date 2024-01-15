package com.github.goomon.boot.trace.app.advisor;

import com.github.goomon.boot.trace.app.advice.TimeAdvice;
import com.github.goomon.boot.trace.app.pointcut.CustomPointcut;
import com.github.goomon.boot.trace.app.target.TargetService;
import com.github.goomon.boot.trace.app.target.TargetServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class AdvisorTest {

    @Test
    void advisorTest1() {
        // given
        ProxyFactory proxyFactory = new ProxyFactory(new TargetServiceImpl());
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);

        // when
        TargetService service = (TargetService) proxyFactory.getProxy();
        service.testMethod();
    }

    @Test
    void advisorWithCustomPointcutTest() {
        // given
        ProxyFactory proxyFactory = new ProxyFactory(new TargetServiceImpl());
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new CustomPointcut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);

        // when
        TargetService service = (TargetService) proxyFactory.getProxy();
        service.testMethod();
    }

    @Test
    void advisorWithNameMatchMethodPointcutTest() {
        // given
        ProxyFactory proxyFactory = new ProxyFactory(new TargetServiceImpl());
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("testMethod");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);

        // when
        TargetService service = (TargetService) proxyFactory.getProxy();
        service.testMethod();
    }
}

package com.github.goomon.boot.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;

public class PostProcessorTest {

    @Test
    void contextLoadBasicTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        assertThatNoException().isThrownBy(() -> applicationContext.getBean("a", A.class));
        assertThatThrownBy(() -> applicationContext.getBean("b", B.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Test
    void beanPostProcessorTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigWithBeanPostProcessor.class);

        assertThatThrownBy(() -> applicationContext.getBean("b", B.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);

        B a = (B) applicationContext.getBean("a");
        a.hello();
    }

    @Configuration
    static class Config {
        @Bean
        public A a() {
            return new A();
        }
    }

    @Configuration
    static class ConfigWithBeanPostProcessor {
        @Bean
        public A a() {
            return new A();
        }

        @Bean
        public BeanPostProcessor customBeanPostProcessor() {
            return new CustomBeanPostProcessor();
        }
    }

    @Slf4j
    static class A {
        public void hello() {
            log.info("A.a()");
        }
    }

    @Slf4j
    static class B {
        public void hello() {
            log.info("B.b()");
        }
    }

    @Slf4j
    static class CustomBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof A && beanName.equals("a")) {
                log.info("A bean found");
                return new B();
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("bean={} beanName = {}", bean, beanName);
            return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }
    }
}

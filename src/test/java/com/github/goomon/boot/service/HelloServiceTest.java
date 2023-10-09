package com.github.goomon.boot.service;

import com.github.goomon.boot.annotation.UnitTest;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {
    @UnitTest
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }

    @UnitTest
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Test");

        assertThat(ret).isEqualTo("* Test *");
    }
}

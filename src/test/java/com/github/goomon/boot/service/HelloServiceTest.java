package com.github.goomon.boot.service;

import com.github.goomon.boot.annotation.UnitTest;
import com.github.goomon.boot.model.Hello;
import com.github.goomon.boot.repository.HelloRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {
    @UnitTest
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService(new HelloRepository() {
            @Override
            public Hello findHello(String name) {
                return null;
            }

            @Override
            public void increaseCount(String name) {

            }
        });

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

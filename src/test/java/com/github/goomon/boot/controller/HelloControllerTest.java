package com.github.goomon.boot.controller;

import com.github.goomon.boot.annotation.UnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HelloControllerTest {
    @UnitTest
    void helloController() {
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        assertThat(ret).isEqualTo("Test");
    }

    @UnitTest
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

        assertThatThrownBy(() -> helloController.hello(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> helloController.hello("")).isInstanceOf(IllegalArgumentException.class);
    }
}

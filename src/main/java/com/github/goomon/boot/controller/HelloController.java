package com.github.goomon.boot.controller;

import com.github.goomon.boot.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();

        return helloService.sayHello(name);
    }
}

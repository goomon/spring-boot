package com.github.goomon.boot.trace.app.target;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteTargetService {
    public String testMethod() {
        log.info("ConcreteTargetService.testMethod()");
        return "test";
    }
}

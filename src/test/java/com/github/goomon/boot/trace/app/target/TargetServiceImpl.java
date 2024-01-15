package com.github.goomon.boot.trace.app.target;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TargetServiceImpl implements TargetService {

    @Override
    public String testMethod() {
        log.info("TargetServiceImpl.testMethod()");
        return "test";
    }
}

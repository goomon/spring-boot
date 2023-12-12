package com.github.goomon.boot.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleControllerV0 {

    private final SimpleServiceV0 service;

    @RequestMapping("/v0/request")
    public String request(String id) {
        service.save(id);
        return "ok";
    }
}

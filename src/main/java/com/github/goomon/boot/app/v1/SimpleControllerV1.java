package com.github.goomon.boot.app.v1;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracerV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleControllerV1 {

    private final SimpleServiceV1 service;
    private final LogTracerV1 tracer;

    @RequestMapping("/v1/request")
    public String request(String id) {
        TraceStatus status = tracer.begin("SimpleControllerV1.request()");
        try {
            service.save(id);
            tracer.end(status);
            return "ok";
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }
}

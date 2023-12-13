package com.github.goomon.boot.app.v2;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracerV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleControllerV2 {

    private final SimpleServiceV2 service;
    private final LogTracerV1 tracer;

    @RequestMapping("/v2/request")
    public String request(String id) {
        TraceStatus status = tracer.begin("SimpleControllerV2.request()");
        try {
            service.save(id, status.getTraceId());
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
        }
        return "ok";
    }
}

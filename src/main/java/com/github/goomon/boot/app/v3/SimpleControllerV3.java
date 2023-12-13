package com.github.goomon.boot.app.v3;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracer;
import com.github.goomon.boot.trace.app.LogTracerV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleControllerV3 {

    private final SimpleServiceV3 service;
    private final LogTracer tracer;

    @RequestMapping("/v3/request")
    public String request(String id) {
        TraceStatus status = tracer.begin("SimpleControllerV3.request()");
        try {
            service.save(id, status.getTraceId());
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
        }
        return "ok";
    }
}

package com.github.goomon.boot.app.v4;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracer;
import com.github.goomon.boot.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleControllerV4 {

    private final SimpleServiceV4 service;
    private final LogTracer tracer;

    @RequestMapping("/v4/request")
    public String request(String id) {
        AbstractTemplate<String> tempalte = new AbstractTemplate<String>(tracer) {
            @Override
            protected String call() {
                service.save(id);
                return "ok";
            }
        };
        return tempalte.execute("SimpleControllerV4.request()");
    }
}

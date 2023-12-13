package com.github.goomon.boot.app.v3;

import com.github.goomon.boot.trace.TraceId;
import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracer;
import com.github.goomon.boot.trace.app.LogTracerV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleServiceV3 {

    private final SimpleRepositoryV3 repository;
    private final LogTracer tracer;

    public void save(String id, TraceId prev) {
        TraceStatus status = tracer.begin("SimpleServiceV3.save()");
        try {
            repository.save(id);
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }
}

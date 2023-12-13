package com.github.goomon.boot.app.v2;

import com.github.goomon.boot.trace.TraceId;
import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracerV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleServiceV2 {

    private final SimpleRepositoryV2 repository;
    private final LogTracerV2 tracer;

    public void save(String id, TraceId prev) {
        TraceStatus status = tracer.beginSync(prev, "SimpleServiceV2.save()");
        try {
            repository.save(id, status.getTraceId());
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }
}

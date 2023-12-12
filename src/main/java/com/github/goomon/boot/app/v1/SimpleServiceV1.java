package com.github.goomon.boot.app.v1;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracerV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleServiceV1 {

    private final SimpleRepositoryV1 repository;
    private final LogTracerV1 tracer;

    public void save(String id) {
        TraceStatus status = tracer.begin("SimpleServiceV1.save()");
        try {
            repository.save(id);
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }

    }
}

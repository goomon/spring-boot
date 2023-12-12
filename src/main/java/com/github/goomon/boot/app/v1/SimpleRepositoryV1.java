package com.github.goomon.boot.app.v1;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracerV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimpleRepositoryV1 {

    private final LogTracerV1 tracer;

    public void save(String id) {

        TraceStatus status = tracer.begin("SimpleRepositoryV1.save()");
        try {
            if (id.equals("ex")) {
                throw new IllegalArgumentException();
            }
            sleep(1000);
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

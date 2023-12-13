package com.github.goomon.boot.app.v2;

import com.github.goomon.boot.trace.TraceId;
import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracerV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimpleRepositoryV2 {

    private final LogTracerV2 tracer;

    public void save(String id, TraceId prev) {

        TraceStatus status = tracer.beginSync(prev, "SimpleRepositoryV2.save()");
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

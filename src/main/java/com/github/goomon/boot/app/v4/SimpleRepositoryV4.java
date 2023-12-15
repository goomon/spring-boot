package com.github.goomon.boot.app.v4;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracer;
import com.github.goomon.boot.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimpleRepositoryV4 {

    private final LogTracer tracer;

    public void save(String id) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(tracer) {
            @Override
            protected Void call() {
                if (id.equals("ex")) {
                    throw new IllegalArgumentException();
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("SimpleRepositoryV4.save()");
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

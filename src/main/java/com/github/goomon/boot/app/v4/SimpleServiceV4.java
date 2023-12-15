package com.github.goomon.boot.app.v4;

import com.github.goomon.boot.trace.app.LogTracer;
import com.github.goomon.boot.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleServiceV4 {

    private final SimpleRepositoryV4 repository;
    private final LogTracer tracer;

    public void save(String id) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(tracer) {
            @Override
            protected Void call() {
                repository.save(id);
                return null;
            }
        };
        template.execute("SimpleServiceV4.save()");
    }
}

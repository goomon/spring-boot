package com.github.goomon.boot.trace.template;

import com.github.goomon.boot.trace.TraceStatus;
import com.github.goomon.boot.trace.app.LogTracer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTracer tracer;

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = tracer.begin(message);

            T result = call();

            tracer.end(status);
            return result;
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}

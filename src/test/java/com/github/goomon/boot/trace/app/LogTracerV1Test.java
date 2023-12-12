package com.github.goomon.boot.trace.app;

import com.github.goomon.boot.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class LogTracerV1Test {

    @Test
    void begin_end() {
        LogTracerV1 tracer = new LogTracerV1();
        TraceStatus status = tracer.begin("test");
        tracer.end(status);
    }

    @Test
    void begin_exception() {
        LogTracerV1 tracer = new LogTracerV1();
        TraceStatus status = tracer.begin("test");
        tracer.exception(status, new IllegalArgumentException("test"));
    }
}

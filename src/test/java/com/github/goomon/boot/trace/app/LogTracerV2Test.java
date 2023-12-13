package com.github.goomon.boot.trace.app;

import com.github.goomon.boot.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class LogTracerV2Test {

    @Test
    void begin_end() {
        LogTracerV2 tracer = new LogTracerV2();
        TraceStatus status1 = tracer.begin("test1");
        TraceStatus status2 = tracer.beginSync(status1.getTraceId(), "test2");
        tracer.end(status2);
        tracer.end(status1);
    }

    @Test
    void begin_exception() {
        LogTracerV2 tracer = new LogTracerV2();
        TraceStatus status1 = tracer.begin("test1");
        TraceStatus status2 = tracer.beginSync(status1.getTraceId(), "test2");
        tracer.exception(status2, new IllegalArgumentException("test2"));
        tracer.exception(status1, new IllegalArgumentException("test1"));
    }
}

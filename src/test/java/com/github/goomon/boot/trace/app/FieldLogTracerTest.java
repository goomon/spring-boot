package com.github.goomon.boot.trace.app;

import com.github.goomon.boot.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class FieldLogTracerTest {

    FieldLogTracer tracer = new FieldLogTracer();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = tracer.begin("test1");
        TraceStatus status2 = tracer.begin("test2");
        tracer.end(status2);
        tracer.end(status1);
    }

    @Test
    void begin_exception_level2() {
        TraceStatus status1 = tracer.begin("test1");
        TraceStatus status2 = tracer.begin("test2");

        IllegalArgumentException ex = new IllegalArgumentException("ex");

        tracer.exception(status2, ex);
        tracer.exception(status1, ex);
    }
}

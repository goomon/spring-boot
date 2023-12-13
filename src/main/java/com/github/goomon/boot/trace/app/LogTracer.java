package com.github.goomon.boot.trace.app;

import com.github.goomon.boot.trace.TraceStatus;

public interface LogTracer {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception ex);
}

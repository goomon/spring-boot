package com.github.goomon.boot;

import com.github.goomon.boot.trace.app.FieldLogTracer;
import com.github.goomon.boot.trace.app.LogTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTracerConfig {

    @Bean
    public LogTracer logTracer() {
        return new FieldLogTracer();
    }
}

package com.github.goomon.boot.availability;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.context.event.*;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationEventListener {

    private final GenericApplicationContext context;

    public MyApplicationEventListener(GenericApplicationContext context) {
        this.context = context;
    }

    @EventListener
    void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            System.out.println("[ApplicationStartingEvent] Application starts.");
        } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            System.out.println("[ApplicationEnvironmentPreparedEvent] Application Environment prepared.");
        } else if (event instanceof ApplicationContextInitializedEvent) {
            System.out.println("[ApplicationContextInitializedEvent] ApplicationContext is created and ApplicationContextInitializer is called.");
        } else if (event instanceof ApplicationPreparedEvent) {
            System.out.println("[ApplicationPreparedEvent] Bean definition is loaded, but refresh method is not called yet.");
        } else if (event instanceof ApplicationStartedEvent) {
            System.out.println("[ApplicationStartedEvent] Refresh is called, application runner will be started.");
        } else if (event instanceof AvailabilityChangeEvent) {
            System.out.println("[AvailabilityChangeEvent] Availability status is changed: " + ((AvailabilityChangeEvent<?>) event).getState());
        } else if (event instanceof ApplicationReadyEvent) {
            System.out.println("[ApplicationReadyEvent] Application runner is already called.");
        } else if (event instanceof ApplicationFailedEvent) {
            System.out.println("[ApplicationFailedEvent] Application is failed, please restart.");
        } else if (event instanceof ContextRefreshedEvent) {
            System.out.println("[ContextRefreshedEvent] ApplicationContext is refreshed.");
        } else if (event instanceof ContextClosedEvent) {
            System.out.println("[ContextClosedEvent] Context will be closed.");
        }
    }
}

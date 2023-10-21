package com.github.goomon.boot.availability;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public MyEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishReadiness(ReadinessState state) {
        AvailabilityChangeEvent.publish(eventPublisher, new RuntimeException(), state);
    }
}

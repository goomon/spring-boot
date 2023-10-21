package com.github.goomon.boot.availability;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyAvailabilityStateExporter {
    @EventListener
    void onStateReadinessChange(AvailabilityChangeEvent<ReadinessState> event) {
        switch (event.getState()) {
            case ACCEPTING_TRAFFIC:
                System.out.println("ACCEPTING_TRAFFIC");
                break;
            case REFUSING_TRAFFIC:
                System.out.println("REFUSING_TRAFFIC");
                break;
        }
    }

    @EventListener
    void onStateLivenessChange(AvailabilityChangeEvent<LivenessState> event) {
        switch (event.getState()) {
            case CORRECT:
                System.out.println("CORRECT");
                break;
            case BROKEN:
                System.out.println("BROKEN");
                break;
        }
    }
}

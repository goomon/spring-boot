package com.github.goomon.boot;

import com.github.goomon.boot.availability.MyEventPublisher;
import com.github.goomon.boot.availability.MyAvailabilityStateExporter;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationAvailabilityTest {

    @Autowired ApplicationAvailability applicationAvailability;

    @Test
    void applicationAvailabilityTest() {
        LivenessState livenessState = applicationAvailability.getLivenessState();
        ReadinessState readinessState = applicationAvailability.getReadinessState();

        assertThat(livenessState).isEqualTo(LivenessState.CORRECT);
        assertThat(readinessState).isEqualTo(ReadinessState.ACCEPTING_TRAFFIC);
    }


    @Autowired
    MyAvailabilityStateExporter readinessStateExporter;
    @Autowired MyEventPublisher publisher;

    @Description("If publisher calls publish method, MyAvailabilityStateExporter should listen changes and print out the changes.")
    @Test
    void managingApplicationAvailabilityTest() {
        publisher.publishReadiness(ReadinessState.ACCEPTING_TRAFFIC); // print out ACCEPTING_TRAFFIC
        publisher.publishReadiness(ReadinessState.REFUSING_TRAFFIC); // print out REFUSING_TRAFFIC
    }
}

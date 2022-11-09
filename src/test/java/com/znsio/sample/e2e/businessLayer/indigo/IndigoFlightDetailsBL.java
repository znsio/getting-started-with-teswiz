package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.indigo.IndigoFlightDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class IndigoFlightDetailsBL {

    private static final Logger LOGGER = Logger.getLogger(IndigoFlightDetailsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public IndigoFlightDetailsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public IndigoFlightDetailsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }


    public IndigoFlightDetailsBL isFlightDetailsAvailable() {
        String expectedFlightDetails = "Delhi to Mumbai";
        String validateFlightDetails = IndigoFlightDetailsScreen
                                        .get()
                                        .isFlightDetailsVisible();
        assertThat(validateFlightDetails).as("Validating Flight Details")
                .isEqualTo(expectedFlightDetails);
        return this;
    }

    public IndigoFlightDetailsBL applyFilters() {
        int flightCount =  IndigoFlightDetailsScreen
                            .get()
                            .applyTimeFilter()
                            .getFlightCounts();
        assertThat(flightCount).isGreaterThan(0);
        return this;
    }
}

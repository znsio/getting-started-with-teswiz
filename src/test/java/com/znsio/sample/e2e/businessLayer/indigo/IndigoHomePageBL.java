package com.znsio.sample.e2e.businessLayer.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import com.znsio.sample.e2e.screen.indigo.IndigoHomePageScreen;


import static org.assertj.core.api.Assertions.assertThat;

public class IndigoHomePageBL {
    private static final Logger LOGGER = Logger.getLogger(IndigoHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public IndigoHomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public IndigoHomePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }


    public IndigoHomePageBL isLandingPageOpened() {
           boolean validatingLandingPage = IndigoHomePageScreen.get()
                                              .validateHomePage();
        assertThat(validatingLandingPage).isTrue();
        return this;
    }

    public IndigoFlightDetailsBL addArrivalDepartureDetails() {
        boolean isFlightDetailsVisible = IndigoHomePageScreen.get()
                                        .addCurrentLocationDetails()
                                        .addDestinationDetails()
                                        .addDateDetails()
                                        .validateFlightDetails();
        assertThat(isFlightDetailsVisible).isTrue();
        return new IndigoFlightDetailsBL();
        //need to discuss
    }
}

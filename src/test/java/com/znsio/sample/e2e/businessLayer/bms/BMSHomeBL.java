package com.znsio.sample.e2e.businessLayer.bms;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bms.BMSHomeScreen;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Runner;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class BMSHomeBL {

    private static final Logger LOGGER = Logger.getLogger(BMSHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public BMSHomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public BMSHomeBL loginAsGuestUserForGivenLocation(String location) {
        BMSHomeScreen.get().searchForLocation(location);
        return this;
    }

    public BMSMoviesBL selectMovieCategory() {
        BMSHomeScreen.get().selectMoviesCategory();
        return new BMSMoviesBL();
    }
}

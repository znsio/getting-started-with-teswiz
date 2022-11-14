package com.znsio.sample.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomePageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ZomatoHomePageBL {
    private static final Logger LOGGER = Logger.getLogger(ZomatoHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ZomatoHomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ZomatoHomePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ZomatoHomePageBL validateHomepage() {
        boolean isZomatoHomepageDisplayed = ZomatoHomePageScreen.get()
                .isHomepageVisible();
        softly.assertThat(isZomatoHomepageDisplayed).isTrue();
        return this;
    }

    public ZomatoCityPageBL selectLocation(String location) {
        boolean isLocationSelected = ZomatoHomePageScreen.get().selectLocationFromDropDown(location);
        softly.assertThat(isLocationSelected).isTrue();
        return new ZomatoCityPageBL();
    }
}

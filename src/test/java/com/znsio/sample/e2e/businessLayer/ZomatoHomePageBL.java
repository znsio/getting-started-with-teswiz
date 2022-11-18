package com.znsio.sample.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomePageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ZomatoHomePageBL {
    private static final Logger LOGGER = Logger.getLogger(ZomatoHomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public ZomatoHomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ZomatoHomePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public ZomatoCityPageBL selectLocation(String location) {
        boolean isLocationSelected = ZomatoHomePageScreen.get()
                .isHomepageVisible()
                .selectLocationFromDropDown(location)
                .validateLocation(location);
        softly.assertThat(isLocationSelected).isTrue();
        return new ZomatoCityPageBL();
    }

    public ZomatoCityPageBL selectFromDetectLocation() {
        boolean isDetectLocationSelected = ZomatoHomePageScreen.get()
                .isHomepageVisible()
                .selectDetectLocation()
                .validateDetectLocation();
        softly.assertThat(isDetectLocationSelected).isTrue();
        return new ZomatoCityPageBL();
    }

    public ZomatoHomePageBL validateLocationDisabledMessage() {
        String getLocationMessage = ZomatoHomePageScreen.get()
                .getLocationErrorMessage();
        softly.assertThat(getLocationMessage).isEqualTo("Please enable location permission from settings and try again!");
        return this;
    }

    public ZomatoHomePageBL selectDetectLocation() {
        ZomatoHomePageScreen.get()
                .isHomepageVisible()
                .selectFromDetectLocation();
        return new ZomatoHomePageBL();
    }
}

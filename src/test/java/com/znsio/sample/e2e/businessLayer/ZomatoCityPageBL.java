package com.znsio.sample.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.ZomatoCityPageScreen;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomePageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ZomatoCityPageBL {
    private static final Logger LOGGER = Logger.getLogger(ZomatoCityPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ZomatoCityPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ZomatoCityPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ZomatoResturantPageBL selectResturant(String resturant) {
        boolean isResturantSelected = ZomatoCityPageScreen.get()
                .selectResturantFromDropdown(resturant)
                .validateResturant();
        softly.assertThat(isResturantSelected).isTrue();
        return new ZomatoResturantPageBL();
    }

    public ZomatoDishPageBL validateDishStatus(String dish, String foodStatus) {
        boolean isDishSelected = ZomatoCityPageScreen.get()
                .selectDish(dish, foodStatus)
                .validateDish(dish, foodStatus);
        softly.assertThat(isDishSelected).isTrue();
        return new ZomatoDishPageBL();
    }


    public ZomatoCityPageBL validateResturantWarning() {
        String getLocationWarning = ZomatoCityPageScreen.get()
                .getQuerryWarning();
        LOGGER.info("Location info message:-"+getLocationWarning );
        return this;
    }

    public ZomatoCityPageBL generateLocationInfoMessage() {
        boolean isLocationDropdownEmpty = ZomatoCityPageScreen.get()
                .validateEmptyDropdown("d");
        softly.assertThat(isLocationDropdownEmpty).isTrue();
        return this;
    }
}

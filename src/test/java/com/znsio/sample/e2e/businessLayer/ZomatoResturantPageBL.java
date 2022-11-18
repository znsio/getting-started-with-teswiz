package com.znsio.sample.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.ZomatoResturantPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ZomatoResturantPageBL {

    private static final Logger LOGGER = Logger.getLogger(ZomatoResturantPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public ZomatoResturantPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ZomatoResturantPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public boolean validateResturantPage() {
        boolean isResturantPageOpened = ZomatoResturantPageScreen.get().validateResturant();
        softly.assertThat(isResturantPageOpened).isTrue();
        return isResturantPageOpened;
    }
}

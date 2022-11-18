package com.znsio.sample.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.zomato.ZomatoDishPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ZomatoDishPageBL {
    private static final Logger LOGGER = Logger.getLogger(ZomatoDishPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public ZomatoDishPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ZomatoDishPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }


    public int validateResturantList() {
        int getCount = ZomatoDishPageScreen.get()
                .getResturantcount();
        softly.assertThat(getCount).isGreaterThan(0);
        return getCount;
    }
}

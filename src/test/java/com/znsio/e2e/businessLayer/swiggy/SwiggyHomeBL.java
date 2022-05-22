package com.znsio.e2e.businessLayer.swiggy;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.swiggy.SwiggyHomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class SwiggyHomeBL {


    private static final Logger LOGGER = Logger.getLogger(SwiggyHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SwiggyHomeBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.GUEST_USER;
        this.currentPlatform = Runner.platform;
    }

    public SwiggyHomeBL searchForDeliveryLocation(String deliveryLoc){
        SwiggyHomeScreen.get().searchForDeliveryLocation(deliveryLoc);
        return this;
    }


}

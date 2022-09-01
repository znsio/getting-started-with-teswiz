package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.CONTEXT_AMAZON;
import com.znsio.sample.e2e.screen.amazon.HomePageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class HomePageBL {
    private static final Logger LOGGER = Logger.getLogger(HomePageBL.class.getName());
    private final SoftAssertions softly;
    private final TestExecutionContext context;

    public HomePageBL(){
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        String currentUserPersona = CONTEXT_AMAZON.ME;
        Platform currentPlatform = Runner.platform;
      }
    public HomePageBL searchForAProduct(String productName) {
        HomePageScreen.get().searchProduct(productName);
        return this;
    }
}

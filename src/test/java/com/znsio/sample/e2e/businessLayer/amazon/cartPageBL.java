package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class cartPageBL {

    private static final Logger LOGGER = Logger.getLogger(cartPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;


    public cartPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public cartPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }


    public cartPageBL validatingIphoneOnCart() {
        boolean isIphoneAddedToCart = AmazonCartScreen.get().iphone13IsDisplayedOnCart();
        softly.assertThat(isIphoneAddedToCart).isTrue();
        return this;
    }
}

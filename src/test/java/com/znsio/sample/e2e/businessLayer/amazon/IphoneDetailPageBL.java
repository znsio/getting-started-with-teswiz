package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.IphoneDetailPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class IphoneDetailPageBL {

    private static final Logger LOGGER = Logger.getLogger(IphoneDetailPageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public IphoneDetailPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public IphoneDetailPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public IphoneDetailPageBL authenticateIphone13DetailPage() {
       boolean isFirstIphoneClicked = IphoneDetailPageScreen
                .get()
                .selectFirstIphone();

        return this;
    }


    public IphoneDetailPageBL addToCart() {
        IphoneDetailPageScreen
                .get()
                .addIphoneToCart();
        return this;
    }
}

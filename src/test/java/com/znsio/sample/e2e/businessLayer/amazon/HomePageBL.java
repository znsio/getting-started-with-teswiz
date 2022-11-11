package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageBL {
    private static final Logger LOGGER = Logger.getLogger(HomePageBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public HomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public HomePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }


    public IphoneDetailPageBL searchForIphone13() {
        boolean IphoneDetailPageOpened = AmazonHomePageScreen.get()
                .searchForiPhone13()
                .isIphoneListVisible()
                .selectFirstIphone();
        softly.assertThat(IphoneDetailPageOpened).isTrue();
        return new IphoneDetailPageBL();
    }
}

package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.jiomeet.InAMeetingBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.HomePageScreen;
import com.znsio.sample.e2e.screen.amazon.SearchPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class HomePageBL {
    private static final Logger LOGGER = Logger.getLogger(InAMeetingBL.class.getName());
    private final SoftAssertions softly;
    private final TestExecutionContext context;

    public HomePageBL(){
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        String currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        Platform currentPlatform = Runner.platform;
      }
    public SearchPageBL searchForAProduct(String productName) {

        SearchPageScreen homePageScreen = HomePageScreen.get().searchProduct(productName);
        return new SearchPageBL();
    }
}

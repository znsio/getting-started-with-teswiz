package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.jiomeet.InAMeetingBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import com.znsio.sample.e2e.screen.amazon.SearchPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class SearchPageBL {
    private static final Logger LOGGER = Logger.getLogger(InAMeetingBL.class.getName());
    private final SoftAssertions softly;
    private final TestExecutionContext context;
    public SearchPageBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        String currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        Platform currentPlatform = Runner.platform;
    }

    public ProductPageScreen selectProduct() {

        ProductPageScreen productPageScreen = SearchPageScreen.get().selectProductFromSearchResultPage();
        return productPageScreen;

    }
}

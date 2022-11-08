package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.SearchResultListPageScreen;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
public class SearchResultListPageBL {
    private static final Logger LOGGER = Logger.getLogger(SearchResultListPageBL.class.getName());
    private final TestExecutionContext context;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public SearchResultListPageBL(String userPersona, Platform forPlatform){
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public SearchResultListPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public SearchResultListPageBL validateIphoneResult() {
        boolean isPhoneNamePresent = SearchResultListPageScreen.get().verifyProductName();
        assertThat(isPhoneNamePresent).isTrue();
        boolean isIphoneListPresent = SearchResultListPageScreen.get().listCount();
        assertThat(isIphoneListPresent).isTrue();
        boolean isResultsTextPresent = SearchResultListPageScreen.get().verifyThePresenceOfResultsText();
        assertThat(isResultsTextPresent).isTrue();
        return this;
    }
    public ProductPageBL clickOnFirstProduct() {
        SearchResultListPageScreen.get().clickOnIphone();
        return new ProductPageBL();
    }
}

package com.znsio.sample.e2e.businessLayer.amazonCart;


import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_ASSIGNMENT_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;


public class AmazonHomePageBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomePageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }
    public AmazonHomePageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_ASSIGNMENT_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public AmazonHomePageBL launchAmazonHomePage() {
        boolean isAmazonHomepageLaunched = AmazonHomePageScreen.get().isAmazonHomepageLaunched();
        assertThat(isAmazonHomepageLaunched).as(String.format("Amazon Base URL Launch failed")).isTrue();
        return this;
    }

    public ProductDescriptionPageBL createProductSearch(String prod) {
        AmazonHomePageScreen.get().userSearchesForTheProduct(prod);
        return new ProductDescriptionPageBL() ;
    }
}

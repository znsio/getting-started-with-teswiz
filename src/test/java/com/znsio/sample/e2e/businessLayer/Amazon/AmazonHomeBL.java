package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.businessLayer.theapp.AppBL;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonCart.AmazonScreen;
import com.znsio.sample.e2e.screen.AmazonCart.ProductCartDetailsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonHomeBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonHomeBL.class.getName());

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonHomeBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonHomeBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonHomeBL get(){
        AmazonScreen.get();
        return this;
    }

    public SearchResultsBL searchForTheItem(String product){
        String signInMessage="Hello, Sign in";
        assertThat(AmazonScreen.get().getSignInStatus()).isEqualTo(signInMessage);
        //System.out.println("==========="+context.getTestState("CartValue"));
        LOGGER.info(System.out.printf("Searching for a '%s'",product));
        AmazonScreen.get().searchTheProduct(product);
        return new SearchResultsBL();
    }
}

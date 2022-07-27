package com.znsio.sample.e2e.businessLayer.theapp;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.theapp.AmazonCartScreen;
import com.znsio.sample.e2e.screen.theapp.AmazonResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class AmazonSearchResultsBL {

    private static final Logger LOGGER = Logger.getLogger(AmazonHomeBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public AmazonSearchResultsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public AmazonSearchResultsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public AmazonResultsScreen SearchProduct(String product) {
        AmazonResultsScreen amazonResultsScreen = AmazonResultsScreen.get().SearchProduct(product);
        return AmazonResultsScreen.get();
    }

    public AmazonResultsScreen VerifyingProduct(String product) {
        AmazonResultsScreen amazonResultsScreen = AmazonResultsScreen.get().VerifyProductDetail(product);
        return AmazonResultsScreen.get();
    }

    public AmazonResultsScreen AddProduct() {
        AmazonResultsScreen amazonResultsScreen = AmazonCartScreen.get().AddingProduct();
        return AmazonResultsScreen.get();
    }


}

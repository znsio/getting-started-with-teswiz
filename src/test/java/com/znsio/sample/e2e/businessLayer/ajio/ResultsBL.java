package com.znsio.sample.e2e.businessLayer.ajio;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.ajio.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class ResultsBL {
    private static final Logger LOGGER = Logger.getLogger(ResultsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ResultsBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public ResultsBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public ResultsBL refineProducts(String genderFilter , String sizeFilter) {
        LOGGER.info(String.format("refineProducts: Refine product on gender filter '%s' and size filter '%s'", genderFilter, sizeFilter));
        SearchResultsScreen searchResultsScreen = SearchResultsScreen.get().refineOnGender(genderFilter );
        searchResultsScreen.refineOnSize(sizeFilter).selectApply();
        softly.assertThat(searchResultsScreen.getAppliedFilters() ).as("Product is not refined on the basis of gender").isGreaterThan(1);
        return this;
    }

    public ProductDetailBL selectProduct() {
        LOGGER.info(String.format("selectProduct: Select product from the product list"));
        SearchResultsScreen.get().selectFirstProduct();
        return new ProductDetailBL();
    }
}
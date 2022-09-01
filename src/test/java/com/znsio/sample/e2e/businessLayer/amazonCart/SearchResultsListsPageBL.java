package com.znsio.sample.e2e.businessLayer.amazonCart;


import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AMAZON_ASSIGNMENT_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductListPageScreen;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SearchResultsListsPageBL {

    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;


    public SearchResultsListsPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_ASSIGNMENT_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }

    public SearchResultsListsPageBL searchedProductIsVisible(String product) {

        boolean validateProductTitle = ProductListPageScreen.get().getTupleTitle().contains(product);
        softly.assertThat(validateProductTitle).as(String.format("Results Not visible for '%s'", product)).isTrue();
        String productTitle = ProductListPageScreen.get().getTupleTitle();
        context.addTestState(AMAZON_ASSIGNMENT_TEST_CONTEXT.TITLE_PRODUCT_1, productTitle);
        String productPrice = ProductListPageScreen.get().getTuplePrice();
        context.addTestState(AMAZON_ASSIGNMENT_TEST_CONTEXT.PRICE_PRODUCT_1, productPrice);
        return new SearchResultsListsPageBL();
    }

    public ProductDescriptionPageBL userSelectsFirstProduct() {
        ProductListPageScreen.get().userSelectsFirstProduct();
        return new ProductDescriptionPageBL();
    }
}

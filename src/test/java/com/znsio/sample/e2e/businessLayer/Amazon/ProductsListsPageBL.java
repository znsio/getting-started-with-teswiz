package com.znsio.sample.e2e.businessLayer.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.AmazonShopping.AMAZON_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.AmazonScreens.ProductListPageScreen;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductsListsPageBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public ProductsListsPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = AMAZON_SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;

    }

    public ProductsListsPageBL userSeesListOfProducts(String productToFind) {
        List<WebElement> ProductList = ProductListPageScreen.get().getProductLinksList();
        context.addTestState(AMAZON_SAMPLE_TEST_CONTEXT.PRODUCT_LIST, ProductList);
        boolean validateListOfProducts = ProductListPageScreen.get().validateListOfProducts(productToFind, ProductList);
        softly.assertThat(validateListOfProducts).as(String.format("List is not showing results for '%s'", productToFind)).isTrue();
        String firstProductTitle = ProductListPageScreen.get().getFirstProductTittle(ProductList);
        context.addTestState(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_TITLE, firstProductTitle);
        String productPrice = ProductListPageScreen.get().getFirstProductPrice();
        context.addTestState(AMAZON_SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_PRICE, productPrice);
        return new ProductsListsPageBL();
    }

    public ProductsLandingPageBL userSelectsFirstProduct(List<WebElement> productList) {
        ProductListPageScreen.get().userSelectsFirstProduct(productList);
        return new ProductsLandingPageBL();
    }
}

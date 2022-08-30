package com.znsio.sample.e2e.businessLayer.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonGridWallPageScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductPageScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageBL {
    private static final Logger LOGGER = Logger.getLogger(HomePageBL.class.getName());
    private TestExecutionContext context;
    private  SoftAssertions softly;
    private  String currentUserPersona;
    private  Platform currentPlatform;
    String selectedProductDesc;

    public ProductPageBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);

    }
    public ProductPageBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = SAMPLE_TEST_CONTEXT.ME;
        this.currentPlatform = Runner.platform;
    }
    public ProductPageBL verifyProductPageAndProductDesc(String expectedProductDesc){
        Boolean isProductPageDisplayed = AmazonProductPageScreen.get().validateProductPageDisplay();
        assertThat(isProductPageDisplayed).as(currentUserPersona + " Product page is not displayed")
                .isTrue();
        Boolean isProductDescMatchWithGridWallPage = AmazonProductPageScreen.get().verifyProductDescSyncWithGridWallPage(expectedProductDesc);
        assertThat(isProductDescMatchWithGridWallPage).as(currentUserPersona + "Product description is not matched between Gridwall page and product page")
               .isTrue();
        return this;
    }

    public ProductPageBL verifyAddToCartButton() {
        Boolean isProductPageDisplayed = AmazonProductPageScreen.get().verifyAddToCartButton();
        assertThat(isProductPageDisplayed).as(currentUserPersona + "Add to cart button is not displayed in product page")
                .isTrue();
        return this;
    }

    public CartPageBL clickAddToCartButton() {
        AmazonProductPageScreen.get().clickAddToCartButton();
        return new CartPageBL();
    }
}

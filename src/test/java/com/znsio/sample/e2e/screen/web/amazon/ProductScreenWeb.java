package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.ProductScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductScreenWeb extends ProductScreen {
    private static final String SCREEN_NAME = ProductScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;

//    private static final By backToProductLinkId=By.id("breadcrumb-back-link");
    private static final By byShareProductDetailsButton =By.xpath("//i[@title='Share']");
    private static final By byProductTitleId =By.id("productTitle");
    private static final By byProductPriceXpath =By.xpath("//span[@class='a-price']/span[@class='a-offscreen']");
    private static final By byAddToCartButtonId =By.id("add-to-cart-button");
    private static final By byCartPopupCloseButtonId =By.id("attach-close_sideSheet-link");

    public ProductScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.driver = driver;
        this.visually = visually;
        this.context= Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean isUserOnProductPage() {
        LOGGER.debug("On ProductsDetail Page");
        visually.checkWindow(SCREEN_NAME,"On ProductsDetail page");
        driver.waitTillElementIsPresent(byShareProductDetailsButton);
        return driver.isElementPresent(byShareProductDetailsButton);
    }
    private ProductScreen detailsOfProductToBeAdded(){
        String productName=driver.findElement(byProductTitleId).getText();
        String productPrice=driver.findElement(byProductPriceXpath).getText();
        if(context.getTestStateAsString(SAMPLE_TEST_CONTEXT.FIRST_PRODUCT_ON_RESULTS_PAGE).equals(productName)) {
            context.addTestState(SAMPLE_TEST_CONTEXT.NAME_OF_PRODUCT_ADDED_TO_CART, productName);
            context.addTestState(SAMPLE_TEST_CONTEXT.PRICE_OF_PRODUCT_ADDED_TO_CART, productPrice);
        }
        return this;
    }
    private ProductScreen iClickAddToCart(){
        driver.waitForClickabilityOf(byAddToCartButtonId);
        driver.findElement(byAddToCartButtonId).click();
        return this;
    }
    private ProductScreen iDismissTheCartDetailsSidePopup(){
        driver.waitForClickabilityOf(byCartPopupCloseButtonId);
        driver.findElement(byCartPopupCloseButtonId).click();
        return this;
    }
    @Override
    public boolean isAddToCartButtonIPresent() {
        return driver.isElementPresent(byAddToCartButtonId);
    }

    @Override
    public ProductScreen iAddProductToCart() {
        detailsOfProductToBeAdded();
        iClickAddToCart();
        LOGGER.info("add to cart button clicked");
        iDismissTheCartDetailsSidePopup();
        return this;
    }

}

package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.CartScreen;
import com.znsio.sample.e2e.screen.amazon.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductFoundNameId = By.id("productTitle");

    private static final By byProductFoundCostXpath = By.xpath("//span[@id='tp_price_block_total_price_ww']/span[1]");

    private static final By byaddToCartBtnId = By.id("add-to-cart-button");

    private static final By byGoToCartBtnId = By.id("attach-sidesheet-view-cart-button");

    private static final By byAddToCartTextXpath = By.xpath("(//div[@id=\"attachDisplayAddBaseAlert\"])/span");

    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product Details screen");
    }

    @Override
    public String getActualProductName() {

        driver.switchToNextTab();
        String itemName = driver.waitTillElementIsPresent(byProductFoundNameId)
                .getText();

        LOGGER.info(String.format("Actual item name: '%s'", itemName));
        return itemName;
    }

    @Override
    public String getActualProductCost() {
        String itemCost = driver.waitTillElementIsPresent(byProductFoundCostXpath)
                .getText();

        LOGGER.info(String.format("Actual item cost: '%s'", itemCost));

        return itemCost;
    }

    @Override
    public ProductDetailsScreen addToCart(){
        driver.waitTillElementIsPresent(byaddToCartBtnId).click();
        visually.checkWindow(SCREEN_NAME, "Item added to cart");
        return this;
    }

    @Override
    public boolean isItemAddedToCartTextVisible(){
        return driver.waitTillElementIsVisible(byAddToCartTextXpath).isDisplayed();
    }

    @Override
    public CartScreen navigateToCartPage(){
        driver.waitTillElementIsVisible(byGoToCartBtnId).click();
        visually.checkWindow(SCREEN_NAME, "Go to cart page");
        return CartScreen.get();
    }
}

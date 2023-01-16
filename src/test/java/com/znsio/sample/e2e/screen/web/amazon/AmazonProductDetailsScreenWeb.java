package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonProductDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonProductDetailsScreenWeb extends AmazonProductDetailsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonProductDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductFoundNameId = By.id("productTitle");

    private static final By byProductFoundCostXpath = By.xpath("//span[@id='tp_price_block_total_price_ww']/span[1]");

    private static final By byaddToCartBtnId = By.id("add-to-cart-button");

    private static final By byGoToCartBtnId = By.id("attach-sidesheet-view-cart-button");

    private static final By byAddToCartTextXpath = By.xpath("(//div[@id=\"attachDisplayAddBaseAlert\"])/span");

    public AmazonProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product Details screen");
    }

    @Override
    public String getActualItemName() {

        driver.switchToNextTab();
        String itemName = driver.waitTillElementIsPresent(byProductFoundNameId)
                .getText();

        LOGGER.info(String.format("Actual item name: '%s'", itemName));
        return itemName;
    }

    @Override
    public String getActualItemCost() {
        String itemCost = driver.waitTillElementIsPresent(byProductFoundCostXpath)
                .getText();

        LOGGER.info(String.format("Actual item cost: '%s'", itemCost));

        return itemCost;
    }

    @Override
    public AmazonProductDetailsScreen addToCart(){
        driver.waitTillElementIsPresent(byaddToCartBtnId).click();
        visually.checkWindow(SCREEN_NAME, "Item added to cart");
        return this;
    }

    @Override
    public boolean isItemAddedToCartTextVisible(){
        return driver.waitTillElementIsVisible(byAddToCartTextXpath).isDisplayed();
    }

    @Override
    public AmazonCartScreen goToCartPage(){
        driver.waitTillElementIsVisible(byGoToCartBtnId).click();
        visually.checkWindow(SCREEN_NAME, "Go to cart page");
        return AmazonCartScreen.get();
    }
}

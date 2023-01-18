package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.CartScreen;
import com.znsio.sample.e2e.screen.amazon.ItemsDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ItemsDetailsScreenWeb extends ItemsDetailsScreen {

    private final Driver driver;

    private final Visual visually;
    private static final String SCREEN_NAME = ItemsDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductTitleId= By.id("productTitle");
    private static final By byAddToCartButtonXpath= By.id("add-to-cart-button");

    private static final By byAddedToCartTextXpath = By.xpath("//div[@id='attachDisplayAddBaseAlert']//span");
    private static final By byCartButtonId= By.id("attach-sidesheet-view-cart-button");


    public ItemsDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Items Details Screen Web ");
        driver.switchToNextTab();
    }

    @Override
    public String getScreenTitle() {
        String screenTitle =  driver.getInnerDriver().getTitle();
        LOGGER.info(String.format("Item Details screen title is: '%s'", screenTitle));
        return screenTitle;
    }

    @Override
    public String getItemsTitle() {
        String itemTitle = driver.waitTillElementIsPresent(byProductTitleId).getText();
        LOGGER.info(String.format("Item title on Item details screen: '%s'", itemTitle));
        return itemTitle;
    }

    @Override
    public ItemsDetailsScreenWeb selectAddToCart() {
        driver.waitForClickabilityOf(byAddToCartButtonXpath).click();
        return this;
    }

    @Override
    public String getCartCreationSuccessText() {
        String addToCartConfirmationText = driver.waitForClickabilityOf(byAddedToCartTextXpath).getText();
        LOGGER.info(String.format("Add to Cart Confirmation Text: '%s'", addToCartConfirmationText));
        return addToCartConfirmationText;
    }

    @Override
    public CartScreen selectCart() {
        driver.waitForClickabilityOf(byCartButtonId).click();
        return CartScreen.get();
    }
}

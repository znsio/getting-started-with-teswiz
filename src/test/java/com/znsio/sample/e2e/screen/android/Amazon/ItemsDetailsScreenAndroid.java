package com.znsio.sample.e2e.screen.android.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.CartScreen;
import com.znsio.sample.e2e.screen.amazon.ItemsDetailsScreen;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ItemsDetailsScreenAndroid extends ItemsDetailsScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ItemsDetailsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byHeartBackgroundXpath = By.xpath("//android.view.View[@resource-id='heart-background']");
    private static final By byProductTitleXpath = By.xpath("//android.view.View[@resource-id='title_feature_div']/android.view.View");
    private static final By byDoneTextXpath = By.xpath("//android.view.View[@text='DONE']");
    private static final By byAddedToCartTextXpath = By.xpath("//android.view.View[@resource-id='atc-success']");
    private static final By byAddedToYourCartTextXpath = By.xpath("//*[@text='Added to your cart']");
    private static final By byCartButtonId = By.id("com.amazon.mShop.android.shopping:id/cart_count");

    public ItemsDetailsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Items Details Screen Android");
    }

    @Override
    public boolean isScreenLoaded() {
        boolean loadStatus = driver.waitTillElementIsPresent(byHeartBackgroundXpath, 20).isDisplayed();
        LOGGER.info(String.format("Item Details screen  load status: '%s'", loadStatus));
        return loadStatus;
    }

    @Override
    public String getItemsTitle() {
        String itemTitle = driver.waitTillElementIsPresent(byProductTitleXpath).getText();
        LOGGER.info(String.format("Item title on Item details screen: '%s'", itemTitle));
        return itemTitle;
    }

    @Override
    public ItemsDetailsScreen selectAddToCart() {
        AndroidElement addToCartButton = (AndroidElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Add to Cart\"))"));
        addToCartButton.click();
        return this;
    }

    @Override
    public String getCartCreationSuccessText() {
        String addToCartConfirmationText = "";
        try {
            addToCartConfirmationText = driver.waitTillElementIsPresent(byAddedToCartTextXpath).getText();
            addToCartConfirmationText = addToCartConfirmationText.replaceAll("[^a-zA-Z\\s+]", "").trim();

        } catch (Exception e) {
            addToCartConfirmationText = driver.waitTillElementIsVisible(byAddedToYourCartTextXpath).getText();
            driver.findElement(byDoneTextXpath).click();
        }
        LOGGER.info(String.format("Add to Cart Confirmation Text: '%s'", addToCartConfirmationText));
        return addToCartConfirmationText.toLowerCase();
    }

    @Override
    public CartScreen selectCart() {
        driver.waitForClickabilityOf(byCartButtonId).click();
        return CartScreen.get();
    }
}

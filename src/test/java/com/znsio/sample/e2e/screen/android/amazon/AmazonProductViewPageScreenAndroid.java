package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductViewPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonProductViewPageScreenAndroid extends AmazonProductViewPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By addToCartButtonByXpath = By.xpath("//android.widget.Button[@resource-id=\"add-to-cart-button\"]");
    private static final By addToCartSuccessMessageByXpath = By.xpath("//android.widget.TextView[@text=\"Added to cart\"]");
    private static final By cartButtonByXpath = By.xpath("//android.widget.Button[@resource-id=\"a-autoid-4-announce\"]");
    private static final By productTitleById = By.id("productTitle");

    public AmazonProductViewPageScreenAndroid(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Product View Page");
    }

    @Override
    public AmazonProductViewPageScreenAndroid addProductToCart() {

        LOGGER.info("Clicking on add to cart button on product view page");
        driver.scrollToAnElementByText("Add to Cart");
        WebElement addToCartButton = driver.waitTillElementIsPresent(addToCartButtonByXpath);
        addToCartButton.click();
        visually.checkWindow(SCREEN_NAME, "Product added to cart");
        return this;
    }

    @Override
    public AmazonShoppingCartScreenAndroid navigateToShoppingCart() {

        LOGGER.info("Clicking on cart button on product view page");
        WebElement cartButton = driver.waitTillElementIsVisible(cartButtonByXpath);
        cartButton.click();
        visually.checkWindow(SCREEN_NAME, "User navigated to cart");
        return new AmazonShoppingCartScreenAndroid(driver, visually);
    }

    @Override
    public String getAddToCartSuccessMessage() {

        LOGGER.info("Fetching add to cart success message from product view page");
        String addToCartSuccessMessage = driver.waitTillElementIsVisible(addToCartSuccessMessageByXpath).getText().trim();
        LOGGER.info(String.format("Add to Cart success message : %s", addToCartSuccessMessage));
        return addToCartSuccessMessage;
    }

    @Override
    public String getProductTitle() {

        LOGGER.info("Fetching product title from product view page");
        String productTitle = driver.waitTillElementIsPresent(productTitleById).getText().trim();
        LOGGER.info(String.format("Product Title : %s", productTitle));
        return productTitle;
    }

    @Override
    public boolean isAddToCartSuccessMessageAvailable() {

        LOGGER.info("Checking the presence of 'Added to Cart' success message on product view page");
        try {
            driver.waitTillElementIsPresent(addToCartSuccessMessageByXpath);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }
}

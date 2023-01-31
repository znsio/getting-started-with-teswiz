package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductViewScreen;
import com.znsio.sample.e2e.screen.web.amazon.AmazonHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class AmazonProductViewAndroid extends AmazonProductViewScreen{

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By byFirstProductXpath = By.xpath("//android.view.View[contains(@content-desc, \""+ SAMPLE_TEST_CONTEXT.PRODUCT_NAME+"\")]/android.view.View[2]");
    private static final By byProductTitleXpath = By.xpath("//android.view.View[@resource-id = \"title\"]");
    private static final By byAddCartButtonXpath = By.xpath("//android.widget.Button[@resource-id = \"add-to-cart-button\"]");
    private static final By byAddedToCartMsgXpath = By.xpath("//div[@id='attachDisplayAddBaseAlert']/span");

    public AmazonProductViewAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonProductViewScreen selectFirstProduct(){
        LOGGER.info(String.format("Select the first product"));
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        driver.waitTillElementIsPresent(byFirstProductXpath).click();
        return this;
    }

    @Override
    public boolean verifyProductDetails(){
        LOGGER.info("Get the product details from the product detail page");
        WebElement element = driver.waitTillElementIsPresent(byProductTitleXpath);
        return element.isDisplayed();
    }

    @Override
    public AmazonProductViewScreen clickAddToCartButton(){
        LOGGER.info(String.format("Add product to the cart"));
        driver.scrollToAnElementByText("Add to Cart");
        driver.waitTillElementIsPresent(byAddCartButtonXpath).click();
        return this;
    }

    @Override
    public String getAddedToCartMessage(){
        LOGGER.info("Get the added to cart message");
        String msg = driver.waitTillElementIsVisible(byAddedToCartMsgXpath).getText();
        return msg;
    }
}

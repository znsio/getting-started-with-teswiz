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

    private static By firstProduct = By.xpath("//android.view.View[contains(@content-desc, \""+ SAMPLE_TEST_CONTEXT.PRODUCT_NAME+"\")]/android.view.View[2]");
    private static final By productTitle = By.xpath("//android.view.View[@resource-id = \"title\"]");
    private static By addCartButton = By.xpath("//android.widget.Button[@resource-id = \"add-to-cart-button\"]");
    private static final By addedToCartMsg = By.xpath("//div[@id='attachDisplayAddBaseAlert']/span");

    public AmazonProductViewAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonProductViewScreen selectFirstProduct(){
        LOGGER.info(String.format("Select the first product"));
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        driver.waitTillElementIsPresent(firstProduct).click();
        return this;
    }

    @Override
    public boolean verifyProductDetails(){
        WebElement element = driver.waitTillElementIsPresent(productTitle);
        return element.isDisplayed();
    }

    @Override
    public AmazonProductViewScreen clickAddToCartButton(){
        LOGGER.info(String.format("Add product to the cart"));
        while(true) {
            waitFor(1);
            if(driver.isElementPresent(addCartButton))
                break;
            driver.scrollDownByScreenSize();
        }

        driver.findElement(addCartButton).click();
        return this;
    }

    @Override
    public String getAddedToCartMessage(){
        LOGGER.info("Get the added to cart message");
        String msg = driver.waitTillElementIsVisible(addedToCartMsg).getText();
        return msg;
    }
}

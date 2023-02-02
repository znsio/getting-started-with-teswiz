package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductViewScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class AmazonProductViewWeb extends AmazonProductViewScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By byFirstProductXpath = By.xpath("//div[@data-index='2']//h2//span");
    private static final By byAddCartButtonId = By.id("add-to-cart-button");
    private static final By byProductTitleId = By.id("productTitle");
    private static final By bySideBarCloseButtonId = By.id("attach-close_sideSheet-link");
    private static final By byAddedToCartMsgXpath = By.xpath("//div[@id='attachDisplayAddBaseAlert']/span");

    public AmazonProductViewWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonProductViewScreen selectFirstProduct(){
        driver.scrollTillElementIntoView(byFirstProductXpath);
        WebElement selectProduct = driver.waitTillElementIsPresent(byFirstProductXpath);
        LOGGER.info(String.format("Select the first product :" + selectProduct.getText()));
        selectProduct.click();
        return this;
    }

    @Override
    public boolean verifyProductDetails(){
        visually.checkWindow(SCREEN_NAME, "Product detail page");
        driver.switchToNextTab();
        WebElement element = driver.findElement(byProductTitleId);
        LOGGER.info(String.format("Product heading displayed :" + element.getText()));
        return element.isDisplayed();
    }

    @Override
    public AmazonProductViewScreen clickAddToCartButton(){
        LOGGER.info(String.format("Add product to the cart"));
        driver.waitForClickabilityOf(byAddCartButtonId).click();
        return this;
    }

    @Override
    public String getAddedToCartMessage(){
        String msg = driver.waitTillElementIsVisible(byAddedToCartMsgXpath).getText();
        LOGGER.info("Message displayed: " + msg);
        driver.waitTillElementIsVisible(bySideBarCloseButtonId).click();
        return msg;
    }
}

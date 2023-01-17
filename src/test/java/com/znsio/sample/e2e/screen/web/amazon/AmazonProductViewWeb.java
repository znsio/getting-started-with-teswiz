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

    private static final By firstProduct = By.xpath("//div[@data-index='2']//h2//span");
    private static final By addCartButton = By.id("add-to-cart-button");
    private static final By productTitle = By.id("productTitle");
    private static final By sideBarCloseButton = By.id("attach-close_sideSheet-link");

    public AmazonProductViewWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonProductViewScreen selectFirstProduct(){
        LOGGER.info(String.format("Select the first product"));
        driver.scrollTillElementIntoView(firstProduct);
        WebElement selectProduct = driver.waitTillElementIsPresent(firstProduct);
        selectProduct.click();
        return this;
    }

    @Override
    public boolean verifyCorrectProductDetails(){
        driver.switchToNextTab();
        WebElement element = driver.findElement(productTitle);
        return element.isDisplayed();
    }

    @Override
    public AmazonProductViewScreen addProductToCart(){
        LOGGER.info(String.format("Add product to the cart"));
        driver.waitForClickabilityOf(addCartButton).click();
        driver.waitTillElementIsVisible(sideBarCloseButton).click();
        return this;
    }
}

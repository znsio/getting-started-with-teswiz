package com.znsio.sample.e2e.screen.web.amazonsearch;
import static org.assertj.core.api.Assertions.assertThat;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonCartScreenWeb extends AmazonCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byAddedToCartXpath = By.xpath("//*[@id='attachDisplayAddBaseAlert']/span");
    private static final By byAddToCartButton = By.xpath("//span[@class='a-button-inner']/child::span[contains(text(),'Cart')]/preceding-sibling::input[@class='a-button-input' and @type='submit']");
    private static final By byproductTitleXpath = By.xpath("//span[@class='a-list-item']/descendant::span[@class='a-truncate-cut' and contains(text(), 'Apple iPhone 13')]");

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    @Override
    public AmazonCartScreen openCartLandingPage() {
        driver.waitTillElementIsVisible(byAddedToCartXpath);
        assertThat(driver.isElementPresent(byAddedToCartXpath)).as("Product not added to the cart").isTrue();
        driver.findElement(byAddToCartButton).click();
        return AmazonCartScreen.get();
    }

    @Override
    public AmazonCartScreen verifyProductPresentInTheCart(String ProductName) {
        driver.waitTillElementIsPresent(byproductTitleXpath);
        assertThat(driver.isElementPresent(byproductTitleXpath)).as("Product name not present").isTrue();
        return AmazonCartScreen.get();
    }
}

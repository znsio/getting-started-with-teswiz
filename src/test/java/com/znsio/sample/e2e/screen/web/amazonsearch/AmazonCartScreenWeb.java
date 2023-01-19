package com.znsio.sample.e2e.screen.web.amazonsearch;
import static org.assertj.core.api.Assertions.assertThat;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

public class AmazonCartScreenWeb extends AmazonCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final By byAddedToCartXpath = By.xpath("//*[@id='attachDisplayAddBaseAlert']/span");
    private static final By byCartButton = By.xpath("//span[@class='a-button-inner']/child::span[contains(text(),'Cart')]/preceding-sibling::input[@class='a-button-input' and @type='submit']");
    private static final By byproductTitleXpath = By.xpath("//span[@class='a-list-item']/descendant::span[@class='a-truncate-cut' and contains(text(), 'Apple iPhone 13')]");

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home Page");
    }

    @Override
    public AmazonCartScreen clickOnCartButton() {
        driver.waitTillElementIsVisible(byAddedToCartXpath);
        assertThat(driver.isElementPresent(byAddedToCartXpath)).as("Product not added to the cart").isTrue();
        driver.findElement(byCartButton).click();
        return this;
    }

    @Override
    public boolean verifyProductPresentInTheCart(String ProductName) {
        driver.waitTillElementIsPresent(byproductTitleXpath);
        boolean isProductPresent = driver.isElementPresent(byproductTitleXpath);
        visually.checkWindow(SCREEN_NAME, "Product list in cart");
        return isProductPresent;
    }
}

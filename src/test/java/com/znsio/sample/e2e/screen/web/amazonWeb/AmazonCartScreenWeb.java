package com.znsio.sample.e2e.screen.web.amazonWeb;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonCartScreenWeb extends AmazonCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byProductName = By.xpath("//span[contains(text(),'Apple iPhone 13')]");
    private static final By shoppingCartHeading = By.cssSelector("div[class='a-row'] h1");

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "cart screen");
    }

    @Override
    public String AmIOnCartPage() {
        LOGGER.debug("On Shopping Cart Page");
        visually.checkWindow(SCREEN_NAME,"On Shopping Cart page");
        return driver.findElement(shoppingCartHeading).getText();
    }

    @Override
    public String verifyAddedProductInCart() {
     return driver.findElement(byProductName).getText();
    }

}

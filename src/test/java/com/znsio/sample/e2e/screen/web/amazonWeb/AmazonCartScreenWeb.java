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

    private static final By bySubtotalText = By.id("sc-subtotal-label-activecart");
    private static final By byShoppingCartHeading = By.cssSelector("div[class='a-row'] h1");
    private static final By byProductNameAddedToTheCart = By.xpath("(//span[@class='a-truncate-cut'])[1]");

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "cart screen");
    }

    @Override
    public String AmIOnCartPage() {
        LOGGER.debug("On Shopping Cart Page");
        visually.checkWindow(SCREEN_NAME, "On Shopping Cart page");
        return driver.findElement(byShoppingCartHeading).getText();
    }

    @Override
    public String verifySubTotalText() {
        LOGGER.info("Verifying the presence of subtotal text on cart page");
        String product_name = driver.findElement(bySubtotalText).getText();
        return product_name;
    }

    @Override
    public boolean verifyThePresenceOfAddedProductInTheCart() {
        String productName = driver.findElement(byProductNameAddedToTheCart).getText();
        if (productName.contains("iPhone 13"))
            return true;
        else
            return false;
    }

}

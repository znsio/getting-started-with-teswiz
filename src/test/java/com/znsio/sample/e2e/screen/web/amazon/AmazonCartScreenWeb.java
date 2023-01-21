package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonCartScreenWeb extends AmazonCartScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By byCartNavigateByXpath = By.xpath("//span[@id='attach-sidesheet-view-cart-button']");
    private final By byProductNameOnCartByXpath = By.xpath("//div[@class='sc-list-item-content']/descendant::span[@class='a-truncate-cut']");

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Cart screen");
    }

    @Override
    public AmazonCartScreen navigateToCart() {
        driver.waitForClickabilityOf(byCartNavigateByXpath,5).click();
        visually.checkWindow(SCREEN_NAME, "land on shopping cart");
       return AmazonCartScreen.get();
    }

    @Override
    public String getProductNameInCart() {
        return  driver.waitTillElementIsPresent(byProductNameOnCartByXpath,2).getText();
    }

}

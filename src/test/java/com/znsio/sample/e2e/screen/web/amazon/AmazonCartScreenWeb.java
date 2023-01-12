package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonCartScreenWeb extends AmazonCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    private static final By byCartHeadingTextXpath = By.xpath("//h1");
    private static final By byCartItemNameXpath = By.xpath("(//div[@class='sc-item-content-group'])/descendant::a/span/span/span[2]");
    private static final By byCartItemCostXpath = By.xpath("(//div[@id=\"attachDisplayAddBaseAlert\"])/span");

    public AmazonCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public boolean isCartHeadingVisible(){
        return driver.isElementPresent(byCartHeadingTextXpath);
    }

    @Override
    public String getCartItemName(){
        String cartItemName = driver.waitTillElementIsPresent(byCartItemNameXpath).getText();
        LOGGER.info(String.format("Cart item name: '%s'", cartItemName));
        return cartItemName;
    }

    @Override
    public String getCartItemCost(){
        return "";
    }
}

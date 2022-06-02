package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.SearchBL;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.CartScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CartScreenWeb extends CartScreen {
    private final Driver driver;
    private static final Logger LOGGER = Logger.getLogger(SearchBL.class.getName());
    private final TestExecutionContext context;
    private final Visual visually;
    private final String SCREEN_NAME = CartScreenWeb.class.getSimpleName();

    private final By cartOptionByXpath = By.xpath("//span[text()='Cart']");

    private final By itemNameInCartByXpath = By.xpath("//div[@class='_33KRy']");

    private final By plusButtonByXpath =By.xpath("//div[text()='+']");

    private final By cartOptionInRestaurantMenuPageByXpath = By.xpath("//div[text()='Cart']");
    public CartScreenWeb(Driver driver, Visual visually) {

            long threadId = Thread.currentThread().getId();
            this.context = Runner.getTestExecutionContext(threadId);
            this.driver = driver;
            this.visually = visually;
            visually.takeScreenshot(SCREEN_NAME, "Restaurant Listing Screen");

    }

    @Override
    public CartScreen openCartOption() {
        driver.waitTillElementIsPresent(cartOptionByXpath).click();
        return this;
    }

    @Override
    public String getItemNameAddedInCart() {
        driver.waitTillElementIsPresent(itemNameInCartByXpath);
        return driver.findElement(itemNameInCartByXpath).getText().replaceAll("\\n.*","");
    }

    @Override
    public Boolean validateCartOptionVisible() {
        return driver.findElement(cartOptionInRestaurantMenuPageByXpath).isDisplayed();
    }


}

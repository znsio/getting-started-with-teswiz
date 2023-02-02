package com.znsio.sample.e2e.screen.android.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AmazonShoppingCartScreenAndroid extends AmazonShoppingCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By shoppingCartPageHeadingByCSS = By.cssSelector(".sc-cart-header");
    private static final By shoppingCartProductTitlesByXpath = By.xpath("//android.view.View[contains(@content-desc, \"iPhone 13\")]/android.widget.TextView");
    private static final By proceedToBuyButtonByXpath = By.xpath("//android.view.View[@resource-id=\"mobile-ptc-button-celWidget\"]//android.widget.Button");

    public AmazonShoppingCartScreenAndroid(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Shopping Cart Page");
    }

    @Override
    public String getShoppingCartPageTitle() {

        LOGGER.info("Fetching shopping cart page title");
        String shoppingCartPageTitle = driver.waitTillElementIsPresent(shoppingCartPageHeadingByCSS).getText().trim();
        LOGGER.info(String.format("Shopping Cart Page Title : %s", shoppingCartPageTitle));
        return shoppingCartPageTitle;
    }

    @Override
    public List<String> getTitleOfAllProductsInShoppingCart() {

        LOGGER.info("Fetching titles of all products in the shopping cart");
        WebDriverWait wait = new WebDriverWait(driver.getInnerDriver(), 30);
        List<WebElement> productTitleWebElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(shoppingCartProductTitlesByXpath));

        List<String> productTitles = new ArrayList<>();
        for (WebElement element : productTitleWebElements) {
            String productTitle = element.getText().strip();
            productTitles.add(productTitle);
        }
        return productTitles;
    }

    @Override
    public boolean isProceedToBuyButtonPresent() {
        LOGGER.info("Checking the presence of 'Add To Buy' button on cart view page");
        try {
            driver.waitTillElementIsPresent(proceedToBuyButtonByXpath);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

}

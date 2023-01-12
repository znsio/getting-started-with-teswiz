package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonShoppingCartScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AmazonShoppingCartScreenWeb extends AmazonShoppingCartScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By shoppingCartPageHeadingByCSS = By.cssSelector(".sc-cart-header");
    private static final By shoppingCartProductTitlesByCSS = By.cssSelector(".sc-product-title");
    public AmazonShoppingCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Shopping Cart Page");
    }

    @Override
    public String getShoppingCartPageHeading() {
        String shoppingCartPageHeading = driver.waitTillElementIsPresent(shoppingCartPageHeadingByCSS).getText().trim();
        LOGGER.info(String.format("Shopping Cart Page Heading : %s", shoppingCartPageHeading));
        return shoppingCartPageHeading;
    }

    @Override
    public List<String> getTitleOfAllProductsInShoppingCart() {
        WebDriverWait wait = new WebDriverWait(driver.getInnerDriver(),30);
        List<WebElement> productTitleWebElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(shoppingCartProductTitlesByCSS));

        List<String> productTitles = new ArrayList<>();
        for(WebElement element: productTitleWebElements){
            String productTitle = element.getText().strip();
            LOGGER.info(String.format("Shopping Cart Product Title : %s", productTitle));
            productTitles.add(element.getText().strip());
        }
        return productTitles;
    }

}

package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.CartPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class CartPageScreenWeb extends CartPageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = CartPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byActiveCartContentsXpath = By.xpath("//div[@data-name='Active Cart']");
    private final By byProductTitleChildXpath = By.xpath(".//span[@class='a-truncate-cut']");
    private final By byProductPriceChildXpath = By.xpath(".//span[contains(@class,'sc-product-price')]");
    private final By byProductQuantityChildXpath = By.xpath(".//span[@class='a-dropdown-prompt']");
    private final By byCartHeaderTextXpath = By.xpath("//div[contains(@class,'sc-cart-header')]");

    public CartPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public CartPageScreen waitTillCartIsDisplayed() {
        driver.waitTillElementIsPresent(byActiveCartContentsXpath).isDisplayed();
        LOGGER.info("Cart page is displayed.");
        return this;
    }

    @Override
    public Map<String, String> getCartContents() {
        //currently coding it for single product only
        Map <String, String> cartContentDetails = new HashMap<>();

        WebElement cartContent = driver.findElement(byActiveCartContentsXpath);

        String productName = cartContent.findElement(byProductTitleChildXpath).getText().trim();
        cartContentDetails.put("productName", productName);
        String productPrice = cartContent.findElement(byProductPriceChildXpath).getText().trim();
        cartContentDetails.put("productPrice", productPrice);
        String productQuantity = cartContent.findElement(byProductQuantityChildXpath).getText().trim();
        cartContentDetails.put("productQuantity",productQuantity);

        LOGGER.info(String.format("Cart contents are: Product Name - '%s', Price - '%s', Quantity - '%s'.",
                productName, productPrice, productQuantity));

        return cartContentDetails;
    }

    @Override
    public String getCartHeaderText() {
        return driver.findElement(byCartHeaderTextXpath).getText().trim();
    }
}

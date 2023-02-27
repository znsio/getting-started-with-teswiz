package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.ProductDetailScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailScreenWeb extends ProductDetailScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byProductSizeXpath = By.xpath("(//div[contains(@class,\"size-instock\")])[1]");
    private static final By byAddToBagClassName = By.className("ic-pdp-add-cart");
    private static  final By byProductBrandCSS = By.cssSelector("[class = brand-name]");
    private static final By byGoToCartClass = By.className("mini-cart-btn");

    public ProductDetailScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public ProductDetailScreen selectProductSize(){
        driver.switchToNextTab();
        visually.checkWindow(SCREEN_NAME, "Product detail screen");
        WebElement webElement = driver.waitTillElementIsPresent(byProductSizeXpath);
        LOGGER.info(String.format("selectProductSize: Product size selected '%s'", webElement.getText()));
        webElement.click();
        return this;
    }
    @Override
    public ProductDetailScreen addToCart(){
        LOGGER.info("addToCart: Add product");
        driver.findElement(byAddToBagClassName).click();
        return this;
    }

    @Override
    public String getProductBrand(){
        String productBrand = driver.findElement(byProductBrandCSS).getText();
        LOGGER.info(String.format("getProductBrand: Product brand name: '%s'", productBrand));
        return productBrand;
    }

    @Override
    public ProductDetailScreen proceedToCart(){
        LOGGER.info("proceedToCart: Navigate to the shopping cart");
        driver.waitForClickabilityOf(byGoToCartClass).click();
        return this;
    }
}

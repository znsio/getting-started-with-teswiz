package com.znsio.sample.e2e.screen.web.ajioShopping;

import com.znsio.sample.e2e.screen.ajioShopping.ProductLandingPageScreen;
import com.znsio.sample.e2e.screen.ajioShopping.ProductListPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductListPageScreenWeb extends ProductListPageScreen {
    private static final String SCREEN_NAME = ProductListPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;
    private final static By productList = By.xpath("//div[@class='nameCls']");

    public ProductListPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public ProductLandingPageScreen selectProduct(int productPosition) {
        LOGGER.info(String.format("Product position: ", productPosition));
        List<WebElement> listOfProducts = driver.findElements(productList);
        String productName = listOfProducts.get(productPosition-1).getText();
        LOGGER.info(String.format("Product Name: ", productName));
        listOfProducts.get(productPosition-1).click();
        driver.switchToNextTab();
        visually.takeScreenshot(SCREEN_NAME, "selected product");
        return ProductLandingPageScreen.get();
    }
}

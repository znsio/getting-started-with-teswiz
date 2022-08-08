package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomePageScreen;
import com.znsio.sample.e2e.screen.amazon.ProductSearchResultScreen;
import org.openqa.selenium.By;

public class AmazonHomePageScreenWeb extends AmazonHomePageScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();

    private final By searchBoxInputField = By.id("twotabsearchtextbox");
    private final By searchButton = By.id("nav-search-submit-button");

    public AmazonHomePageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    public void openHomePage(){
    }

    public ProductSearchResultScreen searchForProduct(String productName) {
        driver.findElement(searchBoxInputField).sendKeys(productName);
        driver.findElement(searchButton).click();
        return new ProductSearchResultScreenWeb(driver,visually);
    }
}

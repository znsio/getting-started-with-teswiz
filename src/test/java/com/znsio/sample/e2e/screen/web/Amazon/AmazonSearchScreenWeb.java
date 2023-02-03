package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.businessLayer.amazon.AmazonProductBL;
import com.znsio.sample.e2e.screen.amazon.AmazonProductScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonSearchScreenWeb extends AmazonSearchScreen {
    private final Driver driver;
    private final WebDriver innerDriver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By bySearchBoxId = By.xpath("//input[@id='twotabsearchtextbox']");
    private static final By bySearchIconId =By.xpath("//input[@id='nav-search-submit-button']");

    public AmazonSearchScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AmazonProductScreen enteringAndSearchingProduct(String productName)
    {
        WebElement searchElement=driver.waitTillElementIsPresent(bySearchBoxId);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(productName);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchIcon=driver.waitTillElementIsPresent(bySearchIconId);
        searchIcon.click();
        LOGGER.info("Searching for the product in progress");
        return AmazonProductScreen.get();
    }

}

package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.amazonsearch.AmazonProductViewScreen;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxXpath = By.id("twotabsearchtextbox");
    private static final By bySearchIconClassName = By.cssSelector("[value='Go']");
    private static final By firstProduct = By.xpath("//div[@data-index='4']//h2//span");

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonProductViewScreen searchProductInAmazonSearch(String productName){
        LOGGER.info(String.format("Search for '%s'", productName));
        WebElement searchElement = driver.waitTillElementIsPresent(bySearchBoxXpath);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(productName);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        driver.waitTillElementIsPresent(bySearchIconClassName).click();
        return AmazonProductViewScreen.get();
    }

    @Override
    public String getActualSearchProduct(){
        LOGGER.info(String.format("Getting actual searched product"));
        WebElement searchElement = driver.waitTillElementIsPresent(firstProduct);
        return searchElement.getText();
    }
}

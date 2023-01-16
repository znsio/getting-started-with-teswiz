package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonHomeScreen;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final By bySearchBoxXpath = By.xpath("//input[@id='twotabsearchtextbox']");

    private static final By bySearchIconXpath = By.xpath("//input[@id='nav-search-submit-button']");
    private static final By byProductSearchedXpath = By.xpath("//span[@class='a-color-state a-text-bold']");
    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public AmazonHomeScreen searchProductFromSearchbar(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        WebElement searchBox=driver.waitTillElementIsVisible(bySearchBoxXpath);
        searchBox.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchIcon=driver.waitTillElementIsVisible(bySearchIconXpath);
        searchIcon.click();
        return this;
    }

    @Override
    public String getActualSearchString() {
        LOGGER.info(String.format("Getting actual search string"));
        WebElement actualSearchProduct= driver.waitTillElementIsVisible(byProductSearchedXpath);
        String actualSearchProductString=actualSearchProduct.getText().replaceAll("\"","").trim();
        return actualSearchProductString;
    }

}

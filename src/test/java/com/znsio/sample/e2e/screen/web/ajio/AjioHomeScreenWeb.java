package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjioHomeScreenWeb
        extends AjioHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxXpath = By.xpath("//input[@name='searchVal']");
    private static final By bySearchIconClassName = By.className("ic-search");

    public AjioHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public AjioSearchResultsScreen searchFor(String product) {
        LOGGER.info(String.format("Search for '%s'", product));
        WebElement searchElement = driver.waitTillElementIsPresent(bySearchBoxXpath);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(product);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        driver.waitTillElementIsPresent(bySearchIconClassName);
        return AjioSearchResultsScreen.get();
    }
}

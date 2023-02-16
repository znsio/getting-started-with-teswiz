package com.znsio.sample.e2e.screen.web.ajio2;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio2.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio2.AjioSearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjioHomeScreenWeb
        extends AjioHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By bySearchBoxXpath = By.xpath("//input[@name='searchVal']");
    private static final By bySearchIconClassName = By.cssSelector("[class=\"rilrtl-button\"]");

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
        driver.waitForClickabilityOf(bySearchIconClassName).click();
        return AjioSearchResultsScreen.get();
    }
}

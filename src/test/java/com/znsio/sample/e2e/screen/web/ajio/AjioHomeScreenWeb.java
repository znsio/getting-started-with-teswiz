package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Map;

public class AjioHomeScreenWeb
        extends AjioHomeScreen {
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxXpath = By.xpath("//input[@name='searchVal']");
    private static final By bySearchIconClassName = By.className("ic-search");
    private final Driver driver;
    private final Visual visually;

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

    @Override
    public AjioHomeScreen goToMenu() {
        throw new NotImplementedException(
                SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public AjioSearchResultsScreen selectProductFromCategory(String product, String category, String gender) {
        throw new NotImplementedException(
                SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public AjioHomeScreen attachFileToDevice(Map imageData) {
        throw new NotImplementedException(
                SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

    @Override
    public AjioSearchResultsScreen searchByImage() {
        throw new NotImplementedException(
                SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }
}

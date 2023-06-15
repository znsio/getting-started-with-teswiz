package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjioHomeScreenWeb
        extends AjioHomeScreen {
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxXpath = By.xpath("//input[@name='searchVal']");
    private static final By bySearchIconClassName = By.className("ic-search");
    private static final By bySignInXpath = By.xpath("//span[contains(text(),'Sign In / Join AJIO')]");
    private static final By byEmailEntryClassName = By.className("username");
    private static final By byContinueClassName = By.className("login-btn");
    private static final By byLoginWithPasswordXpath = By.xpath("//input[@value='LOGIN WITH PASSWORD']");
    private static final By byPasswordEntryId = By.id("pwdInput");
    private static final By byStartShoppingXpath = By.xpath("//input[@value='START SHOPPING']");
    private static final By byMyAccountXpath = By.xpath("//a[text()='My Account']");


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
        driver.waitTillElementIsPresent(bySearchIconClassName).click();
        return AjioSearchResultsScreen.get();
    }

    @Override
    public AjioHomeScreen signInUser(String emailId, String password) {
        LOGGER.info("Logging in as Valid user");
        driver.waitForClickabilityOf(bySignInXpath).click();
        driver.findElement(byEmailEntryClassName).sendKeys(emailId);
        driver.findElement(byContinueClassName).click();
        driver.waitForClickabilityOf(byLoginWithPasswordXpath).click();
        driver.waitTillElementIsVisible(byPasswordEntryId).sendKeys(password);
        driver.findElement(byStartShoppingXpath).click();
        return this;
    }

    @Override
    public boolean isUserSignedIn() {
        LOGGER.info("Verifying user is logged in successfully");
        driver.waitTillElementIsVisible(byMyAccountXpath);
        return driver.isElementPresent(byMyAccountXpath);
    }
}

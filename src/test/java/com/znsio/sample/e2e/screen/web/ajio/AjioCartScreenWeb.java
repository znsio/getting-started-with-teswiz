package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.AjioHomeScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjioCartScreenWeb
        extends AjioCartScreen {
    private static final String SCREEN_NAME = AjioCartScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchBoxXpath = By.xpath("//input[@name='searchVal']");
    private static final By bySignOutXpath = By.xpath("//a[text()='Sign Out']");



    private final Driver driver;
    private final Visual visually;

    public AjioCartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public boolean isProductAddedToBag() {
        return false;
    }

    @Override
    public AjioCartScreen removeProductFromCart() {
        return this;
    }

    @Override
    public boolean isProductRemovedFromCart() {
        return false;
    }

    @Override
    public boolean isCartEmpty() {
        return false;
    }

    @Override
    public AjioHomeScreen logout() {
        LOGGER.info("Logout user");
        driver.findElement(bySignOutXpath).click();
        return AjioHomeScreen.get();
    }
}

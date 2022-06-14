package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.HomeScreen;
import com.znsio.e2e.screen.amazon.ProductListingScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;


public class HomeScreenWeb extends HomeScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(HomeScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = HomeScreenWeb.class.getSimpleName();

    public HomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen");
    }

    @Override
    public HomeScreenWeb login() {
        driver.findElementByXpath("//span[text()='Account & Lists']").click();
        driver.findElementByXpath("//input[@type='email']").sendKeys("email");
        driver.findElementByXpath("//input[@id='continue']").click();
        driver.findElementByXpath("//input[@type='password']").sendKeys("password");
        driver.findElementByXpath("//span[@id='auth-signin-button']").click();
        driver.findElementByXpath("//div[@id='nav-logo']").click();
        return this;
    }

    @Override
    public ProductListingScreen searchForItem(String keyWord) {
        driver.findElementByXpath("//input[@id='twotabsearchtextbox']").sendKeys(keyWord);
        driver.findElementByXpath("//input[@id='nav-search-submit-button']").click();
        return ProductListingScreen.get();
    }
}
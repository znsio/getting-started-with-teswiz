package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.HomeScreen;
import com.znsio.e2e.screen.amazon.ProductListingScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;


public class HomeScreenWeb extends HomeScreen {
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
        if (driver.findElementByXpath("//span[@id='nav-link-accountList-nav-line-1']").getText().contains("Sign in")) {
            driver.findElementByXpath("//span[text()='Account & Lists']").click();
            driver.findElementByXpath("//input[@type='email']").sendKeys("email");
            driver.findElementByXpath("//input[@id='continue']").click();
            driver.findElementByXpath("//input[@type='password']").sendKeys("password");
            driver.findElementByXpath("//span[@id='auth-signin-button']").click();
        }
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
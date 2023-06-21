package com.znsio.sample.e2e.screen.web.zomato;

import com.znsio.sample.e2e.screen.zomato.HomePageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class HomePageScreenWeb extends HomePageScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = HomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(HomePageScreenWeb.class.getName());
    private final String homePagePartialUrl = "www.zomato.com";
    private final By byDiningCardText = By.xpath("//p[text()='Dining']");

    public HomePageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean verifyHomePageLaunch() {
        driver.getInnerDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        LOGGER.info("Verifying Home Page launch");
        return driver.getInnerDriver().getCurrentUrl().contains(homePagePartialUrl);
    }

    @Override
    public HomePageScreen selectDiningOption() {
        driver.waitTillElementIsPresent(byDiningCardText);
        visually.checkWindow(SCREEN_NAME, "HomePage Screen");
        LOGGER.info("Selecting Dining option");
        driver.findElement(byDiningCardText).click();
        return this;
    }
}

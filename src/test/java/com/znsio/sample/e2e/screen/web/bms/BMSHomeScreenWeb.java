package com.znsio.sample.e2e.screen.web.bms;

import com.znsio.sample.e2e.screen.bms.BMSHomeScreen;
import com.znsio.sample.e2e.screen.bms.BMSMoviesScreen;
import com.znsio.sample.e2e.screen.web.jiomeet.InAMeetingScreenWeb;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class BMSHomeScreenWeb extends BMSHomeScreen {

    private static final String SCREEN_NAME = InAMeetingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By bySearchBarXpath = By.xpath("//input[@placeholder = 'Search for your city']");

    private final By byMoviesTabXpath =  By.xpath("//a[text()='Movies']");

    public BMSHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }


    public BMSHomeScreen searchForLocation(String location) {

        LOGGER.info("Location to be searched is " + location);
        driver.waitTillElementIsPresent(bySearchBarXpath).sendKeys(location);
        visually.takeScreenshot(SCREEN_NAME,"BMS Homepage");
        driver.waitTillElementIsVisible(By.xpath("//strong[text()='"+location+"']")).click();
        LOGGER.info(location + " searched and clicked");

        return this;
    }

    @Override
    public BMSMoviesScreen selectMoviesCategory() {

        LOGGER.info("Selecting MOVIES tab");
        driver.waitTillElementIsPresent(byMoviesTabXpath).click();
        LOGGER.info("Movies tab selected successfully");

        return BMSMoviesScreen.get();
    }

}

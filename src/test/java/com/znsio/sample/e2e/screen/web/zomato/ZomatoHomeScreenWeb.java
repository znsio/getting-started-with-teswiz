package com.znsio.sample.e2e.screen.web.zomato;

import com.znsio.sample.e2e.screen.zomato.DiningOutScreen;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomeScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ZomatoHomeScreenWeb extends ZomatoHomeScreen {
    private static final String SCREEN_NAME = ZomatoHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final By byCityNameInputXpath = By.xpath("(//input)[1]");
    private final String bySelectCityXpath = "//p[text()='%s']";

    public ZomatoHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public DiningOutScreen selectLocation(String cityName) {
        visually.checkWindow(SCREEN_NAME, "Home page");
        driver.waitTillElementIsPresent(byCityNameInputXpath).clear();
        driver.findElement(byCityNameInputXpath).sendKeys(cityName);
        driver.findElement(byCityNameInputXpath).click();
        driver.waitTillPresenceOfAllElements(By.xpath(String.format(bySelectCityXpath, cityName))).get(0).click();
        return DiningOutScreen.get();
    }

}

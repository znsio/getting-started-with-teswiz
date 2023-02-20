package com.znsio.sample.e2e.screen.web.Yatra;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Yatra.YatraHomeScreen;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YatraHomeWeb extends YatraHomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    private static final By byTripTypeCss = By.cssSelector("[title=\"Multicity\"]");
    private static  final By byFirstSourceCityId = By.id("BE_flight_origin_city_1");
    private static final By byFirstArrivalCityId = By.id("BE_flight_arrival_city1");
    private static final By bySecondSourceCityId = By.id("BE_flight_origin_city_2");
    private static final By bySecondDestinationCityId = By.id("BE_flight_arrival_city2");
    private static final By byTravelDateXpath = By.xpath("(//td[not (contains(@class,'inActiveTD')) and contains(@id,'01')])[1]");


    public YatraHomeWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public YatraHomeScreen selectTripType(String tripType) {
        LOGGER.info(String.format("selectTripType: Select '%s' trip", tripType));
        visually.checkWindow(SCREEN_NAME, "Home Screen");
        driver.waitTillElementIsPresent(byTripTypeCss).click();
        return this;
    }

    @Override
    public YatraHomeScreen selectSourceCity(String sourceCity) {
        LOGGER.info(String.format("selectSourceCity: Select first source city: '%s'", sourceCity));
        WebElement firstSourceCity = driver.findElement(byFirstSourceCityId);
        firstSourceCity.clear();
        firstSourceCity.sendKeys(sourceCity);
        return this;
    }

    @Override
    public YatraHomeScreen selectDestinationCity(String destinationCity) {
        LOGGER.info(String.format("selectSourceCity: Select first source city: '%s'", destinationCity));
        WebElement firstSourceCity = driver.findElement(byFirstArrivalCityId);
        firstSourceCity.clear();
        firstSourceCity.sendKeys(destinationCity);
        return this;
    }

    @Override
    public YatraHomeScreen selectSecondSourceCity(String sourceCity) {
        LOGGER.info(String.format("selectSecondSourceCity: Select first source city: '%s'", sourceCity));
        WebElement firstSourceCity = driver.findElement(bySecondSourceCityId);
        firstSourceCity.clear();
        firstSourceCity.sendKeys(sourceCity);
        return this;
    }

    @Override
    public YatraHomeScreen selectSecondDestinationCity(String destinationCity) {
        LOGGER.info(String.format("selectSecondDestinationCity: Select first source city: '%s'", destinationCity));
        WebElement firstSourceCity = driver.findElement(bySecondDestinationCityId);
        firstSourceCity.clear();
        firstSourceCity.sendKeys(destinationCity);
        return this;
    }

    @Override
    public YatraHomeScreen selectTravelDate() {
        LOGGER.info("selectTravelDate: Select travel date");
        WebElement firstSourceCity = driver.findElement(byTravelDateXpath);
        return this;
    }
}

package com.znsio.sample.e2e.screen.web.Yatra;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Yatra.HomeScreen;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class HomeWeb extends HomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    private static final By byTripTypeCss = By.cssSelector("[title=\"Multicity\"]");
    private static final String bySourceCityId = "BE_flight_origin_city_%s";
    private static final String byArrivalCityId = "BE_flight_arrival_city%s";

    private static final By byTravelDateXpath = By.id("BE_flight_origin_date_2");
    private static final By bySelectDateXpath = By.xpath("(//td[not (contains(@class,'inActiveTD')) and contains(@id,'01')])[1]");
    private static final By byLogoCss = By.cssSelector("a.logo");
    private static final String bySourceCityCodeXpath = "//label[@for = 'BE_flight_origin_city_%s']//p";
    private static final String byDestinationCityCodeXpath = "//label[@for = 'BE_flight_arrival_city%s']//p";
    private static final By byPopUpCloseCss = By.cssSelector("[class=\"close\"]");
    private static final String byAdvertisementFrameId = "webklipper-publisher-widget-container-notification-frame";
    private static final String  bySelectCityXath = "//p[contains(text(), '%s')]";


    public HomeWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public HomeScreen selectTripType(String tripType) {
        LOGGER.info(String.format("selectTripType: Select '%s' trip", tripType));
        visually.checkWindow(SCREEN_NAME, "Home Screen");
        driver.switchFrameToDefault();
        driver.waitTillElementIsPresent(byTripTypeCss).click();
        return this;
    }

    @Override
    public HomeScreen selectSourceCity(String sourceCity, String tripNumber) {
        LOGGER.info(String.format("selectSourceCity: Select source city: '%s'", sourceCity));
        driver.waitForClickabilityOf(By.id(String.format(bySourceCityId, tripNumber))).click();
        driver.waitTillElementIsPresent(By.xpath(String.format(bySelectCityXath, sourceCity))).click();
        return this;
    }

    @Override
    public HomeScreen selectDestinationCity(String destinationCity, String tripNumber) {
        LOGGER.info(String.format("selectDestinationCity: Select destination city: '%s'", destinationCity));
        driver.waitForClickabilityOf(By.id(String.format(byArrivalCityId, tripNumber))).click();
        driver.waitTillElementIsPresent(By.xpath(String.format(bySelectCityXath, destinationCity))).click();
        return this;
    }

    @Override
    public HomeScreen selectTravelDate() {
        LOGGER.info("selectTravelDate: Select travel date");
        driver.findElement(byTravelDateXpath).click();
        driver.waitTillElementIsPresent(bySelectDateXpath).click();
        return this;
    }

    @Override
    public String getSourceCity(String tripNumber) {
        String sourceCityCode = driver.findElement(By.xpath(String.format(bySourceCityCodeXpath, tripNumber))).getText();
        LOGGER.info(String.format("getSourceCity: Source city code: '%s'", sourceCityCode));
        return sourceCityCode;
    }

    @Override
    public String getDestinationCity(String tripNumber) {
        String destinationCityCode = driver.findElement(By.xpath(String.format(byDestinationCityCodeXpath, tripNumber))).getText();
        LOGGER.info(String.format("getDestinationCity: Destination city code: '%s'", destinationCityCode));
        return destinationCityCode;
    }

    @Override
    public String getTitle() {
        String title = driver.waitTillElementIsPresent(byLogoCss).getAttribute("title");
        LOGGER.info(String.format("getTitle: title available: '%s'", title));
        return title;
    }

    @Override
    public HomeScreen closeAdvertisement() {
        try {
            LOGGER.info("closeAdvertisement: Close advertisement pop up");
            driver.waitTillElementIsPresent(By.id(byAdvertisementFrameId));
            driver.switchToFrame(byAdvertisementFrameId);
            driver.waitForClickabilityOf(byPopUpCloseCss, 30).click();
        }catch (NoSuchElementException e){
            LOGGER.info("closeAdvertisement: No advertisement are visible");
        }
        return this;
    }

    @Override
    public HomeScreen selectClassOption() {
        return this;
    }

    @Override
    public HomeScreen addAdults(String adultCount) {
        return this;
    }

    @Override
    public HomeScreen addChildren(String childrenCount) {
        return this;
    }

    @Override
    public HomeScreen addInfants(String infantCount) {
        return this;
    }
}

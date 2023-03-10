package com.znsio.sample.e2e.screen.web.Yatra;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Yatra.HomeScreen;
import com.znsio.sample.e2e.screen.web.ajio.AjioHomeScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class HomeWeb extends HomeScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    private static final By byTripTypeCss = By.cssSelector("[title=\"Multicity\"]");
    private static final By byTravelDateXpath = By.id("BE_flight_origin_date_2");
    private static final By bySelectDateXpath = By.xpath("(//td[not (contains(@class,'inActiveTD')) and contains(@id,'01')])[1]");
    private static final By byLogoCss = By.cssSelector("a.logo");
    private static final By byPopUpCloseCss = By.cssSelector("[class=\"close\"]");
    private static final String byAdvertisementFrameId = "webklipper-publisher-widget-container-notification-frame";
    private static final String  bySelectCityXpath = "//p[contains(text(), '%s')]";
    private static final By byTravellersOptionClassName = By.className("arrowpassengerBox");
    private static final String byClassOptionsXpath = "//div[@id='flight_class_select_child']//span[contains(text(), '%s')]";
    private static final By bySelectNonStopCss = By.cssSelector("[title=\"Non Stop Flights\"]");
    private static final By bySearchFightCss = By.cssSelector("[value=\"Search Flights\"]");
    private static final By byTotalPassengerCountClassName = By.className("totalCount");
    private static final By byAddAdultXpath = By.xpath("//div[@data-flightagegroup='adult' and contains(@class, 'pax-limit')]//span[@class='ddSpinnerPlus']");
    private static final By byAddChildrenXpath = By.xpath("//div[@data-flightagegroup='child' and contains(@class, 'pax-limit')]//span[@class='ddSpinnerPlus']");
    private static final By byAddInfantXpath = By.xpath("//div[@data-flightagegroup='infant' and contains(@class, 'pax-limit')]//span[@class='ddSpinnerPlus']");
    private static final By byFirstSourceCityId = By.id("BE_flight_origin_city_1");
    private static final By byFirstDestinationCityId = By.id("BE_flight_arrival_city1");
    private static final By bySecondSourceCityId = By.id("BE_flight_origin_city_2");
    private static final By bySecondDestinationCityId = By.id("BE_flight_arrival_city2");
    private static final By byFirstSourceCityCodeXpath = By.xpath("//label[@for = 'BE_flight_origin_city_1']//p");
    private static final By byFirstDestinationCityCodeXpath = By.xpath("//label[@for = 'BE_flight_arrival_city1']//p");
    private static final By bySecondSourceCityCodeXpath = By.xpath("//label[@for = 'BE_flight_origin_city_2']//p");
    private static final By bySecondDestinationCityCodeXpath = By.xpath("//label[@for = 'BE_flight_arrival_city2']//p");

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
    public HomeScreen selectFirstSourceCity(String sourceCity) {
        LOGGER.info(String.format("selectSourceCity: Select first source city: '%s'", sourceCity));
        driver.waitForClickabilityOf(byFirstSourceCityId).click();
        return selectCity(sourceCity);
    }

    @Override
    public HomeScreen selectFirstDestinationCity(String destinationCity) {
        LOGGER.info(String.format("selectDestinationCity: Select first destination city: '%s'", destinationCity));
        driver.waitForClickabilityOf(byFirstDestinationCityId).click();
        return selectCity(destinationCity);
    }

    @Override
    public HomeScreen selectSecondSourceCity(String sourceCity) {
        LOGGER.info(String.format("selectSourceCity: Select second source city: '%s'", sourceCity));
        driver.waitForClickabilityOf(bySecondSourceCityId).click();
        return selectCity(sourceCity);
    }

    @Override
    public HomeScreen selectSecondDestinationCity(String destinationCity) {
        LOGGER.info(String.format("selectDestinationCity: Select first destination city: '%s'", destinationCity));
        driver.waitForClickabilityOf(bySecondDestinationCityId).click();
        return selectCity(destinationCity);
    }

    private HomeScreen selectCity(String cityName){
        driver.waitTillElementIsPresent(By.xpath(String.format(bySelectCityXpath, cityName))).click();
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
    public String getFirstSourceCity() {
        String sourceCityCode = driver.findElement(byFirstSourceCityCodeXpath).getText();
        LOGGER.info(String.format("getSourceCity: Source city code: '%s'", sourceCityCode));
        return sourceCityCode;
    }

    @Override
    public String getFirstDestinationCity() {
        String destinationCityCode = driver.findElement(byFirstDestinationCityCodeXpath).getText();
        LOGGER.info(String.format("getDestinationCity: Destination city code: '%s'", destinationCityCode));
        return destinationCityCode;
    }

    @Override
    public String getSecondSourceCity() {
        String sourceCityCode = driver.findElement(bySecondSourceCityCodeXpath).getText();
        LOGGER.info(String.format("getSourceCity: Source city code: '%s'", sourceCityCode));
        return sourceCityCode;
    }

    @Override
    public String getSecondDestinationCity() {
        String destinationCityCode = driver.findElement(bySecondDestinationCityCodeXpath).getText();
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
    public HomeScreen selectTravellerOption() {
        LOGGER.info("selectTravellerOption: Select traveller's option for the flight");
        driver.findElement(byTravellersOptionClassName).click();
        return this;
    }

    @Override
    public HomeScreen addAdults(int adultCount) {
        LOGGER.info(String.format("addAdults: Add adults for the flight '%s'", adultCount));
        for(int adult=1; adult<adultCount; adult++){
            driver.findElement(byAddAdultXpath).click();
        }
        return this;
    }

    @Override
    public HomeScreen addChildren(int childrenCount) {
        LOGGER.info(String.format("addChildren: Add children for the flight '%s'", childrenCount));
        for(int child=1; child<=childrenCount; child++){
            driver.findElement(byAddChildrenXpath).click();
        }
        return this;
    }

    @Override
    public HomeScreen addInfants(int infantCount) {
        LOGGER.info(String.format("addInfants: Add infant for the flight: '%s'", infantCount));
        for(int infant=1; infant <= infantCount; infant++){
            driver.findElement(byAddInfantXpath).click();
        }
        return this;
    }

    @Override
    public HomeScreen selectClass(String flightClass) {
        LOGGER.info(String.format("selectFlightClass: Select flight class: '%s'", flightClass));
        driver.findElement(By.xpath(String.format(byClassOptionsXpath, flightClass))).click();
        return this;
    }

    @Override
    public HomeScreen selectNonStop() {
        LOGGER.info("selectNonStopFlight: Select non stop flight type");
        driver.findElement(bySelectNonStopCss).click();
        return this;
    }

    @Override
    public HomeScreen selectSearch() {
        LOGGER.info("selectSearchFight: Select search flight option");
        driver.findElement(bySearchFightCss).click();
        return this;
    }

    @Override
    public int getTravelCount() {
        String totalCount = driver.findElement(byTotalPassengerCountClassName).getText();
        LOGGER.info(String.format("getTravelCount: Total number of passenger added '%s'", totalCount));
        return Integer.parseInt(totalCount);
    }
}

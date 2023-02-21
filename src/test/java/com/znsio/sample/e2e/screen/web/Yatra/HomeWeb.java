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
    private static final String bySourceCityId = "BE_flight_origin_city_%s";
    private static final String byArrivalCityId = "BE_flight_arrival_city%s";

    private static final By byTravelDateXpath = By.id("BE_flight_origin_date_2");
    private static final By bySelectDateXpath = By.xpath("(//td[not (contains(@class,'inActiveTD')) and contains(@id,'01')])[1]");
    private static final By byLogoCss = By.cssSelector("a.logo");
    private static final String bySourceCityCodeXpath = "//label[@for = 'BE_flight_origin_city_%s']//p";
    private static final String byDestinationCityCodeXpath = "//label[@for = 'BE_flight_arrival_city%s']//p";
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
        driver.waitTillElementIsPresent(By.xpath(String.format(bySelectCityXpath, sourceCity))).click();
        return this;
    }

    @Override
    public HomeScreen selectDestinationCity(String destinationCity, String tripNumber) {
        LOGGER.info(String.format("selectDestinationCity: Select destination city: '%s'", destinationCity));
        driver.waitForClickabilityOf(By.id(String.format(byArrivalCityId, tripNumber))).click();
        driver.waitTillElementIsPresent(By.xpath(String.format(bySelectCityXpath, destinationCity))).click();
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

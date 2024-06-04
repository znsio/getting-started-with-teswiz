package com.znsio.sample.e2e.screen.web.bookingDotCom;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.bookingDotCom.BookingDotComFlightSearchResultsScreen;
import com.znsio.sample.e2e.screen.bookingDotCom.BookingDotComFlightsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class BookingDotComFlightsScreenWeb extends BookingDotComFlightsScreen {
    private static final String SCREEN_NAME = BookingDotComFlightsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;

    public BookingDotComFlightsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public BookingDotComFlightsScreen selectJourneyType(String journeyType) {
        driver.waitTillElementIsPresent(By.xpath("//div[text()='" + journeyType + "']")).click();
        return this;
    }

    @Override
    public BookingDotComFlightsScreen selectTo(String destination) {
        driver.waitTillElementIsPresent(By.xpath("//button[@data-ui-name='input_location_to_segment_0']")).click();
        driver.waitTillElementIsPresent(By.xpath("//input[@data-ui-name='input_text_autocomplete']")).sendKeys(destination);
        driver.waitTillElementIsPresent(By.xpath("//ul[@id='flights-searchbox_suggestions']/li[1]")).click();
        return this;
    }

    @Override
    public BookingDotComFlightSearchResultsScreen searchFlightOptions() {
        visually.check(SCREEN_NAME, "Search options selected", Target.region(By.xpath("//div[contains(@class, 'Frame-module__margin-top_6')]")));
        driver.waitTillElementIsPresent(By.xpath("//button[@data-ui-name='button_search_submit']")).click();
        driver.waitTillElementIsPresent(By.xpath("//h2[text()='Filters']"));
        visually.checkWindow(SCREEN_NAME, "Search Results");
        return BookingDotComFlightSearchResultsScreen.get();
    }
}
